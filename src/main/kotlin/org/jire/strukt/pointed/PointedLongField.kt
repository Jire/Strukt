package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.LongField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedLongField(
	type: KClass<*>,
	strukts: Strukts,
	override val default: Long
) : AbstractPointedField(type, strukts), LongField {
	
	override fun get(address: Long) = OS.memory().readLong(pointer(address))
	
	override fun set(address: Long, value: Long) = OS.memory().writeLong(pointer(address), value)
	
}