package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.DoubleField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticDoubleField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Double
) : AbstractElasticField<T>(type, strukts), DoubleField<T> {
	
	override fun invoke(address: Long) = OS.memory().readDouble(pointer(address))
	
	override fun invoke(address: Long, value: Double) = OS.memory().writeDouble(pointer(address), value)
	
}