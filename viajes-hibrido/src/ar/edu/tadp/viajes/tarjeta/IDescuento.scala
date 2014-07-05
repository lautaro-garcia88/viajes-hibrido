package ar.edu.tadp.viajes.tarjeta

import ar.edu.tadp.viajes.Tramo

trait IDescuento {
	def calcular(tramos: List[Tramo]): Float
}