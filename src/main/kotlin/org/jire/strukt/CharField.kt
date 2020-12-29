package org.jire.strukt

interface CharField<T : Strukt> : Field<T> {
	
	override val size get() = 2L
	
	val default: Char
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun get(address: Long): Char
	operator fun set(address: Long, value: Char)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Char) = set(address, value)
	
}