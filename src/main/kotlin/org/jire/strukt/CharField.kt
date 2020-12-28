package org.jire.strukt

interface CharField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Char
	
	operator fun invoke(address: Long, value: Char)
	
}