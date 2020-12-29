package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.EnumField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedEnumField<T : Strukt, E : Enum<E>>(
	type: KClass<T>, strukts: Strukts<T>,
	override val values: Array<E>,
	override val default: E
) : AbstractFixedField<T>(type, strukts), EnumField<T, E> {
	
	override fun get(address: Long) = values[OS.memory().readInt(pointer(address))]
	
	override fun set(address: Long, value: E) = OS.memory().writeInt(pointer(address), value.ordinal)
	
}