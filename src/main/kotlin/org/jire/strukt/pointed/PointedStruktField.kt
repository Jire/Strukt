package org.jire.strukt.pointed

import it.unimi.dsi.fastutil.longs.LongSet
import org.jire.strukt.Strukt
import org.jire.strukt.StruktField

interface PointedStruktField<T : Strukt> : StruktField<T> {
	
	val initializedAddresses: LongSet
	
	fun pointer(address: Long): Long {
		val pointer = address + offset
		val initialized = initializedAddresses.contains(address)
		if (!initialized) {
			writeDefault(pointer)
			initializedAddresses.add(address)
		}
		return pointer
	}
	
}