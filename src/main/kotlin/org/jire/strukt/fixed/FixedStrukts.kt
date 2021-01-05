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

package org.jire.strukt.fixed

import it.unimi.dsi.fastutil.longs.LongArrayList
import it.unimi.dsi.fastutil.longs.LongList
import net.openhft.chronicle.core.OS
import net.openhft.chronicle.core.StruktOS
import org.jire.strukt.AbstractStrukts
import java.io.File
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import kotlin.reflect.KClass

open class FixedStrukts(
	type: KClass<*>,
	val capacity: Long,
	val persistedTo: File? = null
) : AbstractStrukts(type) {
	
	var baseAddress = UNSET_BASE_ADDRESS
	var baseSize = 0L
	
	var offset = 0L
	
	val freed: LongList = LongArrayList()
	
	val raf = if (persistedTo == null) null else RandomAccessFile(persistedTo, "rw")
	
	override fun free() = if (raf == null) {
		OS.memory().freeMemory(baseAddress, baseSize)
		true
	} else {
		OS.unmap(baseAddress, baseSize)
		raf.channel.close()
		raf.close()
		
		val file = persistedTo!!
		file.deleteOnExit()
		file.delete()
	}
	
	private fun allocateBase() {
		baseSize = size * (capacity + 1)
		baseAddress = if (raf == null)
			OS.memory().allocate(baseSize)
		else StruktOS.map(
			raf.channel,
			FileChannel.MapMode.READ_WRITE, 0, baseSize
		)
		for (field in fields) {
			field.writeDefault(baseAddress)
		}
		offset += size
	}
	
	override fun allocate(): Long {
		if (freed.size > 0) {
			return freed.removeLong(0)
		}
		if (baseAddress == UNSET_BASE_ADDRESS) {
			allocateBase()
			return allocate()
		}
		val address = baseAddress + offset
		OS.memory().copyMemory(baseAddress, address, size)
		offset += size
		return address
	}
	
	override fun free(address: Long) = freed.add(address)
	
	override fun byteField(default: Byte) = FixedByteField(type, this, default)
	override fun shortField(default: Short) = FixedShortField(type, this, default)
	override fun intField(default: Int) = FixedIntField(type, this, default)
	override fun longField(default: Long) = FixedLongField(type, this, default)
	override fun floatField(default: Float) = FixedFloatField(type, this, default)
	override fun doubleField(default: Double) = FixedDoubleField(type, this, default)
	override fun charField(default: Char) = FixedCharField(type, this, default)
	override fun booleanField(default: Boolean) = FixedBooleanField(type, this, default)
	override fun <E : Enum<E>> enumField(default: E, values: Array<E>) = FixedEnumField(type, this, values, default)
	
	companion object {
		private const val UNSET_BASE_ADDRESS = -1L
	}
	
}