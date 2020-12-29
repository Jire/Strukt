# Strukt

C-style structs on the JVM!

**ZERO** garbage, **ZERO** reflection, **ZERO** code generation.  
**EASY** to use, and with **INCREDIBLE** [performance](#performance).

[![Build Status](https://travis-ci.com/Jire/Strukt.svg?branch=master)](https://travis-ci.com/Jire/Strukt)
[![license](https://img.shields.io/github/license/Jire/Strukt.svg)](https://github.com/Jire/Strukt/blob/master/LICENSE.txt)

### Gradle

```groovy
compile group: 'org.jire.strukt', name: 'strukt', version: '3.0.0'
```

### Maven

```xml

<dependency>
	<groupId>org.jire.strukt</groupId>
	<artifactId>strukt</artifactId>
	<version>3.0.0</version>
</dependency>
```

---

## Declaring a Strukt

First you need to use one of the extension types on your `Strukt`'s `::class` which manages (allocates, frees, etc.)
your `Strukt` to create a `Strukts`.

```kotlin
val points = Point::class.dynamic()
```

You can also create a fixed-size amount of `Strukt`s like so:

```kotlin
val points = Point::class.fixed(1_000_000)
```

A fixed `Strukts` can also be persisted to the disk as a memory mapped file!
This means you can allocate with as much space as disk space, rather than RAM!

For example, the mapped file in this example allows us to work with 256GB of points on a machine with limited RAM!

```kotlin
val points = Point::class.fixed(32_000_000_000, "points.dat")
```

Then, you need to declare your fields using your `Strukts`s invoke operator.

The value you pass in determines the field's type, and the default value for each allocation -- in this case it's `0`
for both.

```kotlin
val pointX = points(0)
val pointY = points(0)
```

Now we need to define our inline `Strukt` class.

```kotlin
inline class Point(override val address: Long = points()) : Strukt {
	var x
		get() = pointX(address)
		set(value) = pointX(address, value)
	var y
		get() = pointY(address)
		set(value) = pointY(address, value)
}
```

Because of the way inlining works, you'll notice that we need to provide virtual fields using our previously defined
fields.

You should also notice the use of `Strukts`.`invoke` as a default value for allocating our address.

## Allocating a `Strukt`

The syntax for allocation is the same as a regular object!

For the above _Point_ example, this might look like:

```kotlin
val example = Point()
```

## Accessing fields

Accessing fields is exactly like normal objects!

```kotlin
example.y = 123
println("x: ${example.x}, y: ${example.y}") // 0, 123
```

## Freeing a `Strukt`

To free any `Strukt`, you can use:

```kotlin
points.free(example.address)
```

The reason this longer syntax is necessary is because we want to avoid "generic" type use of our inline class, to
prevent creating boxed instances.

## Performance

**Benchmark sources:** https://github.com/Jire/Strukt/tree/master/src/jmh/kotlin/org/jire/strukt/benchmarks

#### Test Bench

* **OS:** Windows 10 Pro 64-bit (10.0, Build 19042)
* **CPU:** Intel(R) Core(TM) i7-6700K CPU @ 4.60GHz (8 CPUs), ~4.6GHz
* **RAM:** 2x 16GB DDR4-2100MHz
* **JMH version:** 1.25
* **VM version:** JDK 1.8.0_271, Java HotSpot(TM) 64-Bit Server VM, 25.271-b09
* **VM options:** <none>
  Tested on an Intel i7 6700K @ 4.6GHz with default Oracle JDK 8 VM parameters on Windows 10:

```
Benchmark                                  Mode  Cnt          Score   Error  Units

HeapRead.read                             thrpt       434769304.174          ops/s
PointedRead.read                          thrpt       372296437.498          ops/s
FixedRead.read                            thrpt       438208243.968          ops/s
ElasticRead.read                          thrpt       418701531.606          ops/s

HeapWrite.write                           thrpt       561591456.153          ops/s
PointedWrite.write                        thrpt       622915634.215          ops/s
FixedWrite.write                          thrpt       631370517.687          ops/s
ElasticWrite.write                        thrpt       600571289.654          ops/s

HeapAllocate.allocate                        ss              96.597          ms/op
PointedAllocate.allocate                     ss           20556.955          ms/op
FixedAllocate.allocate                       ss            2436.920          ms/op
ElasticAllocate.allocate                     ss            1882.812          ms/op

FixedFree.free                               ss            4836.308          ms/op
ElasticFree.free                             ss            4876.006          ms/op
```