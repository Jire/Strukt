@file:JvmName("StruktsTypes")

package org.jire.strukt

import org.jire.strukt.pointed.PointedStrukts
import kotlin.reflect.KClass

inline val <reified T : Strukt> KClass<T>.pointed: Strukts<T> get() = PointedStrukts(T::class)