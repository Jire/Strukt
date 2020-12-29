package org.jire.strukt.elastic

import org.jire.strukt.Field
import org.jire.strukt.Strukt

interface ElasticField<T : Strukt> : Field<T> {
	
	fun pointer(address: Long) = address + offset
	
}