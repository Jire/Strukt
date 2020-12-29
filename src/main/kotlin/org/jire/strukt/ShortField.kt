package org.jire.strukt

interface ShortField<T : Strukt> : Field<T> {
	
	override val size get() = 2L
	
	val default: Short
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun get(address: Long): Short
	operator fun set(address: Long, value: Short)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Short) = set(address, value)
	
}