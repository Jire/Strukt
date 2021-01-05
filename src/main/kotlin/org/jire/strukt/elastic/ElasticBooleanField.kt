package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.BooleanField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticBooleanField(
	type: KClass<*>, strukts: Strukts,
	override val default: Boolean
) : AbstractElasticField(type, strukts), BooleanField {
	
	override fun get(address: Long) = OS.memory().readByte(pointer(address)).toInt() != 0
	
	override fun set(address: Long, value: Boolean) = OS.memory().writeByte(pointer(address), if (value) 1 else 0)
	
}