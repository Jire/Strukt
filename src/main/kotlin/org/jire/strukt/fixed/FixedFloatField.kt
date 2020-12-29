package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.FloatField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedFloatField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Float
) : AbstractFixedStruktField<T>(type, strukts), FloatField<T> {
	
	override fun invoke(address: Long) = OS.memory().readFloat(pointer(address))
	
	override fun invoke(address: Long, value: Float) = OS.memory().writeFloat(pointer(address), value)
	
}