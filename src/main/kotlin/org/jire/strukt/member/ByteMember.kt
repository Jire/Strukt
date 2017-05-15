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

import org.jire.strukt.Strukt
import org.jire.strukt.UNSAFE
import kotlin.reflect.KProperty

/**
 * A [StruktMember] which delegates [Byte]s.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param defaultValue The default value of this member.
 */
class ByteMember(strukt: Strukt, val defaultValue: Byte) : StruktMember(strukt, 1) {
	
	init {
		offset = strukt.internalPointer
		strukt.internalPointer += size
		strukt.members.add(this)
	}
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = UNSAFE.getByte(pointer())
	
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Byte) = write(value)
	
	override fun writeDefault() = write(defaultValue)
	
	private inline fun write(value: Byte) = UNSAFE.putByte(pointer(), value)
	
}

/**
 * Creates a [ByteMember].
 *
 * @param defaultValue The default value for the new member.
 */
fun Strukt.byte(defaultValue: Byte = 0) = ByteMember(this, defaultValue)