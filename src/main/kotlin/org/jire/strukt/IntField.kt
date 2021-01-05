/*
 *    Copyright 2020 Thomas Nappo (Jire)
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
 */

package org.jire.strukt

import net.openhft.chronicle.core.OS

interface IntField : Field {
	
	override val size get() = 4L
	
	val default: Int
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Int)
	
	operator fun get(address: Long): Int {
		val pointer = pointer(address)
		return when (threadSafeType) {
			ThreadSafeType.VOLATILE -> OS.memory().readVolatileInt(pointer)
			ThreadSafeType.SYNCHRONIZED -> synchronized(this) { OS.memory().readInt(pointer) }
			else -> OS.memory().readInt(pointer)
		}
	}
	
	operator fun set(address: Long, value: Int) {
		val pointer = pointer(address)
		when (threadSafeType) {
			ThreadSafeType.VOLATILE -> OS.memory().writeVolatileInt(pointer, value)
			ThreadSafeType.SYNCHRONIZED -> synchronized(this) { OS.memory().writeInt(pointer, value) }
			else -> OS.memory().writeInt(pointer, value)
		}
	}
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Int) = set(address, value)
	
}