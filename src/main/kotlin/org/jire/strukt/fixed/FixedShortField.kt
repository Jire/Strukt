package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ShortField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedShortField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Short
) : AbstractFixedField<T>(type, strukts), ShortField<T> {
	
	override fun get(address: Long) = OS.memory().readShort(pointer(address))
	
	override fun set(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}