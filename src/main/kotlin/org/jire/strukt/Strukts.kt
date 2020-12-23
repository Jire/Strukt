package org.jire.strukt

import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap

object Strukts {
	
	const val ID_OFFSET = 4L
	const val SIZE_OFFSET = ID_OFFSET + 4L
	
	var nextID = 0
	
	val idToStrukt: Int2ObjectMap<Strukt> = Int2ObjectOpenHashMap()
	
}