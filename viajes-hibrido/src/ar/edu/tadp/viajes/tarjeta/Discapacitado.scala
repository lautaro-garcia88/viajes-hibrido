package ar.edu.tadp.viajes.tarjeta

import scala.collection.immutable.List
import ar.edu.tadp.viajes.Tramo

class Discapacitado extends IDescuento {
  
	def calcular(tramos: List[Tramo]): Float = {
		return 0 
	}
}