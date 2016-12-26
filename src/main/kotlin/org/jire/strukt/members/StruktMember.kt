/*
 *    Copyright 2016-2017 Thomas G. Nappo (Jire)
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
 *
 */

package org.jire.strukt.members

import org.jire.strukt.INITIAL_REFERENCE_POINTER
import org.jire.strukt.Strukt

/**
 * The offset value of an uninitialized [StruktMember].
 */
internal const val UNINITIALIZED_OFFSET_VALUE = -1L

/**
 * A member of a [Strukt], which serves as a delegate for the respective member.
 *
 * Unless you know what you're doing, you shouldn't share instances with multiple members.
 */
abstract class StruktMember {
	
	/**
	 * The parent [Strukt] of this member, to which this member belongs to.
	 */
	abstract val strukt: Strukt
	
	/**
	 * The size, in bytes, of the member's data within the [strukt]'s heap.
	 */
	abstract val size: Long
	
	/**
	 * The offset, in bytes, of the member.
	 *
	 * This is typically used to read
	 */
	open var offset = UNINITIALIZED_OFFSET_VALUE
	
	/**
	 * Calculates the heap pointer of this member, using the [strukt]'s active reference pointer.
	 */
	open fun pointer() = ((strukt.referencePointer - INITIAL_REFERENCE_POINTER) * strukt.size) + offset
	
	/**
	 * Initializes the member and parent [Strukt] so that the initial reference
	 * contains a reference with the default values.
	 */
	abstract fun setup()
	
	/**
	 * Resets the attributes of this member back to their uninitialized state.
	 *
	 * This includes setting [offset] back to [UNINITIALIZED_OFFSET_VALUE].
	 */
	open fun reset() {
		offset = UNINITIALIZED_OFFSET_VALUE
	}
	
}