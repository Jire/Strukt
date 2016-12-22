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

package org.jire.strukt.members

import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

/**
 * A [StruktMember] which delegates [Long]s.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param default The default value of this member.
 * @param size The size, in bytes, of the member's data within the [strukt]'s heap.
 */
class LongMember(override val strukt: Strukt, val default: Long, override val size: Long) : StruktMember() {
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = strukt.heap.readLong(pointer())
	
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
		strukt.heap.writeLong(pointer(), value)
	}
	
	init {
		offset = strukt.heapPointer
		strukt.heap.writeLong(pointer(), default)
		strukt.heapPointer += size
	}
	
}

/**
 * Creates a [LongMember] with the default value and size.
 *
 * @param defaultValue The default value for the new member.
 * @param size The size, in bytes, of the member's data within the [Strukt]'s heap.
 */
fun Strukt.long(defaultValue: Long = 0, size: Long = 8) = LongMember(this, defaultValue, size)