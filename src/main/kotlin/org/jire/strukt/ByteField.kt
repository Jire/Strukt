package org.jire.strukt

interface ByteField<T : Strukt> : Field<T> {
	
	override val size get() = 1L
	
	val default: Byte
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun get(address: Long): Byte
	operator fun set(address: Long, value: Byte)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Byte) = set(address, value)
	
}