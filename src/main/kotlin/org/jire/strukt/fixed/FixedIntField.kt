package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.IntField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedIntField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Int
) : AbstractFixedStruktField<T>(type, strukts), IntField<T> {
	
	override fun invoke(address: Long) = OS.memory().readInt(pointer(address))
	
	override fun invoke(address: Long, value: Int) = OS.memory().writeInt(pointer(address), value)
	
}