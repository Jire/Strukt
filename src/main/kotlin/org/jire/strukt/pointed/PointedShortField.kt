package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ShortField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedShortField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>,
	val default: Short
) : AbstractPointedStruktField<T>(2, type, strukts), ShortField<T> {
	
	override fun writeDefault(address: Long) = OS.memory().writeShort(address, default)
	
	override fun invoke(address: Long) = OS.memory().readShort(pointer(address))
	
	override fun invoke(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}