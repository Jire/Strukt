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

import net.openhft.chronicle.core.OS
import org.jire.strukt.internal.*
import kotlin.reflect.KClass

open class PointedStrukts(type: KClass<*>) : AbstractStrukts(type) {
	
	var defaultAddress = UNSET_DEFAULT_ADDRESS
	
	override fun free(): Boolean {
		//OS.memory().freeMemory(defaultAddress, size)
		return false
	}
	
	override fun allocate(): Long {
		var address = OS.memory().allocate(size)
		if (defaultAddress == UNSET_DEFAULT_ADDRESS) {
			defaultAddress = address
			for (field in fields) {
				field.pointer(address)
			}
			
			address = OS.memory().allocate(size)
		}
		OS.memory().copyMemory(defaultAddress, address, size)
		return address
	}
	
	override fun free(address: Long): Boolean {
		OS.memory().freeMemory(address, size)
		return true
	}
	
	override fun byteField(default: Byte) = InternalByteField(type, this, default)
	override fun shortField(default: Short) = InternalShortField(type, this, default)
	override fun intField(default: Int) = InternalIntField(type, this, default)
	override fun longField(default: Long) = InternalLongField(type, this, default)
	override fun floatField(default: Float) = InternalFloatField(type, this, default)
	override fun doubleField(default: Double) = InternalDoubleField(type, this, default)
	override fun charField(default: Char) = InternalCharField(type, this, default)
	override fun booleanField(default: Boolean) = InternalBooleanField(type, this, default)
	override fun <E : Enum<E>> enumField(default: E, values: Array<E>) = InternalEnumField(type, this, default, values)
	
	companion object {
		private const val UNSET_DEFAULT_ADDRESS = -1L
	}
	
}