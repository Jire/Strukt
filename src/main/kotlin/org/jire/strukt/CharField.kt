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

interface CharField : Field {
	
	override val size get() = 2L
	
	val default: Char
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Char)
	
	operator fun get(address: Long) = threadSafeType.run { readChar(pointer(address)) }
	operator fun set(address: Long, value: Char) = threadSafeType.run { writeChar(pointer(address), value) }
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Char) = set(address, value)
	
}