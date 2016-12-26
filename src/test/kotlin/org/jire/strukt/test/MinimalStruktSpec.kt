package org.jire.strukt.test

import io.kotlintest.specs.FreeSpec
import org.jire.strukt.INITIAL_HEAP_POINTER
import org.jire.strukt.INITIAL_REFERENCE_POINTER
import org.jire.strukt.invoke
import org.jire.strukt.members.UNINITIALIZED_OFFSET_VALUE

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
				MinimalStrukt.reset()
				
				MinimalStrukt.heap.isEmpty shouldBe true
				MinimalStrukt.heapPointer shouldBe INITIAL_HEAP_POINTER
				MinimalStrukt.referencePointer shouldBe INITIAL_REFERENCE_POINTER
				MinimalStrukt.nextReferencePointer shouldBe INITIAL_HEAP_POINTER + 1
				MinimalStrukt.members.forEach { it.offset shouldBe UNINITIALIZED_OFFSET_VALUE }
			}
		}
	}
	
}