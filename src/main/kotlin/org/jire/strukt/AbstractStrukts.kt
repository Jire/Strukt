package org.jire.strukt

import it.unimi.dsi.fastutil.objects.ObjectArrayList
import kotlin.reflect.full.declaredMembers

abstract class AbstractStrukts<T : Strukt> : Strukts<T> {
	
	override var size = 0L
	override var nextIndex = 0L
	
	override val fields = ObjectArrayList<Field<T>>()
	
	override fun addField(field: Field<T>) {
		size += field.size
		nextIndex++
		fields.add(field)
	}
	
	abstract fun allocateDirect(): Long
	
	fun initializeFields() {
		for (member in this::class.declaredMembers) {
			member.call(this)
		}
	}
	
	override fun allocate(): Long {
		if (size == 0L) initializeFields()
		return allocateDirect()
	}
	
	override fun toString(address: Long) = fields.joinToString(", ") { "${it.name}(${it.getBoxed(address)})" }
	
}