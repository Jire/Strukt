package org.jire.strukt

interface ShortField<T : Strukt> : Field<T> {
	
	override val size get() = 2L
	
	val default: Short
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Short
	
	operator fun invoke(address: Long, value: Short)
	
}