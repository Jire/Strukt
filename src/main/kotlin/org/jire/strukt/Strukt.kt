package org.jire.strukt

import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import org.jire.strukt.Strukts.idToStrukt
import org.jire.strukt.field.*
import kotlin.reflect.KProperty

abstract class Strukt {
	
	val id = Strukts.nextID++
	
	@PublishedApi
	internal var default = -1L
	
	@PublishedApi
	internal var cursor = -1L
	
	@PublishedApi
	internal var offset = 0
	
	@PublishedApi
	internal val fields: ObjectList<StruktField> = ObjectArrayList()
	
	operator fun get(cursor: Long) {
		this.cursor = cursor
	}
	
	operator fun get(reference: StruktReference<*>) = get(reference.address)
	
	override fun toString() = "${this::class.simpleName}(${fields.joinToString(",") { "${it.prop.name}=$it" }})"
	
	operator fun Byte.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = ByteField(thisRef, prop, offset, this)
	operator fun Short.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = ShortField(thisRef, prop, offset, this)
	operator fun Int.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = IntField(thisRef, prop, offset, this)
	operator fun Long.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = LongField(thisRef, prop, offset, this)
	operator fun Float.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = FloatField(thisRef, prop, offset, this)
	operator fun Double.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = DoubleField(thisRef, prop, offset, this)
	operator fun Char.provideDelegate(thisRef: Strukt, prop: KProperty<*>) = CharField(thisRef, prop, offset, this)
	operator fun Boolean.provideDelegate(thisRef: Strukt, prop: KProperty<*>) =
		BooleanField(thisRef, prop, offset, this)
	
	init {
		idToStrukt[id] = this@Strukt
	}
	
}