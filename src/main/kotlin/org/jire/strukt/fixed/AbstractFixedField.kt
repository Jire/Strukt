package org.jire.strukt.fixed

import org.jire.strukt.AbstractField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractFixedField(
	type: KClass<*>,
	strukts: Strukts
) : AbstractField(type, strukts), FixedField