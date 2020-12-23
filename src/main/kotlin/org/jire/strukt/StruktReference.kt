package org.jire.strukt

import net.openhft.chronicle.core.OS

inline class StruktReference<T : Strukt>(val address: Long) {
	
	fun struktID() = OS.memory().readInt(address)
	
	fun size() = OS.memory().readInt(address)
	
	fun free(size: Int = size()) = OS.memory().freeMemory(address, size + Strukts.SIZE_OFFSET)
	
	operator fun invoke(): T {
		val struktID = struktID()
		val strukt = Strukts.idToStrukt[struktID]
		strukt.cursor = address
		
		@Suppress("UNCHECKED_CAST")
		return strukt as T
	}
	
}