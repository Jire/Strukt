package org.jire.strukt.pointed

import org.jire.strukt.Strukt
import org.jire.strukt.StruktField

interface PointedStruktField<T : Strukt> : StruktField<T> {
	
	var defaultPointer: Long
	
	fun pointer(address: Long): Long {
		val pointer = address + offset
		if (defaultPointer == UNSET_DEFAULT_POINTER) {
			defaultPointer = pointer
			writeDefault(defaultPointer)
			return defaultPointer
		}
		return pointer
	}
	
	companion object {
		internal const val UNSET_DEFAULT_POINTER = -1L
	}
	
}