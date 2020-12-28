package org.jire.strukt

interface ByteField<T : Strukt> : StruktField<T> {
	
	override val size get() = 1L
	
	val default: Byte
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Byte
	
	operator fun invoke(address: Long, value: Byte)
	
}