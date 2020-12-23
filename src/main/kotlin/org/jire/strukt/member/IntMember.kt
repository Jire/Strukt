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

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

/**
 * A [StruktMember] which delegates [Int]s.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param defaultValue The default value of this member.
 */
class IntMember(strukt: Strukt, val defaultValue: Int) : StruktMember(strukt, 4) {
	
	init {
		offset = strukt.internalPointer
		strukt.internalPointer += size
		strukt.members.add(this)
	}
	
	/**
	 * Gets the value of this [IntMember].
	 */
	fun get() = OS.memory().readInt(pointer())
	
	/**
	 * Sets the value of this [IntMember] to the specified value.
	 *
	 * @param value The new value.
	 */
	fun set(value: Int) = OS.memory().writeInt(pointer(), value)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) = set(value)
	
	override fun writeDefault() = set(defaultValue)
	
	override fun toString(): String = Integer.toString(get())
	
}