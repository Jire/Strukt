package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.AbstractStrukts
import org.jire.strukt.Strukt
import kotlin.reflect.KClass

class PointedStrukts<T : Strukt>(override val type: KClass<T>) : AbstractStrukts<T>() {
	
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
				field as AbstractPointedStruktField<T>
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
	
	override fun invoke(default: Byte) = PointedByteField(type, this, default)
	override fun invoke(default: Short) = PointedShortField(type, this, default)
	override fun invoke(default: Int) = PointedIntField(type, this, default)
	override fun invoke(default: Long) = PointedLongField(type, this, default)
	override fun invoke(default: Float) = PointedFloatField(type, this, default)
	override fun invoke(default: Double) = PointedDoubleField(type, this, default)
	override fun invoke(default: Char) = PointedCharField(type, this, default)
	override fun invoke(default: Boolean) = PointedBooleanField(type, this, default)
	
	companion object {
		private const val UNSET_DEFAULT_ADDRESS = -1L
	}
	
}