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

package org.jire.strukt.member

import org.jire.strukt.Strukt

/**
 * The offset value of an uninitialized [StruktMember].
 */
private const val INITIAL_OFFSET_VALUE = 0L

/**
 * A member of a [Strukt], which serves as a delegate for the respective member.
 *
 * Unless you know what you're doing, you shouldn't share instances with multiple members.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param size The size, in bytes, of the member's data within the [strukt].
 */
abstract class StruktMember(val strukt: Strukt, val size: Long) {
	
	/**
	 * The offset, in bytes, of the member.
	 *
	 * This used in calculation of the member's [pointer].
	 */
	var offset = INITIAL_OFFSET_VALUE
		protected set
	
	/**
	 * Calculates the pointer of this member, using the [strukt]'s active reference pointer.
	 */
	fun pointer() = strukt.pointer + offset
	
	/**
	 * Writes the default value to the [strukt]'s active reference pointer.
	 */
	abstract fun writeDefault()
	
}