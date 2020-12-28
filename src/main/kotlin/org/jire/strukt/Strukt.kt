package org.jire.strukt

interface Strukt {
	
	val address: Long
	
	fun free(allocator: Strukts<*>) = allocator.free(address)
	
}