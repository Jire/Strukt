package org.jire.strukt.fixed

import it.unimi.dsi.fastutil.longs.LongArrayList
import it.unimi.dsi.fastutil.longs.LongList
import net.openhft.chronicle.core.OS
import net.openhft.chronicle.core.StruktOS
import org.jire.strukt.AbstractStrukts
import org.jire.strukt.Strukt
import java.io.File
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import kotlin.reflect.KClass

class FixedStrukts<T : Strukt>(
	override val type: KClass<T>,
	val capacity: Long,
	val persistedTo: File?
) : AbstractStrukts<T>() {
	
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
	
	companion object {
		private const val UNSET_BASE_ADDRESS = -1L
	}
	
}