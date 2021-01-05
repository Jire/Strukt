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

interface BooleanField : Field {
	
	override val size get() = 1L
	
	val default: Boolean
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Boolean)
	
	operator fun get(address: Long): Boolean {
		val pointer = pointer(address)
		return (when (threadSafeType) {
			ThreadSafeType.VOLATILE -> OS.memory().readVolatileByte(pointer)
			ThreadSafeType.SYNCHRONIZED -> synchronized(this) { OS.memory().readByte(pointer) }
			else -> OS.memory().readByte(pointer)
		}.toInt()) != 0
	}
	
	operator fun set(address: Long, value: Boolean) {
		val pointer = pointer(address)
		val byteValue: Byte = if (value) 1 else 0
		when (threadSafeType) {
			ThreadSafeType.VOLATILE -> OS.memory().writeVolatileByte(pointer, byteValue)
			ThreadSafeType.SYNCHRONIZED -> synchronized(this) { OS.memory().writeByte(pointer, byteValue) }
			else -> OS.memory().writeByte(pointer, byteValue)
		}
	}
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Boolean) = set(address, value)
	
}