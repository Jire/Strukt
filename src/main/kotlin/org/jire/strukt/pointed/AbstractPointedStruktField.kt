package org.jire.strukt.pointed

import org.jire.strukt.AbstractStruktField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractPointedStruktField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>
) : AbstractStruktField<T>(type, strukts), PointedStruktField<T> {
	
	override var defaultPointer = PointedStruktField.UNSET_DEFAULT_POINTER
	
}