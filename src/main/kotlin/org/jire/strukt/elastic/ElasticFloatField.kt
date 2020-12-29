package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.FloatField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticFloatField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Float
) : AbstractElasticField<T>(type, strukts), FloatField<T> {
	
	override fun get(address: Long) = OS.memory().readFloat(pointer(address))
	
	override fun set(address: Long, value: Float) = OS.memory().writeFloat(pointer(address), value)
	
}