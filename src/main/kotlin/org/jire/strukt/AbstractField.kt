package org.jire.strukt

import kotlin.reflect.KClass

abstract class AbstractField(
	override val type: KClass<*>,
	final override val strukts: Strukts
) : Field {
	
	override val index: Long = strukts.nextIndex
	override val offset: Long = strukts.size
	
	init {
		strukts.addField(this)
	}
	
	override val name by lazy(LazyThreadSafetyMode.NONE) {
		javaClass.simpleName
	}
	
}