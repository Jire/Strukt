package org.jire.strukt

interface CharField<T : Strukt> : StruktField<T> {
	
	override val size get() = 2L
	
	val default: Char
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Char
	
	operator fun invoke(address: Long, value: Char)
	
}