package org.jire.strukt

interface ShortField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Short
	
	operator fun invoke(address: Long, value: Short)
	
}