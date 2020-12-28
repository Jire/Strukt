package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.IntField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedIntField<T : Strukt>(
	type: KClass<T>,
	allocator: Strukts<T>,
	val default: Int
) : AbstractPointedStruktField<T>(4, type, allocator), IntField<T> {
	
	override fun writeDefault(address: Long) = OS.memory().writeInt(address, default)
	
	override fun invoke(address: Long) = OS.memory().readInt(pointer(address))
	
	override fun invoke(address: Long, value: Int) = OS.memory().writeInt(pointer(address), value)
	
}