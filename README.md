# Strukt

C-style structs on the JVM!

**ZERO** garbage, **ZERO** reflection, **ZERO** code generation.  
**EASY** to use, and with **INCREDIBLE** [performance](#performance).

[![Build Status](https://travis-ci.com/Jire/Strukt.svg?branch=master)](https://travis-ci.com/Jire/Strukt)
[![license](https://img.shields.io/github/license/Jire/Strukt.svg)](https://github.com/Jire/Strukt/blob/master/LICENSE.txt)

### Gradle

```groovy
compile group: 'org.jire.strukt', name: 'strukt', version: '2.0.0'
```

### Maven

```xml

<dependency>
	<groupId>org.jire.strukt</groupId>
	<artifactId>strukt</artifactId>
	<version>2.0.0</version>
</dependency>
```

---

## Declaring a Strukt

First, you need to declare your fields using your `Strukt`'s `::class`'s invoke operator. The value you pass in
determines the field's type.

```kotlin
val pointX = Point::class(0)
val pointY = Point::class(0)
```

The value you pass in will be the default on allocation, in this case it's `0` for both.

Now we need to define our inline `Strukt` class.

```kotlin
inline class Point(override val address: Long = new<Point>()) : Strukt() {
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

You should also notice the use of `new` as a default value for allocating our address.

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

Tested on an Intel i7 6700K @ 4.6GHz with default Oracle JDK 15 VM parameters on Windows 10.

* **alloc/s:** ~15 million
* **free/s:** ~25 million
* **read/s:** ~4.4 billion
* **write/s:** ~4.4 billion

`Struct`s are completely off-heap, there are absolutely <ins>no heap allocations</ins> beyond the class configurations!