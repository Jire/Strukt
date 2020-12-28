package org.jire.strukt

import kotlin.reflect.KClass

abstract class AbstractStruktField<T : Strukt>(
	override val type: KClass<T>,
	final override val strukts: Strukts<T>
) : StruktField<T> {
	
	override val index: Long = strukts.nextIndex
	override val offset: Long = strukts.size
	
	init {
		strukts.size += size
		strukts.nextIndex++
		//strukts.addressesSize += 8
		strukts.fields.add(this)
	}
	
}