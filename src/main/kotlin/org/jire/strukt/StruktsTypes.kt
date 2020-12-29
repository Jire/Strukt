@file:JvmName("StruktsTypes")

package org.jire.strukt

import org.jire.strukt.elastic.ElasticStrukts
import org.jire.strukt.fixed.FixedStrukts
import org.jire.strukt.pointed.PointedStrukts
import java.io.File
import kotlin.reflect.KClass

@Deprecated("Use the type you want instead of this. Will be removed in future.", ReplaceWith("pointed()"))
inline fun <reified T : Strukt> KClass<T>.dynamic(): Strukts<T> = pointed() // default

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long): Strukts<T> =
	FixedStrukts(T::class, capacity, null)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedTo: File? = null): Strukts<T> =
	FixedStrukts(T::class, capacity, persistedTo)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedToPathname: String? = null): Strukts<T> =
	FixedStrukts(
		T::class, capacity,
		if (persistedToPathname == null) null else File(persistedToPathname)
	)

inline fun <reified T : Strukt> KClass<T>.pointed(): Strukts<T> = PointedStrukts(T::class)

inline fun <reified T : Strukt> KClass<T>.elastic(initialCapacity: Long, growthFactor: Double = 2.0): Strukts<T> =
	ElasticStrukts(T::class, initialCapacity, growthFactor)