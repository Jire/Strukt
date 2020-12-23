@file:JvmName("Allocate")

package org.jire.strukt

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukts.ID_OFFSET
import org.jire.strukt.Strukts.SIZE_OFFSET

inline operator fun <reified T : Strukt> T.invoke(setCursor: Boolean = true): StruktReference<T> {
	val mem = OS.memory()
	
	val size = offset
	val blockSize = size + SIZE_OFFSET
	var address = mem.allocate(blockSize)
	
	if (default == -1L) {
		default = address
		
		mem.writeInt(address, id)
		mem.writeInt(address + ID_OFFSET, size)
		
		for (field in fields) {
			val pointer = address + field.offset + SIZE_OFFSET
			field.writeDefault(pointer)
		}
		
		address = mem.allocate(blockSize)
	}
	
	mem.copyMemory(default, address, blockSize)
	
	if (setCursor) cursor = address
	
	return StruktReference(address)
}

inline operator fun <reified T : Strukt> T.invoke(init: T.() -> Unit): StruktReference<T> {
	val pointer = invoke()
	init()
	return pointer
}