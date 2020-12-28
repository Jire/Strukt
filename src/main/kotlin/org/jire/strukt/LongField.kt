package org.jire.strukt

interface LongField<T : Strukt> : StruktField<T> {
	
	override val size get() = 8L
	
	val default: Long
	
	override fun writeDefault(address: Long) = invoke(address, default)
	
	operator fun invoke(address: Long): Long
	
	operator fun invoke(address: Long, value: Long)
	
}