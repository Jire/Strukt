package org.jire.strukt

interface EnumField<T : Strukt, E : Enum<E>> : Field<T> {
	
	override val size get() = 4L
	
	val values: Array<E>
	val default: E
	
	override fun writeDefault(address: Long) = set(address, default)
	
	operator fun get(address: Long): E
	operator fun set(address: Long, value: E)
	
	operator fun invoke(address: Long) = get(address)
	operator fun invoke(address: Long, value: E) = set(address, value)
	
}