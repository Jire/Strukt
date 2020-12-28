@file:JvmName("StruktsTypes")

package org.jire.strukt

import org.jire.strukt.fixed.FixedStrukts
import org.jire.strukt.pointed.PointedStrukts
import java.io.File
import kotlin.reflect.KClass

inline fun <reified T : Strukt> KClass<T>.dynamic(): Strukts<T> = PointedStrukts(T::class)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedTo: File? = null): Strukts<T> =
	FixedStrukts(T::class, capacity, persistedTo)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedToPathname: String? = null): Strukts<T> =
	FixedStrukts(
		T::class, capacity,
		if (persistedToPathname == null) null else File(persistedToPathname)
	)