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

import it.unimi.dsi.fastutil.longs.LongHeapPriorityQueue
import it.unimi.dsi.fastutil.longs.LongPriorityQueue
import net.openhft.chronicle.core.OS
import org.jire.strukt.internal.AbstractStrukts
import kotlin.reflect.KClass

open class ElasticStrukts(
	type: KClass<*>,
	val initialCapacity: Long = Strukts.DEFAULT_ELASTIC_CAPACITY,
	val growthFactor: Double = Strukts.DEFAULT_ELASTIC_GROWTH_FACTOR
) : AbstractStrukts(type) {
	
	var baseAddress = UNSET_BASE_ADDRESS
	var baseSize = 0L
	
	var offset = 0L
	
	val freed: LongPriorityQueue = LongHeapPriorityQueue()
	
	override fun free() = false
	
	private fun allocateBase(baseSize: Long = size * (initialCapacity + 1)) {
		this.baseSize = baseSize
		baseAddress = OS.memory().allocate(baseSize)
		for (field in fields) {
			field.writeDefault(baseAddress)
		}
		offset += size
	}
	
	private fun expandBase() = allocateBase((baseSize * growthFactor).toLong())
	
	override fun allocate(): Long {
		if (!freed.isEmpty) {
			return freed.dequeueLong()
		}
		if (baseAddress == UNSET_BASE_ADDRESS) {
			allocateBase()
			return allocate()
		}
		if (offset >= baseSize) {
			expandBase()
			return allocate()
		}
		val address = baseAddress + offset
		OS.memory().copyMemory(baseAddress, address, size)
		offset += size
		return address
	}
	
	override fun free(address: Long): Boolean {
		freed.enqueue(address)
		return true
	}
	
	companion object {
		private const val UNSET_BASE_ADDRESS = -1L
	}
	
}