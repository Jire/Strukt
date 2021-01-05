package org.jire.strukt

import it.unimi.dsi.fastutil.objects.ObjectArrayList
import kotlin.reflect.KClass

abstract class AbstractStrukts(override val type: KClass<*>) : Strukts {
	
	override var size = 0L
	override var nextIndex = 0L
	
	override val fields = ObjectArrayList<Field>()
	
	override fun addField(field: Field) {
		size += field.size
		nextIndex++
		fields.add(field)
	}
	
	override fun toString(address: Long) = fields.joinToString(", ") { "${it.name}(${it.getBoxed(address)})" }
	
}