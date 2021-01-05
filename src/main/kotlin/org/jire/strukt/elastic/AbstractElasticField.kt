package org.jire.strukt.elastic

import org.jire.strukt.AbstractField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

abstract class AbstractElasticField(
	type: KClass<*>,
	strukts: Strukts
) : AbstractField(type, strukts), ElasticField