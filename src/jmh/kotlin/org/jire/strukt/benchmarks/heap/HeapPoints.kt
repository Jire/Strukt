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

import org.jire.strukt.*
import org.jire.strukt.benchmarks.VALUE

val heapPointX = HeapPoints(VALUE)
val heapPointY = HeapPoints(VALUE)

object HeapPoints : AbstractStrukts<HeapPoint>() {
	
	override fun allocate() = HeapPoint().address
	
	override val type = HeapPoint::class
	
	override fun free(): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun free(address: Long): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun byteField(default: Byte): ByteField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun shortField(default: Short): ShortField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun intField(default: Int) = HeapIntField(VALUE, HeapPoint())
	
	override fun longField(default: Long): LongField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun floatField(default: Float): FloatField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun doubleField(default: Double): DoubleField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun charField(default: Char): CharField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun booleanField(default: Boolean): BooleanField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun <E : Enum<E>> enumField(values: Array<E>, default: E): EnumField<HeapPoint, E> {
		TODO("Not yet implemented")
	}
	
}