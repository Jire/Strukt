/*
 *    Copyright 2016-2017 Thomas G. Nappo (Jire)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

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