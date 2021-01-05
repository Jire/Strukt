package org.jire.strukt

import kotlin.reflect.KClass

interface Field {
	
	val size: Long
	val name: String
	val type: KClass<*>
	val strukts: Strukts
	val index: Long
	val offset: Long
	
	fun pointer(address: Long) = address + offset
	
	fun writeDefault(address: Long)
	
	fun getBoxed(address: Long): Any?
	
	fun setBoxed(address: Long, value: Any?)
	
	fun toString(address: Long) = getBoxed(address).toString()
	
}