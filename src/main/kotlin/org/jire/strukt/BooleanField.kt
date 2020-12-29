package org.jire.strukt

interface BooleanField<T : Strukt> : Field<T> {
	
	override val size get() = 1L
	
	val default: Boolean
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Boolean
	
	operator fun invoke(address: Long, value: Boolean)
	
}