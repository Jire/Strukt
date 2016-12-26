package org.jire.strukt.test

import io.kotlintest.specs.FreeSpec
import org.jire.strukt.INITIAL_HEAP_POINTER
import org.jire.strukt.INITIAL_REFERENCE_POINTER
import org.jire.strukt.invoke

class MinimalStruktSpec : FreeSpec() {
	
	init {
		"Allocations" - {
			"should begin with the initial reference pointer" {
				MinimalStrukt.referencePointer shouldBe INITIAL_REFERENCE_POINTER
			}
			"should match next reference pointer" {
				val expectedReference = MinimalStrukt.nextReferencePointer
				val example = MinimalStrukt {}
				example shouldBe expectedReference
			}
			"should properly reset" {
				with(MinimalStrukt) {
					reset()
					
					heap.isEmpty shouldBe true
					heapPointer shouldBe members.map { it.size }.sum() + 1
					referencePointer shouldBe INITIAL_REFERENCE_POINTER
					nextReferencePointer shouldBe INITIAL_HEAP_POINTER + 1
				}
			}
			"should have proper default values" {
				MinimalStrukt {} shouldBe INITIAL_REFERENCE_POINTER + 1
				with(MinimalStrukt) {
					byte shouldBe 1.toByte()
					short shouldBe 2.toShort()
					int shouldBe 3
					long shouldBe 4L
					float shouldBe 5F
					double shouldBe (6.0 plusOrMinus 0.0000001)
					
					reset()
				}
			}
		}
	}
	
}