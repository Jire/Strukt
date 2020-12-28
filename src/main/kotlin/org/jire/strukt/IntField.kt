package org.jire.strukt

interface IntField<T : Strukt> : StruktField<T> {
	
	override val size get() = 4L
	
	val default: Int
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Int
	
	operator fun invoke(address: Long, value: Int)
	
}