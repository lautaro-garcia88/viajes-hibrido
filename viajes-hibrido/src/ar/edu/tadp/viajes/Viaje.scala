package ar.edu.tadp.viajes

import ar.edu.tadp.viajes.modulo.ModuloExternoI
import ar.edu.tadp.viajes.modulo.ModuloExterno

class Viaje( origen : Direccion, destino: Direccion ) {
	
	implicit def moduloExterno : ModuloExternoI = ModuloExterno
	
	def armarRecorrido() : List[Tramo] = {
	  
	  return List.empty
	}
}