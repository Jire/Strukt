package org.jire.strukt

import net.openhft.chronicle.core.OS

interface Strukt {
	
	val address: Long
	
	fun free() {
		val config = StruktConfig.map[this::class]!!
		OS.memory().freeMemory(address, config.size)
	}
	
}

inline fun <reified T : Strukt> new(): Long {
	val config = StruktConfig.map[T::class]!!
	config.init()
	return config.alloc()
}