package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.BooleanField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticBooleanField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Boolean
) : AbstractElasticField<T>(type, strukts), BooleanField<T> {
	
	override fun get(address: Long) = OS.memory().readByte(pointer(address)).toInt() != 0
	
	override fun set(address: Long, value: Boolean) = OS.memory().writeByte(pointer(address), if (value) 1 else 0)
	
}