package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ShortField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedShortField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Short
) : AbstractFixedStruktField<T>(type, strukts), ShortField<T> {
	
	override fun invoke(address: Long) = OS.memory().readShort(pointer(address))
	
	override fun invoke(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}