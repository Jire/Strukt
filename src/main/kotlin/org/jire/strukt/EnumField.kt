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

interface EnumField<E : Enum<E>> : Field {
	
	override val size get() = 4L
	
	val values: Array<E>
	val default: E
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun getBoxed(address: Long) = get(address)
	@Suppress("UNCHECKED_CAST")
	override fun setBoxed(address: Long, value: Any?) = set(address, value as E)
	
	operator fun get(address: Long): E
	operator fun set(address: Long, value: E)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: E) = set(address, value)
	
}