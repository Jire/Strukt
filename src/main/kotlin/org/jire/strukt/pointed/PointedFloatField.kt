package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.FloatField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedFloatField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>,
	val default: Float
) : AbstractPointedStruktField<T>(4, type, strukts), FloatField<T> {
	
	override fun writeDefault(address: Long) = OS.memory().writeFloat(address, default)
	
	override fun invoke(address: Long) = OS.memory().readFloat(pointer(address))
	
	override fun invoke(address: Long, value: Float) = OS.memory().writeFloat(pointer(address), value)
	
}