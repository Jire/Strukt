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
 * A [StruktMember] which delegates [Int]s.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param default The default value of this member.
 * @param size The size, in bytes, of the member's data within the [strukt]'s heap.
 */
class IntMember(override val strukt: Strukt, val default: Int, override val size: Long) : StruktMember() {
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = read()
	
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
		write(value)
	}
	
	private fun read() = with(strukt.heap) {
		when (size) {
			1L -> readByte(pointer()).toInt()
			2L -> readShort(pointer()).toInt()
			3L -> readUnsignedInt24(pointer())
			else -> readInt(pointer())
		}
	}
	
	private fun write(value: Int) {
		with(strukt.heap) {
			when (size) {
				1L -> writeByte(pointer(), value.toByte())
				2L -> writeShort(pointer(), value.toShort())
				3L -> writeInt24(pointer(), value)
				else -> writeInt(pointer(), value)
			}
		}
	}
	
	override fun setup() {
		offset = strukt.heapPointer
		write(default)
		strukt.heapPointer += size
		
		strukt.members.add(this)
	}
	
}

/**
 * Creates a [IntMember] with the default value and size.
 *
 * @param defaultValue The default value for the new member.
 * @param size The size, in bytes, of the member's data within the [Strukt]'s heap.
 */
fun Strukt.int(defaultValue: Int = 0, size: Long = 4)
		= IntMember(this, defaultValue, size).apply(StruktMember::setup)