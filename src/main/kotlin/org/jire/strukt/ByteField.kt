package org.jire.strukt

interface ByteField<T : Strukt> : StruktField<T> {
	
	operator fun invoke(address: Long): Byte
	
	operator fun invoke(address: Long, value: Byte)
	
}