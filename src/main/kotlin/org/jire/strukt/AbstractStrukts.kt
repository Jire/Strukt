package org.jire.strukt

import it.unimi.dsi.fastutil.objects.ObjectArrayList

abstract class AbstractStrukts<T : Strukt> : Strukts<T> {
	
	override var size = 0L
	override var nextIndex = 0L
	
	override val fields = ObjectArrayList<Field<T>>()
	
}