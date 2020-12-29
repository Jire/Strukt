package org.jire.strukt

import kotlin.reflect.KClass

abstract class AbstractField<T : Strukt>(
	override val type: KClass<T>,
	final override val strukts: Strukts<T>
) : Field<T> {
	
	override val index: Long = strukts.nextIndex
	override val offset: Long = strukts.size
	
	init {
		strukts.addField(this)
	}
	
}