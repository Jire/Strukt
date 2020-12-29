package org.jire.strukt

interface IntField<T : Strukt> : Field<T> {
	
	override val size get() = 4L
	
	val default: Int
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun get(address: Long): Int
	operator fun set(address: Long, value: Int)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Int) = set(address, value)
	
}