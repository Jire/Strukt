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
 * A [StruktMember] which delegates [Float]s.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param default The default value of this member.
 * @param size The size, in bytes, of the member's data within the [strukt]'s heap.
 */
class FloatMember(override val strukt: Strukt, val default: Float, override val size: Long) : StruktMember() {
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = strukt.heap.readFloat(pointer())
	
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
		strukt.heap.writeFloat(pointer(), value)
	}
	
	override fun setup() {
		offset = strukt.heapPointer
		strukt.heap.writeFloat(pointer(), default)
		strukt.heapPointer += size
		
		strukt.members.add(this)
	}
	
}

/**
 * Creates a [FloatMember] with the default value and size.
 *
 * @param defaultValue The default value for the new member.
 * @param size The size, in bytes, of the member's data within the [Strukt]'s heap.
 */
fun Strukt.float(defaultValue: Float = 0F, size: Long = 4)
		= FloatMember(this, defaultValue, size).apply(StruktMember::setup)