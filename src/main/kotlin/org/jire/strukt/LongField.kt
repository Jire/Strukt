package org.jire.strukt

interface LongField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Long
	
	operator fun invoke(address: Long, value: Long)
	
}