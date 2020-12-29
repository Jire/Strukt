package org.jire.strukt.pointed

import org.jire.strukt.AbstractField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractPointedField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>
) : AbstractField<T>(type, strukts), PointedField<T> {
	
	override var defaultPointer = PointedField.UNSET_DEFAULT_POINTER
	
}