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

package org.jire.strukt.benchmarks.heap

import org.jire.strukt.benchmarks.VALUE
import org.jire.strukt.internal.AbstractStrukts

object HeapPoints : AbstractStrukts(HeapPoint::class) {
	
	val x by VALUE
	val y by VALUE
	
	override fun allocate() = HeapPoint().address
	
	override val type = HeapPoint::class
	
	override fun free(): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun free(address: Long): Boolean {
		TODO("Not yet implemented")
	}
	
}