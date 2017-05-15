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

import sun.misc.Unsafe

/**
 * A convenience reference to "theUnsafe" from within [sun.misc.Unsafe].
 *
 * WARNING: Strukt uses this internally, only use this if you know what you're doing.
 */
val UNSAFE by lazy(LazyThreadSafetyMode.NONE) {
	val field = Unsafe::class.java.getDeclaredField("theUnsafe")
	field.isAccessible = true
	field.get(null) as Unsafe
}