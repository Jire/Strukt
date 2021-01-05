@file:JvmName("StruktsReified")

package org.jire.strukt

import java.io.File
import kotlin.reflect.KClass

@Deprecated("Use the type you want instead of this. Will be removed in future.", ReplaceWith("pointed()"))
inline fun <reified T : Strukt> KClass<T>.dynamic(): Strukts = pointed() // default

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long) = Strukts.fixed(T::class, capacity)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedTo: File) =
	Strukts.fixed(T::class, capacity, persistedTo)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedToPathname: String) =
	Strukts.fixed(T::class, capacity, persistedToPathname)

inline fun <reified T : Strukt> KClass<T>.pointed(): Strukts = Strukts.pointed(T::class)

@JvmOverloads
inline fun <reified T : Strukt> KClass<T>.elastic(
	initialCapacity: Long = Strukts.DEFAULT_ELASTIC_CAPACITY,
	growthFactor: Double = Strukts.DEFAULT_ELASTIC_GROWTH_FACTOR
) = Strukts.elastic(T::class, initialCapacity, growthFactor)