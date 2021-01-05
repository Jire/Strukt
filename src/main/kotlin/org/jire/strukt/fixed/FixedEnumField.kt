package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.EnumField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedEnumField<E : Enum<E>>(
	type: KClass<*>, strukts: Strukts,
	override val values: Array<E>,
	override val default: E
) : AbstractFixedField(type, strukts), EnumField<E> {
	
	override fun get(address: Long) = values[OS.memory().readInt(pointer(address))]
	
	override fun set(address: Long, value: E) = OS.memory().writeInt(pointer(address), value.ordinal)
	
}