package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.AbstractStrukts
import org.jire.strukt.Strukt
import kotlin.reflect.KClass

open class PointedStrukts<T : Strukt>(override val type: KClass<T>) : AbstractStrukts<T>() {
	
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
				field as AbstractPointedField<T>
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
	
	override fun byteField(default: Byte) = PointedByteField(type, this, default)
	override fun shortField(default: Short) = PointedShortField(type, this, default)
	override fun intField(default: Int) = PointedIntField(type, this, default)
	override fun longField(default: Long) = PointedLongField(type, this, default)
	override fun floatField(default: Float) = PointedFloatField(type, this, default)
	override fun doubleField(default: Double) = PointedDoubleField(type, this, default)
	override fun charField(default: Char) = PointedCharField(type, this, default)
	override fun booleanField(default: Boolean) = PointedBooleanField(type, this, default)
	override fun <E : Enum<E>> enumField(values: Array<E>, default: E) = PointedEnumField(type, this, values, default)
	
	companion object {
		private const val UNSET_DEFAULT_ADDRESS = -1L
	}
	
}