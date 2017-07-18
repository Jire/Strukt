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

@file:JvmName("Members")

package org.jire.strukt.member

import org.jire.strukt.Strukt

/**
 * Creates a [ByteMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("byteMember")
fun Strukt.byte(defaultValue: Byte = 0) = ByteMember(this, defaultValue)

/**
 * Creates a [ShortMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("shortMember")
fun Strukt.short(defaultValue: Short = 0) = ShortMember(this, defaultValue)

/**
 * Creates an [IntMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("intMember")
fun Strukt.int(defaultValue: Int = 0) = IntMember(this, defaultValue)

/**
 * Creates a [LongMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("longMember")
fun Strukt.long(defaultValue: Long = 0) = LongMember(this, defaultValue)

/**
 * Creates a [FloatMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("floatMember")
fun Strukt.float(defaultValue: Float = 0F) = FloatMember(this, defaultValue)

/**
 * Creates a [DoubleMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("doubleMember")
fun Strukt.double(defaultValue: Double = 0.0) = DoubleMember(this, defaultValue)

/**
 * Creates a [BooleanMember].
 *
 * @param defaultValue The default value for the new member.
 */
@JvmName("booleanMember")
fun Strukt.boolean(defaultValue: Boolean = false) = BooleanMember(this, defaultValue)

/**
 * Creates a [StruktWithinStruktMember].
 *
 * @param T The type of [Strukt] within.
 * @param strukt The [Strukt] within this [Strukt].
 * @param initializer The "constructor" block for the [strukt].
 */
@JvmName("struktMember")
fun <T : Strukt> Strukt.strukt(strukt: T, initializer: T.() -> Unit = {})
		= StruktWithinStruktMember(this, strukt, initializer)