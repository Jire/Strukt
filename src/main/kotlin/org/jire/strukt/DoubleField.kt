package org.jire.strukt

interface DoubleField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Double
	
	operator fun invoke(address: Long, value: Double)
	
}