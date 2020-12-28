package org.jire.strukt

interface FloatField<T : Strukt> : StruktField<T> {
	
	override val size get() = 4L
	
	val default: Float
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Float
	
	operator fun invoke(address: Long, value: Float)
	
}