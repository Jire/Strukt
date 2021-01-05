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

package org.jire.strukt.elastic

import net.openhft.chronicle.core.OS
import org.jire.strukt.IntField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticIntField(
	type: KClass<*>, strukts: Strukts,
	override val default: Int
) : AbstractElasticField(type, strukts), IntField {
	
	override fun get(address: Long) = OS.memory().readInt(pointer(address))
	
	override fun set(address: Long, value: Int) = OS.memory().writeInt(pointer(address), value)
	
}