package org.jire.strukt.test

import io.kotlintest.specs.FreeSpec
import org.jire.strukt.INITIAL_HEAP_POINTER
import org.jire.strukt.INITIAL_REFERENCE_POINTER
import org.jire.strukt.invoke

class MinimalStruktSpec : FreeSpec() {
	
	override fun afterEach() {
		with(MinimalStrukt) {
			reset()
			
			heap.isEmpty shouldBe true
			heapPointer shouldBe members.map { it.size }.sum() + 1
			referencePointer shouldBe INITIAL_REFERENCE_POINTER
			nextReferencePointer shouldBe INITIAL_HEAP_POINTER + 1
		}
	}
	
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
			"should have proper default values" {
				MinimalStrukt {} shouldBe INITIAL_REFERENCE_POINTER + 1
				with(MinimalStrukt) {
					byte shouldBe 1.toByte()
					short shouldBe 2.toShort()
					int shouldBe 3
					long shouldBe 4L
					float shouldBe 5F
					double shouldBe (6.0 plusOrMinus 0.0000001)
				}
			}
			"should have matching initialize sets and gets" {
				MinimalStrukt { byte = 10; short = 20; int = 30; long = 40; float = 50F; double = 60.0 }
				with(MinimalStrukt) {
					byte shouldBe 10.toByte()
					short shouldBe 20.toShort()
					int shouldBe 30
					long shouldBe 40L
					float shouldBe 50F
					double shouldBe (60.0 plusOrMinus 0.0000001)
				}
			}
		}
		
		"Members" - {
			"should be able to be set" {
				MinimalStrukt {}
				with(MinimalStrukt) {
					++byte shouldBe 2.toByte()
					++short shouldBe 3.toShort()
					++int shouldBe 4
					++long shouldBe 5L
					++float shouldBe 6F
					++double shouldBe (7.0 plusOrMinus 0.0000001)
				}
			}
		}
	}
	
}