package org.jire.strukt

import org.jire.strukt.elastic.ElasticStrukts
import org.jire.strukt.fixed.FixedStrukts
import org.jire.strukt.pointed.PointedStrukts
import java.io.File
import kotlin.reflect.KClass

interface Strukts<T : Strukt> {
	
	val type: KClass<T>
	
	var size: Long
	var nextIndex: Long
	
	val fields: MutableList<Field<T>>
	
	fun free(): Boolean
	
	fun allocate(): Long
	
	fun free(address: Long): Boolean
	fun free(strukt: T) = free(strukt.address)
	
	operator fun invoke() = allocate()
	
	fun byteField(default: Byte): ByteField<T>
	fun shortField(default: Short): ShortField<T>
	fun intField(default: Int): IntField<T>
	fun longField(default: Long): LongField<T>
	fun floatField(default: Float): FloatField<T>
	fun doubleField(default: Double): DoubleField<T>
	fun charField(default: Char): CharField<T>
	fun booleanField(default: Boolean): BooleanField<T>
	
	operator fun invoke(default: Byte) = byteField(default)
	operator fun invoke(default: Short) = shortField(default)
	operator fun invoke(default: Int) = intField(default)
	operator fun invoke(default: Long) = longField(default)
	operator fun invoke(default: Float) = floatField(default)
	operator fun invoke(default: Double) = doubleField(default)
	operator fun invoke(default: Char) = charField(default)
	operator fun invoke(default: Boolean) = booleanField(default)
	
	companion object {
		
		const val DEFAULT_ELASTIC_CAPACITY = 1024L
		const val DEFAULT_ELASTIC_GROWTH_FACTOR = 2.0
		
		@JvmStatic
		fun <T : Strukt> fixed(type: KClass<T>, capacity: Long): Strukts<T> = FixedStrukts(type, capacity, null)
		
		@JvmStatic
		fun <T : Strukt> fixed(type: Class<T>, capacity: Long): Strukts<T> = fixed(type.kotlin, capacity)
		
		@JvmStatic
		fun <T : Strukt> fixed(type: KClass<T>, capacity: Long, persistedTo: File): Strukts<T> =
			FixedStrukts(type, capacity, persistedTo)
		
		@JvmStatic
		fun <T : Strukt> fixed(type: Class<T>, capacity: Long, persistedTo: File): Strukts<T> =
			fixed(type.kotlin, capacity, persistedTo)
		
		@JvmStatic
		fun <T : Strukt> fixed(type: KClass<T>, capacity: Long, persistedToPathname: String): Strukts<T> =
			FixedStrukts(type, capacity, File(persistedToPathname))
		
		@JvmStatic
		fun <T : Strukt> fixed(type: Class<T>, capacity: Long, persistedToPathname: String): Strukts<T> =
			fixed(type.kotlin, capacity, persistedToPathname)
		
		@JvmStatic
		fun <T : Strukt> pointed(type: KClass<T>): Strukts<T> = PointedStrukts(type)
		
		@JvmStatic
		fun <T : Strukt> pointed(type: Class<T>): Strukts<T> = pointed(type.kotlin)
		
		@JvmStatic
		@JvmOverloads
		fun <T : Strukt> elastic(
			type: KClass<T>,
			initialCapacity: Long = DEFAULT_ELASTIC_CAPACITY,
			growthFactor: Double = DEFAULT_ELASTIC_GROWTH_FACTOR
		): Strukts<T> = ElasticStrukts(type, initialCapacity, growthFactor)
		
		@JvmStatic
		@JvmOverloads
		fun <T : Strukt> elastic(
			type: Class<T>,
			initialCapacity: Long = DEFAULT_ELASTIC_CAPACITY,
			growthFactor: Double = DEFAULT_ELASTIC_GROWTH_FACTOR
		): Strukts<T> = elastic(type.kotlin, initialCapacity, growthFactor)
		
	}
	
}