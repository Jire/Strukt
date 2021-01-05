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

import org.jire.strukt.IntField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import org.openjdk.jmh.annotations.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsAppend = ["-Xmx64m", "-verbose:gc", "-ea"])
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 300, timeUnit = TimeUnit.MILLISECONDS)
abstract class Write<T : Strukt>(val strukts: Strukts, val field: IntField) {
	
	val tlr = ThreadLocalRandom.current()
	
	val address = strukts.allocate()
	
	@Benchmark
	fun write() = field(address, tlr.nextInt())
	
}