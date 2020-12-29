package org.jire.strukt.fixed

import org.jire.strukt.Field
import org.jire.strukt.Strukt

interface FixedField<T : Strukt> : Field<T> {
	
	fun pointer(address: Long) = address + offset
	
}