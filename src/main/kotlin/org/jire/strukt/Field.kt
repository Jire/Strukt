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

import kotlin.reflect.KClass

interface Field {
	
	val size: Long
	val name: String
	val type: KClass<*>
	val strukts: Strukts
	val index: Long
	val offset: Long
	
	val threadSafeType: ThreadSafeType
	
	fun pointer(address: Long) = address + offset
	
	fun writeDefault(address: Long)
	
	fun getBoxed(address: Long): Any?
	
	fun setBoxed(address: Long, value: Any?)
	
	fun toString(address: Long) = getBoxed(address).toString()
	
}