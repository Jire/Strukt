@file:JvmName("StruktFields")

package org.jire.strukt

import kotlin.reflect.KClass

operator fun <T : Strukt> KClass<T>.invoke(default: Byte = 0) = ByteField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Short = 0) = ShortField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Int = 0) = IntField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Long = 0) = LongField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Float = 0F) = FloatField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Double = 0.0) = DoubleField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Boolean = false) = BooleanField(this, default)
operator fun <T : Strukt> KClass<T>.invoke(default: Char = 0.toChar()) = CharField(this, default)
fun <T : Strukt> KClass<T>.strukt(default: Long) = StruktLongField(this, default)