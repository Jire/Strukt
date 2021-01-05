package org.jire.strukt

interface ByteField : Field {
	
	override val size get() = 1L
	
	val default: Byte
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Byte)
	
	operator fun get(address: Long): Byte
	operator fun set(address: Long, value: Byte)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Byte) = set(address, value)
	
}