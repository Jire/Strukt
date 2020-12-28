package org.jire.strukt

interface Strukt {
	
	val address: Long
	
	fun free(strukts: Strukts<*>) = strukts.free(address)
	
}