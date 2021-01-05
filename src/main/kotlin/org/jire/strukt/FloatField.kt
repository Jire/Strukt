package org.jire.strukt

interface FloatField : Field {
	
	override val size get() = 4L
	
	val default: Float
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Float)
	
	operator fun get(address: Long): Float
	operator fun set(address: Long, value: Float)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Float) = set(address, value)
	
}