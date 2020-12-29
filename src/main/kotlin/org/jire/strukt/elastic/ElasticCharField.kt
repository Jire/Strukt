package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.CharField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticCharField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Char
) : AbstractElasticField<T>(type, strukts), CharField<T> {
	
	override fun invoke(address: Long) = OS.memory().readShort(pointer(address)).toChar()
	
	override fun invoke(address: Long, value: Char) = OS.memory().writeShort(pointer(address), value.toShort())
	
}