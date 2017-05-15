package org.jire.strukt.member

import org.jire.strukt.NIL
import org.jire.strukt.Strukt
import org.jire.strukt.get
import org.jire.strukt.invoke
import kotlin.reflect.KProperty

/**
 * A member of a [Strukt], which serves as a delegate for a single instance of the [value].
 *
 * @param value The [Strukt] within this [Strukt].
 * @param initializer The "constructor" block for the [value].
 */
class StruktWithinStruktMember<T : Strukt>(strukt: Strukt, val value: T,
                                           val initializer: T.() -> Unit)
	: StruktMember(strukt, value.size) {
	
	private var valuePointer = NIL
	
	init {
		offset = strukt.internalPointer
		strukt.internalPointer += size
		strukt.members.add(this)
	}
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = value[valuePointer]
	
	override fun writeDefault() {
		valuePointer = value(initializer)
	}
	
}

/**
 * Creates a [StruktWithinStruktMember].
 *
 * @param T The type of [Strukt] within.
 * @param strukt The [Strukt] within this [Strukt].
 * @param initializer The "constructor" block for the [strukt].
 */
fun <T : Strukt> Strukt.strukt(strukt: T, initializer: T.() -> Unit = {})
		= StruktWithinStruktMember(this, strukt, initializer)