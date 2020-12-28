package org.jire.strukt

interface FloatField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Float
	
	operator fun invoke(address: Long, value: Float)
	
}