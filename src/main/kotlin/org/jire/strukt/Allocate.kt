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
 * val threeFive = Point { x = 3; y = 5 }
 * ```
 *
 * @param initializer The "constructor" block for the new reference.
 * If you do not set a member it will be set as the default value.
 */
operator inline fun <T : Strukt, R> T.invoke(initializer: T.() -> R): Long {
	size = internalPointer // the heap pointer will be the size since it is increased by members
	
	pointer = UNSAFE.allocateMemory(size) // create our new memory block
	
	if (defaultPointer == NIL) {
		defaultPointer = pointer
		for (member in members)
			member.writeDefault()
		
		pointer = UNSAFE.allocateMemory(size) // grab a new pointer for the actual instance
	}
	
	UNSAFE.copyMemory(defaultPointer, pointer, size) // copy the default instance
	initializer() // then apply our initializer
	
	return pointer
}