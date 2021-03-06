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

interface ByteField : Field {
	
	override val size get() = 1L
	
	val default: Byte
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	override fun setBoxed(address: Long, value: Any?) = set(address, value as Byte)
	
	operator fun get(address: Long) = threadSafeType.run { readByte(pointer(address)) }
	operator fun set(address: Long, value: Byte) = threadSafeType.run { writeByte(pointer(address), value) }
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: Byte) = set(address, value)
	
}