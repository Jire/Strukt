package org.jire.strukt

interface LongField : Field {
	
	override val size get() = 8L
	
	val default: Long
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Long)
	
	operator fun get(address: Long): Long
	operator fun set(address: Long, value: Long)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Long) = set(address, value)
	
}