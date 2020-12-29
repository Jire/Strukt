package org.jire.strukt

interface CharField<T : Strukt> : Field<T> {
	
	override val size get() = 2L
	
	val default: Char
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Char)
	
	operator fun get(address: Long): Char
	operator fun set(address: Long, value: Char)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Char) = set(address, value)
	
}