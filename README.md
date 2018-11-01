# Strukt
C-style structs on the JVM!

**ZERO** garbage, **ZERO** reflection, **ZERO** maps. **EASY** to use, and with **INCREDIBLE** performance.

[![Build Status](https://travis-ci.org/Jire/Strukt.svg?branch=master)](https://travis-ci.org/Jire/Strukt)
[![license](https://img.shields.io/github/license/Jire/Strukt.svg)](https://github.com/Jire/Strukt/blob/master/LICENSE.txt)

**WARNING:** This library is more of a proof-of-concept, and is extremely experimental and untested. DO NOT USE IN PRODUCTION!

**New!** Now fully supported across all JVM languages!

### Gradle

```groovy
compile group: 'org.jire.strukt', name: 'strukt', version: '1.2.0'
```

### Maven

```xml
<dependency>
    <groupId>org.jire.strukt</groupId>
    <artifactId>strukt</artifactId>
    <version>1.2.0</version>
</dependency>
```

---

## Declaring a Strukt

The declaration syntax is very similar to regular classes, except:

* You should declare as an `object`, rather than `class`
* Members should be delegated by a subclass of `StruktMember`
* You must extend `Strukt`

For example, a Strukt for representing a coordinate might look like:

```kotlin
object Point : Strukt() {
	var x by 0
	var y by 0
}
```

There are member delegates built in for all primitives besides _char_.

You can also specify a default value for your member, like so:

```kotlin
var x by 3 // 3 is the default value
var y by 5 // 5 is the default value, shown with named arguments
```

## Allocating a reference

The syntax for allocation is a bit different than regular object construction.
Instead, you use the _invoke_ operator to set values.

For the above _Point_ example, this might look like:

```kotlin
val example = Point { x = 3; y = 5 }
```

If you wanted to make use of default arguments, you can omit the sets.

```kotlin
val example = Point {}
```

Allocating a reference automatically sets the _reference pointer_. (More on this in the following section.)

## Accessing members

Accessing members is the farthest deviation from regular object syntax.

It is important to understand under the hood, _Strukt_ uses something called a _reference pointer_
to keep track of what reference is currently being worked on.

Switching to a reference pointer is done through the _get_ operator on the _Strukt_'s type.

This might look like this:

```kotlin
Point[example]
```

In full effect, the _Point_ example might be used like this:

```kotlin
Point[example].y = 20
println("x: ${Point[example].x}, y: ${Point[example].y}") // prints "x: 3, y: 20"
```

Since the switch (_get_) operator switches the _reference pointer_, you can write
a shorthand version by referring to the type (_Point_ in our example) directly.

This might look like:

```kotlin
Point[example].x = 123 // `Point[example]` sets the reference pointer...
println("x: ${Point.x}, y: ${Point.y}") // so `Point.x` and `Point.y` can be referred to directly
```
