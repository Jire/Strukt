package org.jire.strukt.pointed

import org.jire.strukt.Field

interface PointedField : Field {
	
	var defaultPointer: Long
	
	override fun pointer(address: Long): Long {
		val pointer = super.pointer(address)
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