package ar.edu.tadp.viajes

class Viaje(tramos: List[Tramo], origen: Direccion, destino: Direccion) {
  
	def getTramos : List[Tramo] = this.tramos
	
	def calcularPrecio() : Float = {
	  0
	}
}