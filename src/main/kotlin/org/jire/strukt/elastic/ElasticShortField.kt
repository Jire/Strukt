package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.ShortField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticShortField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Short
) : AbstractElasticField<T>(type, strukts), ShortField<T> {
	
	override fun invoke(address: Long) = OS.memory().readShort(pointer(address))
	
	override fun invoke(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}