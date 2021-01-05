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

package org.jire.strukt.internal

import org.jire.strukt.Field
import org.jire.strukt.Strukts
import org.jire.strukt.ThreadSafeType
import kotlin.reflect.KClass

abstract class AbstractField(
	override val type: KClass<*>,
	final override val strukts: Strukts,
	override val threadSafeType: ThreadSafeType
) : Field {
	
	override val index: Long = strukts.nextIndex
	override val offset: Long = strukts.size
	
	init {
		strukts.addField(this)
	}
	
	override val name by lazy(LazyThreadSafetyMode.NONE) {
		javaClass.simpleName
	}

/*	override val threadSafeType =
		property.annotations
			.firstOrNull { it::class == ThreadSafe::class }
			?.let { it as ThreadSafe }?.threadSafeType
			?: ThreadSafeType.NONE*/
	
}