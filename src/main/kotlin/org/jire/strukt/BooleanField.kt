package org.jire.strukt

interface BooleanField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Boolean
	
	operator fun invoke(address: Long, value: Boolean)
	
}