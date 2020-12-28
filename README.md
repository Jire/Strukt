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
val points = Point::class.pointed
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

To free any `Strukt`, it's as simple as using `free()`:

```kotlin
example.free()
```

## Performance

**Benchmark sources:** https://github.com/Jire/Strukt/tree/master/src/jmh/kotlin/org/jire/strukt/benchmarks

Tested on an Intel i7 6700K @ 4.6GHz with default Oracle JDK 15 VM parameters on Windows 10:

```Benchmark                        Mode  Cnt          Score   Error  Units
StruktBenchmark.allocateHeap    thrpt       255688253.582          ops/s
StruktBenchmark.allocateStrukt  thrpt        18503518.730          ops/s
StruktBenchmark.readHeap        thrpt       606700018.268          ops/s
StruktBenchmark.readStrukt      thrpt       297642739.099          ops/s
StruktBenchmark.writeHeap       thrpt       474856413.211          ops/s
StruktBenchmark.writeStrukt     thrpt       296077262.619          ops/s
```

Results

* ~14x (1400%) slower allocation
* ~2x (200%) slower read
* ~1.6x (160%) slower write

So there is insignificant overhead for read/write, but significant overhead for allocation (but remember, we aren't
allocating on-heap and there are absolutely <ins>no heap allocations</ins>!)