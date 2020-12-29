package org.jire.strukt

interface FloatField<T : Strukt> : Field<T> {
	
	override val size get() = 4L
	
	val default: Float
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun get(address: Long): Float
	operator fun set(address: Long, value: Float)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Float) = set(address, value)
	
}