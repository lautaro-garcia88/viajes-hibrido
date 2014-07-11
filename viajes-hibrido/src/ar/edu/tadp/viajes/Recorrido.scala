package ar.edu.tadp.viajes

case class Recorrido( tramos: List[Tramo]){
  def getTramos = tramos
}