package org.jire.strukt

import net.openhft.chronicle.core.OS

interface Strukt {
	
	val address: Long
	
	fun free() {
		val config = StruktConfig.map[this::class]!!
		for (field in config.fieldsArray) {
			field.free(address)
		}
		OS.memory().freeMemory(address, config.addressesSize)
	}
	
}

inline fun <reified T : Strukt> new(): Long {
	val config = StruktConfig.map[T::class]!!
	config.ready()
	return config.alloc()
}