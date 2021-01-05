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

import it.unimi.dsi.fastutil.objects.ObjectArrayList
import kotlin.reflect.KClass

abstract class AbstractStrukts(override val type: KClass<*>) : Strukts {
	
	override var size = 0L
	override var nextIndex = 0L
	
	override val fields = ObjectArrayList<Field>()
	
	override fun addField(field: Field) {
		size += field.size
		nextIndex++
		fields.add(field)
	}
	
	override fun toString(address: Long) = fields.joinToString(", ") { "${it.name}(${it.getBoxed(address)})" }
	
}