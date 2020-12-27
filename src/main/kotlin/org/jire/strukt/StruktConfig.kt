package org.jire.strukt

import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class StruktConfig(val type: KClass<*>) {
	
	@Volatile
	var defaultAddress: Long = -1L
	
	var size = 0
	var nextIndex = 0
	var addressesSize = 0L
	
	val fields: ObjectList<StruktField<*>> = ObjectArrayList()
	lateinit var fieldsArray: Array<StruktField<*>>
	
	fun ready() {
		if (defaultAddress == -1L) {
			fieldsArray = fields.toTypedArray()
			defaultAddress = alloc(true)
		}
	}
	
	fun alloc(writeDefault: Boolean = false): Long {
		val address = OS.memory().allocate(addressesSize)
		for (field in fieldsArray) {
			field.pointer(address)
			if (writeDefault) {
				field.writeDefault(address)
			}
		}
		return address
	}
	
	companion object {
		val map: Object2ObjectMap<KClass<*>, StruktConfig> = Object2ObjectOpenHashMap()
	}
	
}