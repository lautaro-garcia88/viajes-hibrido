package ar.edu.tadp.viajes

class Viaje(recorrido: Recorrido, origen: Direccion, destino: Direccion, costo: Float) {

  //  type fCalculaCosto = List[Tramo] => Float

  def getRecorrido = recorrido
  def getCosto = costo
}