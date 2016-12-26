package org.jire.strukt.test

import org.jire.strukt.Strukt
import org.jire.strukt.members.*

object MinimalStrukt : Strukt() {
	var byte by byte(1)
	var short by short(2)
	var int by int(3)
	var long by long(4)
	var float by float(5F)
	var double by double(6.0)
}