package org.jire.strukt

import it.unimi.dsi.fastutil.longs.LongOpenHashSet
import it.unimi.dsi.fastutil.longs.LongSet
import kotlin.reflect.KClass

abstract class StruktField<T : Strukt>(
	val size: Long,
	val type: KClass<T>
) {
	
	val index: Int
	val offset: Long
	
	val initializedAddresses: LongSet = LongOpenHashSet()
	
	abstract fun writeDefault(address: Long)
	
	fun pointer(address: Long): Long {
		val pointer = address + offset
		val initialized = initializedAddresses.contains(address)
		if (!initialized) {
			writeDefault(pointer)
			initializedAddresses.add(address)
		}
		return pointer
	}
	
	init {
		val config = StruktConfig.map[type] ?: StruktConfig(type).apply { StruktConfig.map[type] = this }
		
		index = config.nextIndex
		offset = config.size
		
		config.size += size
		config.nextIndex++
		config.addressesSize += 8
		config.fields.add(this)
	}
	
}