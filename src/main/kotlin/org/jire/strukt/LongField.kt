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

interface LongField : Field {
	
	override val size get() = 8L
	
	val default: Long
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Long)
	
	operator fun get(address: Long): Long {
		val pointer = pointer(address)
		return when (threadSafeType) {
			ThreadSafeType.VOLATILE -> OS.memory().readVolatileLong(pointer)
			ThreadSafeType.SYNCHRONIZED -> synchronized(this) { OS.memory().readLong(pointer) }
			else -> OS.memory().readLong(pointer)
		}
	}
	
	operator fun set(address: Long, value: Long) {
		val pointer = pointer(address)
		when (threadSafeType) {
			ThreadSafeType.VOLATILE -> OS.memory().writeVolatileLong(pointer, value)
			ThreadSafeType.SYNCHRONIZED -> synchronized(this) { OS.memory().writeLong(pointer, value) }
			else -> OS.memory().writeLong(pointer, value)
		}
	}
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Long) = set(address, value)
	
}