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

import org.jire.strukt.internal.*
import java.io.File
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSubclassOf

interface Strukts {
	
	val type: KClass<*>
	
	var size: Long
	var nextIndex: Long
	
	val fields: List<Field>
	
	fun addField(field: Field)
	
	fun free(): Boolean
	
	fun allocate(): Long
	
	fun free(address: Long): Boolean
	fun free(strukt: Strukt) = free(strukt.address)
	
	operator fun invoke() = allocate()
	
	fun byteField(default: Byte, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): ByteField =
		InternalByteField(type, this, threadSafeType, default)
	
	fun shortField(default: Short, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): ShortField =
		InternalShortField(type, this, threadSafeType, default)
	
	fun intField(default: Int, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): IntField =
		InternalIntField(type, this, threadSafeType, default)
	
	fun longField(default: Long, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): LongField =
		InternalLongField(type, this, threadSafeType, default)
	
	fun floatField(default: Float, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): FloatField =
		InternalFloatField(type, this, threadSafeType, default)
	
	fun doubleField(default: Double, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): DoubleField =
		InternalDoubleField(type, this, threadSafeType, default)
	
	fun charField(default: Char, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): CharField =
		InternalCharField(type, this, threadSafeType, default)
	
	fun booleanField(default: Boolean, threadSafeType: ThreadSafeType = ThreadSafeType.NONE): BooleanField =
		InternalBooleanField(type, this, threadSafeType, default)
	
	fun <E : Enum<E>> enumField(
		default: E,
		threadSafeType: ThreadSafeType = ThreadSafeType.NONE,
		values: Array<E> = default.javaClass.enumConstants
	): EnumField<E> =
		InternalEnumField(type, this, threadSafeType, default, values)
	
	operator fun invoke(default: Byte) = byteField(default)
	operator fun invoke(default: Short) = shortField(default)
	operator fun invoke(default: Int) = intField(default)
	operator fun invoke(default: Long) = longField(default)
	operator fun invoke(default: Float) = floatField(default)
	operator fun invoke(default: Double) = doubleField(default)
	operator fun invoke(default: Char) = charField(default)
	operator fun invoke(default: Boolean) = booleanField(default)
	operator fun <E : Enum<E>> invoke(default: E, values: Array<E> = default.javaClass.enumConstants) =
		enumField(default, values = values)
	
	operator fun Byte.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(byteField(this, prop.threadSafeType))
	
	operator fun Short.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(shortField(this, prop.threadSafeType))
	
	operator fun Int.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(intField(this, prop.threadSafeType))
	
	operator fun Long.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(longField(this, prop.threadSafeType))
	
	operator fun Float.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(floatField(this, prop.threadSafeType))
	
	operator fun Double.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(doubleField(this, prop.threadSafeType))
	
	operator fun Char.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(charField(this, prop.threadSafeType))
	
	operator fun Boolean.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(booleanField(this, prop.threadSafeType))
	
	operator fun <E : Enum<E>> E.provideDelegate(thisRef: Strukts, prop: KProperty<*>) =
		FieldDelegate(enumField(this, prop.threadSafeType))
	
	fun toString(address: Long): String
	fun toString(strukt: Strukt) = toString(strukt.address)
	
	companion object {
		
		private val KProperty<*>.threadSafeType
			get() = annotations
				.firstOrNull { it::class.isSubclassOf(ThreadSafe::class) }
				?.let { it as ThreadSafe }?.threadSafeType
				?: ThreadSafeType.NONE
		
		class FieldDelegate<F : Field>(val delegatedTo: F) {
			operator fun getValue(strukts: Strukts, property: KProperty<*>) = delegatedTo
		}
		
		const val DEFAULT_ELASTIC_CAPACITY = 1024L
		const val DEFAULT_ELASTIC_GROWTH_FACTOR = 2.0
		
		@JvmStatic
		fun fixed(type: KClass<*>, capacity: Long): Strukts = FixedStrukts(type, capacity, null)
		
		@JvmStatic
		fun fixed(type: Class<*>, capacity: Long): Strukts = fixed(type.kotlin, capacity)
		
		@JvmStatic
		fun fixed(type: KClass<*>, capacity: Long, persistedTo: File): Strukts =
			FixedStrukts(type, capacity, persistedTo)
		
		@JvmStatic
		fun fixed(type: Class<*>, capacity: Long, persistedTo: File): Strukts =
			fixed(type.kotlin, capacity, persistedTo)
		
		@JvmStatic
		fun fixed(type: KClass<*>, capacity: Long, persistedToPathname: String): Strukts =
			FixedStrukts(type, capacity, File(persistedToPathname))
		
		@JvmStatic
		fun fixed(type: Class<*>, capacity: Long, persistedToPathname: String): Strukts =
			fixed(type.kotlin, capacity, persistedToPathname)
		
		@JvmStatic
		fun pointed(type: KClass<*>): Strukts = PointedStrukts(type)
		
		@JvmStatic
		fun pointed(type: Class<*>): Strukts = pointed(type.kotlin)
		
		@JvmStatic
		@JvmOverloads
		fun elastic(
			type: KClass<*>,
			initialCapacity: Long = DEFAULT_ELASTIC_CAPACITY,
			growthFactor: Double = DEFAULT_ELASTIC_GROWTH_FACTOR
		): Strukts = ElasticStrukts(type, initialCapacity, growthFactor)
		
		@JvmStatic
		@JvmOverloads
		fun elastic(
			type: Class<*>,
			initialCapacity: Long = DEFAULT_ELASTIC_CAPACITY,
			growthFactor: Double = DEFAULT_ELASTIC_GROWTH_FACTOR
		): Strukts = elastic(type.kotlin, initialCapacity, growthFactor)
		
	}
	
}