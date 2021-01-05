package org.jire.strukt

interface IntField : Field {
	
	override val size get() = 4L
	
	val default: Int
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Int)
	
	operator fun get(address: Long): Int
	operator fun set(address: Long, value: Int)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Int) = set(address, value)
	
}