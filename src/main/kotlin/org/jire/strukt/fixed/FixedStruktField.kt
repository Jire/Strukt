package org.jire.strukt.fixed

import org.jire.strukt.Strukt
import org.jire.strukt.StruktField

interface FixedStruktField<T : Strukt> : StruktField<T> {
	
	fun pointer(address: Long) = address + offset
	
}