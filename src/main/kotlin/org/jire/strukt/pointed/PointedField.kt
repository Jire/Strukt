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

package org.jire.strukt.pointed

import org.jire.strukt.Field

interface PointedField : Field {
	
	var defaultPointer: Long
	
	override fun pointer(address: Long): Long {
		val pointer = super.pointer(address)
		if (defaultPointer == UNSET_DEFAULT_POINTER) {
			defaultPointer = pointer
			writeDefault(defaultPointer)
			return defaultPointer
		}
		return pointer
	}
	
	companion object {
		internal const val UNSET_DEFAULT_POINTER = -1L
	}
	
}