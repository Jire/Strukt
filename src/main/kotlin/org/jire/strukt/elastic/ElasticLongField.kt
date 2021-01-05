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
import org.jire.strukt.LongField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class ElasticLongField(
	type: KClass<*>, strukts: Strukts,
	override val default: Long
) : AbstractElasticField(type, strukts), LongField {
	
	override fun get(address: Long) = OS.memory().readLong(pointer(address))
	
	override fun set(address: Long, value: Long) = OS.memory().writeLong(pointer(address), value)
	
}