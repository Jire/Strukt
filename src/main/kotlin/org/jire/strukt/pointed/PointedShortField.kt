package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ShortField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedShortField(
	type: KClass<*>,
	strukts: Strukts,
	override val default: Short
) : AbstractPointedField(type, strukts), ShortField {
	
	override fun get(address: Long) = OS.memory().readShort(pointer(address))
	
	override fun set(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}