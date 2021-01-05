# Strukt

C-style structs on the JVM!

**ZERO** garbage, **ZERO** reflection, **ZERO** code generation.  
**EASY** to use, and with **INCREDIBLE** [performance](#performance).

[![Maven Central](https://img.shields.io/maven-central/v/org.jire.strukt/strukt.svg)](https://search.maven.org/search?q=g:org.jire.strukt)
[![Build Status](https://travis-ci.com/Jire/Strukt.svg?branch=master)](https://travis-ci.com/Jire/Strukt)
[![license](https://img.shields.io/github/license/Jire/Strukt.svg)](https://github.com/Jire/Strukt/blob/master/LICENSE.txt)

---

## Declaring `Strukts` to manage your `Strukt`

First you need to create a `Strukts` which manages your `Strukt` (allocates, frees, etc.).

**Fixed:** uses a single memory allocation and addresses are given with an offset. This is the fastest option available.

```kotlin
object Points : FixedStrukts(Point::class, capacity)
```

**Persisted fixed:** same as above, except uses a memory-mapped file rather than system memory and persists to disk.
This means you can allocate with as much space as disk space, rather than RAM. There is practically no performance
penalty for using this over `.fixed(capacity)`!

```kotlin
object Points : FixedStrukts(Point::class, capacity, persistedTo: File|String)
```

**Elastic:** similar to `.fixed(capacity)` except will dynamically resize, and has a performance penalty of a few
operations for that capability.

```kotlin
object Points : ElasticStrukts(Point::class, initialCapacity = 1024, growthFactor = 2.0)
```

**Pointed:** uses a laid-out pointer system, this option supports unlimited allocations without any "resizing"
necessary. Note: Since a memory allocation and a memory copy is necessary, this has slow allocations.

```kotlin
object Points : PointedStrukts(Point::class)
```

## Declaring fields of your `Strukt`

You need to declare your fields within your `Strukts`s using delegates to the default value.

The value you delegate to determines the field's type, and the default value for each allocation -- in this case it's
an `Int` of the default value `0` for both.

```kotlin
object Points : ElasticStrukts(Point::class) {
	val x by 0
	val y by 0
}
```

All primitive types are supported, and even enums:

```kotlin
enum class Type { DEFAULT, SPECIAL }

object Points : ElasticStrukts(Point::class) {
        val x by 0
        val y by 0
        val type by Type.DEFAULT  
}
```

## Declaring your inline `Strukt`

```kotlin
inline class Point(override val address: Long = Points()) : Strukt {
	var x
		get() = Points.x(address)
		set(x) = Points.x(address, x)
	var y
		get() = Points.y(address)
		set(y) = Points.y(address, y)
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

## Using in Java

It's not too hard to define and use a `Strukt` in Java!

```java
public interface Point extends Strukt {
	
	Strukts points = Strukts.elastic(Point.class);
	
	IntField x = points.intField(0);
	IntField y = points.intField(0);
	
	static long allocate() {
		return points.allocate();
	}

}
```

You can then use it like so:

```java
long example = Point.allocate();

Point.y.set(example, 123);
System.out.println("x: " + Point.x.get(example) + ", y: " + Point.y.get(example)); // 0, 123
```

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