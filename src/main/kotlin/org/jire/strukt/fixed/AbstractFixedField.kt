package org.jire.strukt.fixed

import org.jire.strukt.AbstractField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractFixedField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>
) : AbstractField<T>(type, strukts), FixedField<T>