package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ShortField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedShortField(
	type: KClass<*>, strukts: Strukts,
	override val default: Short
) : AbstractFixedField(type, strukts), ShortField {
	
	override fun get(address: Long) = OS.memory().readShort(pointer(address))
	
	override fun set(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}