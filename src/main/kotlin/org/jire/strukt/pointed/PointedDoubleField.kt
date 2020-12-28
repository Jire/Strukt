package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.DoubleField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedDoubleField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>,
	val default: Double
) : AbstractPointedStruktField<T>(8, type, strukts), DoubleField<T> {
	
	override fun writeDefault(address: Long) = OS.memory().writeDouble(address, default)
	
	override fun invoke(address: Long) = OS.memory().readDouble(pointer(address))
	
	override fun invoke(address: Long, value: Double) = OS.memory().writeDouble(pointer(address), value)
	
}