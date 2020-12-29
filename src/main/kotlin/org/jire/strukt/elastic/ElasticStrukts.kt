package org.jire.strukt.elastic

import it.unimi.dsi.fastutil.longs.LongArrayList
import it.unimi.dsi.fastutil.longs.LongList
import net.openhft.chronicle.core.OS
import org.jire.strukt.AbstractStrukts
import org.jire.strukt.Strukt
import kotlin.reflect.KClass

class ElasticStrukts<T : Strukt>(
	override val type: KClass<T>,
	val initialCapacity: Long,
	val growthFactor: Double
) : AbstractStrukts<T>() {
	
	var baseAddress = UNSET_BASE_ADDRESS
	var baseSize = 0L
	
	var offset = 0L
	
	val freed: LongList = LongArrayList()
	
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
		if (freed.size > 0) {
			return freed.removeLong(0)
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
	
	override fun free(address: Long) = freed.add(address)
	
	override fun byteField(default: Byte) = ElasticByteField(type, this, default)
	override fun shortField(default: Short) = ElasticShortField(type, this, default)
	override fun intField(default: Int) = ElasticIntField(type, this, default)
	override fun longField(default: Long) = ElasticLongField(type, this, default)
	override fun floatField(default: Float) = ElasticFloatField(type, this, default)
	override fun doubleField(default: Double) = ElasticDoubleField(type, this, default)
	override fun charField(default: Char) = ElasticCharField(type, this, default)
	override fun booleanField(default: Boolean) = ElasticBooleanField(type, this, default)
	
	companion object {
		private const val UNSET_BASE_ADDRESS = -1L
	}
	
}