package org.jire.strukt

import kotlin.reflect.KClass

abstract class AbstractStruktField<T : Strukt>(
	final override val size: Long,
	override val type: KClass<T>,
	final override val allocator: Strukts<T>
) : StruktField<T> {
	
	override val index: Long = allocator.nextIndex
	override val offset: Long = allocator.size
	
	init {
		allocator.size += size
		allocator.nextIndex++
		//allocator.addressesSize += 8
		allocator.fields.add(this)
	}
	
}