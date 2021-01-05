package org.jire.strukt

interface DoubleField : Field {
	
	override val size get() = 8L
	
	val default: Double
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Double)
	
	operator fun get(address: Long): Double
	operator fun set(address: Long, value: Double)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Double) = set(address, value)
	
}