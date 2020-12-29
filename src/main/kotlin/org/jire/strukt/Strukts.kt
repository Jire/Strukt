package org.jire.strukt

import kotlin.reflect.KClass

interface Strukts<T : Strukt> {
	
	val type: KClass<T>
	
	var size: Long
	var nextIndex: Long
	
	val fields: MutableList<Field<T>>
	
	fun free(): Boolean
	
	fun allocate(): Long
	
	fun free(address: Long): Boolean
	fun free(strukt: T) = free(strukt.address)
	
	operator fun invoke() = allocate()
	
	operator fun invoke(default: Byte): ByteField<T>
	operator fun invoke(default: Short): ShortField<T>
	operator fun invoke(default: Int): IntField<T>
	operator fun invoke(default: Long): LongField<T>
	operator fun invoke(default: Float): FloatField<T>
	operator fun invoke(default: Double): DoubleField<T>
	operator fun invoke(default: Char): CharField<T>
	operator fun invoke(default: Boolean): BooleanField<T>
	
}