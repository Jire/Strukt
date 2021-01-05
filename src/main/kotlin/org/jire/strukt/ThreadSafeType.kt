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

import net.openhft.chronicle.core.OS.memory as mem

enum class ThreadSafeType {
	
	NONE {
		override fun Field.readByte(address: Long) = mem().readByte(address)
		override fun Field.readShort(address: Long) = mem().readShort(address)
		override fun Field.readInt(address: Long) = mem().readInt(address)
		override fun Field.readLong(address: Long) = mem().readLong(address)
		override fun Field.readFloat(address: Long) = mem().readFloat(address)
		override fun Field.readDouble(address: Long) = mem().readDouble(address)
		
		override fun Field.writeByte(address: Long, value: Byte) = mem().writeByte(address, value)
		override fun Field.writeShort(address: Long, value: Short) = mem().writeShort(address, value)
		override fun Field.writeInt(address: Long, value: Int) = mem().writeInt(address, value)
		override fun Field.writeLong(address: Long, value: Long) = mem().writeLong(address, value)
		override fun Field.writeFloat(address: Long, value: Float) = mem().writeFloat(address, value)
		override fun Field.writeDouble(address: Long, value: Double) = mem().writeDouble(address, value)
	},
	VOLATILE {
		override fun Field.readByte(address: Long) = mem().readVolatileByte(address)
		override fun Field.readShort(address: Long) = mem().readVolatileShort(address)
		override fun Field.readInt(address: Long) = mem().readVolatileInt(address)
		override fun Field.readLong(address: Long) = mem().readVolatileLong(address)
		override fun Field.readFloat(address: Long) = mem().readVolatileFloat(address)
		override fun Field.readDouble(address: Long) = mem().readVolatileDouble(address)
		
		override fun Field.writeByte(address: Long, value: Byte) = mem().writeVolatileByte(address, value)
		override fun Field.writeShort(address: Long, value: Short) = mem().writeVolatileShort(address, value)
		override fun Field.writeInt(address: Long, value: Int) = mem().writeVolatileInt(address, value)
		override fun Field.writeLong(address: Long, value: Long) = mem().writeVolatileLong(address, value)
		override fun Field.writeFloat(address: Long, value: Float) = mem().writeVolatileFloat(address, value)
		override fun Field.writeDouble(address: Long, value: Double) = mem().writeVolatileDouble(address, value)
	},
	SYNCHRONIZE {
		override fun Field.readByte(address: Long) = synchronized(this) { mem().readVolatileByte(address) }
		override fun Field.readShort(address: Long) = synchronized(this) { mem().readVolatileShort(address) }
		override fun Field.readInt(address: Long) = synchronized(this) { mem().readVolatileInt(address) }
		override fun Field.readLong(address: Long) = synchronized(this) { mem().readVolatileLong(address) }
		override fun Field.readFloat(address: Long) = synchronized(this) { mem().readVolatileFloat(address) }
		override fun Field.readDouble(address: Long) = synchronized(this) { mem().readVolatileDouble(address) }
		
		override fun Field.writeByte(address: Long, value: Byte) =
			synchronized(this) { mem().writeVolatileByte(address, value) }
		
		override fun Field.writeShort(address: Long, value: Short) =
			synchronized(this) { mem().writeVolatileShort(address, value) }
		
		override fun Field.writeInt(address: Long, value: Int) =
			synchronized(this) { mem().writeVolatileInt(address, value) }
		
		override fun Field.writeLong(address: Long, value: Long) =
			synchronized(this) { mem().writeVolatileLong(address, value) }
		
		override fun Field.writeFloat(address: Long, value: Float) =
			synchronized(this) { mem().writeVolatileFloat(address, value) }
		
		override fun Field.writeDouble(address: Long, value: Double) =
			synchronized(this) { mem().writeVolatileDouble(address, value) }
	};
	
	abstract fun Field.readByte(address: Long): Byte
	abstract fun Field.readShort(address: Long): Short
	abstract fun Field.readInt(address: Long): Int
	abstract fun Field.readLong(address: Long): Long
	abstract fun Field.readFloat(address: Long): Float
	abstract fun Field.readDouble(address: Long): Double
	
	fun Field.readChar(address: Long): Char = readShort(address).toChar()
	fun Field.readBoolean(address: Long): Boolean = readByte(address).toInt() != 0
	fun <E : Enum<E>> Field.readEnum(address: Long, values: Array<E>): E = values[readInt(address)]
	
	abstract fun Field.writeByte(address: Long, value: Byte)
	abstract fun Field.writeShort(address: Long, value: Short)
	abstract fun Field.writeInt(address: Long, value: Int)
	abstract fun Field.writeLong(address: Long, value: Long)
	abstract fun Field.writeFloat(address: Long, value: Float)
	abstract fun Field.writeDouble(address: Long, value: Double)
	
	fun Field.writeChar(address: Long, value: Char) = writeShort(address, value.toShort())
	fun Field.writeBoolean(address: Long, value: Boolean) = writeByte(address, if (value) 1 else 0)
	fun <E : Enum<E>> Field.writeEnum(address: Long, value: E) = writeInt(address, value.ordinal)
	
}