package org.jire.strukt.fixed

import org.jire.strukt.AbstractStruktField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractFixedStruktField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>
) : AbstractStruktField<T>(type, strukts), FixedStruktField<T>