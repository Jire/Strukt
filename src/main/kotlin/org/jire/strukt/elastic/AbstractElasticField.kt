package org.jire.strukt.elastic

import org.jire.strukt.AbstractField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractElasticField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>
) : AbstractField<T>(type, strukts), ElasticField<T>