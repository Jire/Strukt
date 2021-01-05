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

package org.jire.strukt.benchmarks

import it.unimi.dsi.fastutil.longs.LongArrayList
import it.unimi.dsi.fastutil.longs.LongList
import org.jire.strukt.Strukts
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsAppend = ["-Xmx16g", "-Xms16g"])
@Warmup(iterations = 0)
@Measurement(iterations = 1)
abstract class Free(val strukts: Strukts<*>) {
	
	private val addresses: LongList = LongArrayList(CAPACITY.toInt())
	
	@Setup
	fun setup() {
		for (i in 1..CAPACITY) {
			addresses.add(strukts.allocate())
		}
	}
	
	@Benchmark
	fun free() {
		val it = addresses.listIterator()
		while (it.hasNext()) {
			val address = it.nextLong()
			strukts.free(address)
		}
	}
	
}