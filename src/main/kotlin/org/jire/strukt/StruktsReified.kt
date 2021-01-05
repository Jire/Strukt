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

@file:JvmName("StruktsReified")

package org.jire.strukt

import java.io.File
import kotlin.reflect.KClass

@Deprecated("Use the type you want instead of this. Will be removed in future.", ReplaceWith("pointed()"))
inline fun <reified T : Strukt> KClass<T>.dynamic(): Strukts = pointed() // default

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long) = Strukts.fixed(T::class, capacity)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedTo: File) =
	Strukts.fixed(T::class, capacity, persistedTo)

inline fun <reified T : Strukt> KClass<T>.fixed(capacity: Long, persistedToPathname: String) =
	Strukts.fixed(T::class, capacity, persistedToPathname)

inline fun <reified T : Strukt> KClass<T>.pointed(): Strukts = Strukts.pointed(T::class)

@JvmOverloads
inline fun <reified T : Strukt> KClass<T>.elastic(
	initialCapacity: Long = Strukts.DEFAULT_ELASTIC_CAPACITY,
	growthFactor: Double = Strukts.DEFAULT_ELASTIC_GROWTH_FACTOR
) = Strukts.elastic(T::class, initialCapacity, growthFactor)