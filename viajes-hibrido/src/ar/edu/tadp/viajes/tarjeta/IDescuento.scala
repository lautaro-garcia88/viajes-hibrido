package ar.edu.tadp.viajes.tarjeta

import ar.edu.tadp.viajes._

trait IDescuento {
	def calcular(tramos: List[Tramo]): Float
}