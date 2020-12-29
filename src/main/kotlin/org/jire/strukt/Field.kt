package org.jire.strukt

import kotlin.reflect.KClass

interface Field<T : Strukt> {
	
	val size: Long
	val type: KClass<T>
	val strukts: Strukts<T>
	val index: Long
	val offset: Long
	
	fun writeDefault(address: Long)
	
}