package org.jire.strukt.pointed

import it.unimi.dsi.fastutil.longs.LongOpenHashSet
import org.jire.strukt.AbstractStruktField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractPointedStruktField<T : Strukt>(
	size: Long,
	type: KClass<T>,
	allocator: Strukts<T>
) : AbstractStruktField<T>(size, type, allocator), PointedStruktField<T> {
	
	override val initializedAddresses = LongOpenHashSet()
	
}