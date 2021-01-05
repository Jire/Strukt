package org.jire.strukt.pointed

import org.jire.strukt.AbstractField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractPointedField(
	type: KClass<*>,
	strukts: Strukts
) : AbstractField(type, strukts), PointedField {
	
	override var defaultPointer = PointedField.UNSET_DEFAULT_POINTER
	
}