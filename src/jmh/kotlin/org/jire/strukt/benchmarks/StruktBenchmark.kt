package org.jire.strukt.benchmarks

import org.openjdk.jmh.Main
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

open class StruktBenchmark {
	
	companion object {
		@JvmStatic
		fun main(args: Array<String>) = Main.main(args)
	}
	
	@Fork(value = 1, warmups = 0)
	@Warmup(iterations = 1)
	@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	fun allocateStrukt() = BenchmarkHelper.allocatePoint()
	
	@Fork(value = 1, warmups = 0)
	@Warmup(iterations = 1)
	@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	fun allocateHeap(): Point = BenchmarkHelper.allocatePointHeap()
	
	@Fork(value = 1, warmups = 0)
	@Warmup(iterations = 1)
	@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	fun readStrukt() = BenchmarkHelper.readPoint()
	
	@Fork(value = 1, warmups = 0)
	@Warmup(iterations = 1)
	@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	fun readHeap() = BenchmarkHelper.readPointHeap()
	
	@Fork(value = 1, warmups = 0)
	@Warmup(iterations = 1)
	@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	fun writeStrukt() = BenchmarkHelper.writePoint()
	
	@Fork(value = 1, warmups = 0)
	@Warmup(iterations = 1)
	@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	fun writeHeap() = BenchmarkHelper.writePointHeap()
	
}