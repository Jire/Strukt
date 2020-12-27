package org.jire.strukt

import it.unimi.dsi.fastutil.longs.Long2BooleanMap
import it.unimi.dsi.fastutil.longs.Long2BooleanOpenHashMap
import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

abstract class StruktField<T : Strukt>(
	val size: Int,
	val type: KClass<T>
) {
	
	val index: Int
	val offset: Int
	
	val addressMap: Long2BooleanMap = Long2BooleanOpenHashMap()
	
	abstract fun writeDefault(address: Long)
	
	fun pointer(address: Long, skipMapped: Boolean = false): Long {
		val pointer = address + offset
		if (!skipMapped && !addressMap.get(address)) {
			val pointerAddress = OS.memory().allocate(size.toLong())
			writeDefault(pointerAddress)
			
			OS.memory().writeLong(pointer, pointerAddress)
			
			addressMap.put(address, true)
		}
		return OS.memory().readLong(pointer)
	}
	
	fun free(address: Long) {
		val fieldPointer = pointer(address, true)
		OS.memory().freeMemory(fieldPointer, size.toLong())
		addressMap.remove(address)
	}
	
	init {
		val config = StruktConfig.map[type] ?: StruktConfig(type).apply { StruktConfig.map[type] = this }
		
		index = config.nextIndex
		offset = index * 8
		
		config.size += size
		config.nextIndex++
		config.addressesSize += 8
		config.fields.add(this)
	}
	
}