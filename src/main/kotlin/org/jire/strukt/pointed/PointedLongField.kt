package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.LongField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedLongField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>,
	val default: Long
) : AbstractPointedStruktField<T>(8, type, strukts), LongField<T> {
	
	override fun writeDefault(address: Long) = OS.memory().writeLong(address, default)
	
	override fun invoke(address: Long) = OS.memory().readLong(pointer(address))
	
	override fun invoke(address: Long, value: Long) = OS.memory().writeLong(pointer(address), value)
	
}