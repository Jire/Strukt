package org.jire.strukt

interface IntField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Int
	
	operator fun invoke(address: Long, value: Int)
	
}