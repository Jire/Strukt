package org.jire.strukt

interface DoubleField<T : Strukt> : Field<T> {
	
	override val size get() = 8L
	
	val default: Double
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Double
	
	operator fun invoke(address: Long, value: Double)
	
}