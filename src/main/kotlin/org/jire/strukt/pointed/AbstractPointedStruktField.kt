package org.jire.strukt.pointed

import it.unimi.dsi.fastutil.longs.LongOpenHashSet
import org.jire.strukt.AbstractStruktField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractPointedStruktField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>
) : AbstractStruktField<T>(type, strukts), PointedStruktField<T> {
	
	override val initializedAddresses = LongOpenHashSet()
	
}