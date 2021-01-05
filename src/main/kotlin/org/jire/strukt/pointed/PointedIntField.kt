package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.IntField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedIntField(
	type: KClass<*>,
	strukts: Strukts,
	override val default: Int
) : AbstractPointedField(type, strukts), IntField {
	
	override fun get(address: Long) = OS.memory().readInt(pointer(address))
	
	override fun set(address: Long, value: Int) = OS.memory().writeInt(pointer(address), value)
	
}