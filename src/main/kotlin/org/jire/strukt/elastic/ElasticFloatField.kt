package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.FloatField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticFloatField(
	type: KClass<*>, strukts: Strukts,
	override val default: Float
) : AbstractElasticField(type, strukts), FloatField {
	
	override fun get(address: Long) = OS.memory().readFloat(pointer(address))
	
	override fun set(address: Long, value: Float) = OS.memory().writeFloat(pointer(address), value)
	
}