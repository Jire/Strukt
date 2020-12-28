package org.jire.strukt

import kotlin.reflect.KClass

interface StruktField<T : Strukt> {
	
	val size: Long
	val type: KClass<T>
	val allocator: Strukts<T>
	val index: Long
	val offset: Long
	
	fun writeDefault(address: Long)
	
}