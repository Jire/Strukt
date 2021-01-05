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

package org.jire.strukt.benchmarks.fixed

import org.jire.strukt.Strukt
import org.jire.strukt.benchmarks.CAPACITY
import org.jire.strukt.benchmarks.Point
import org.jire.strukt.benchmarks.VALUE
import org.jire.strukt.fixed

val fixedPoints = FixedPoint::class.fixed(CAPACITY, "fixedpoints.dat")

val fixedPointX = fixedPoints(VALUE)
val fixedPointY = fixedPoints(VALUE)

inline class FixedPoint(override val address: Long = fixedPoints()) : Point, Strukt {
	override var x: Int
		get() = fixedPointX(address)
		set(value) = fixedPointX(address, value)
	override var y: Int
		get() = fixedPointY(address)
		set(value) = fixedPointY(address, value)
}