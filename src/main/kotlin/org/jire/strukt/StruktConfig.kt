package org.jire.strukt

import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class StruktConfig(val type: KClass<*>) {
	
	var size = 0L
	var nextIndex = 0
	var addressesSize = 0L
	
	val fields: ObjectList<StruktField<*>> = ObjectArrayList()
	var defaultAddress = -1L
	lateinit var fieldsArray: Array<StruktField<*>>
	
	fun init() {
		if (defaultAddress == -1L) {
			fieldsArray = fields.toTypedArray()
			defaultAddress = alloc(true)
		}
	}
	
	fun alloc(default: Boolean = false): Long {
		var address = OS.memory().allocate(size)
		if (default) {
			defaultAddress = address
			for (field in fieldsArray) {
				field.pointer(address)
			}
			
			address = OS.memory().allocate(size)
		}
		OS.memory().copyMemory(defaultAddress, address, size)
		return address
	}
	
	companion object {
		val map: Object2ObjectMap<KClass<*>, StruktConfig> = Object2ObjectOpenHashMap()
	}
	
}