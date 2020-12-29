package org.jire.strukt

import kotlin.reflect.KClass

interface Field<T : Strukt> {
	
	val size: Long
	val name: String
	val type: KClass<T>
	val strukts: Strukts<T>
	val index: Long
	val offset: Long
	
	fun writeDefault(address: Long)
	
	fun getBoxed(address: Long): Any?
	
	fun setBoxed(address: Long, value: Any?)
	
	fun toString(address: Long) = getBoxed(address).toString()
	
}