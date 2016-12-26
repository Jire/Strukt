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

package org.jire.strukt

import net.openhft.chronicle.bytes.Bytes
import org.jire.strukt.members.StruktMember

/**
 * The initial heap size.
 */
private const val INITIAL_HEAP_SIZE = 1L

/**
 * The initial pointer value for the data heap.
 */
const val INITIAL_HEAP_POINTER = 1L

/**
 * The initial pointer value of the active reference.
 *
 * This value is also the reference pointer to the default value reference.
 */
const val INITIAL_REFERENCE_POINTER = 1L

/**
 * The size of an uninitialized Strukt.
 */
const val UNINITIALIZED_STRUKT_SIZE = -1L

/**
 * A _Strukt_, or more recognizably a _structure_, is a data type
 * that represents a group of members (see: [StruktMember]).
 *
 * An example _Strukt_ definition might look like this:
 *
 * ```kotlin
 * object Coordinate : Strukt() {
 *     val x by int()
 *     val y by int()
 * }
 * ```
 */
abstract class Strukt {
	
	/**
	 * The raw data heap that stores the member's data.
	 */
	val heap: Bytes<Void> = Bytes.allocateElasticDirect(INITIAL_HEAP_SIZE)
	
	/**
	 * The pointer to the [heap].
	 */
	var heapPointer = INITIAL_HEAP_POINTER
	
	/**
	 * The pointer to the active reference.
	 */
	var referencePointer = INITIAL_REFERENCE_POINTER
	
	/**
	 * The pointer to the next available reference pointer.
	 */
	var nextReferencePointer = INITIAL_REFERENCE_POINTER + 1
	
	/**
	 * The total size, in bytes, of the structure.
	 */
	var size = UNINITIALIZED_STRUKT_SIZE
	
	open val members: MutableSet<StruktMember> = HashSet()
	
	open fun reset() {
		heap.clear()
		heapPointer = INITIAL_HEAP_POINTER
		
		referencePointer = INITIAL_REFERENCE_POINTER
		nextReferencePointer = INITIAL_REFERENCE_POINTER + 1
		
		members.forEach(StruktMember::reset) // reset first...
		members.forEach(StruktMember::setup) // then setup
	}
	
}

/**
 * Adjusts the view of the [Strukt] to match the specified [referencePointer].
 *
 * @param referencePointer The new reference pointer, received from [Strukt] allocation.
 */
operator fun <T : Strukt> T.get(referencePointer: Long) = apply {
	this.referencePointer = referencePointer
}

/**
 * Allocates a new [Strukt] reference using the given initializer,
 * and gives you back its reference pointer.
 *
 * This function automatically adjusts the active
 * reference pointer to match the new reference.
 *
 * A typical allocation might look like this:
 *
 * ```kotlin
 * val threeFive = Coordinate { x = 3; y = 5 }
 * ```
 *
 * @param initializer The "constructor" block for the new reference.
 * If you do not set a member it will be set as the default value.
 */
operator fun <T : Strukt> T.invoke(initializer: T.() -> Unit): Long {
	// If the size has been uninitialized, set it now
	if (size == UNINITIALIZED_STRUKT_SIZE)
		size = heapPointer - INITIAL_HEAP_POINTER
	
	// Grab the next reference
	val reference = nextReferencePointer++
	
	// Set our active reference pointer to the new reference
	referencePointer = reference
	
	// Fill the new reference in with the default values
	val copyReference = reference - INITIAL_REFERENCE_POINTER
	for (copyIndex in INITIAL_HEAP_POINTER..size) {
		val copyIndexTarget = (copyReference * size) + copyIndex
		heap.writeByte(copyIndexTarget, heap.readByte(copyIndex))
	}
	
	// Run the user's initializer block
	initializer()
	
	// Return our reference pointer
	return reference
}