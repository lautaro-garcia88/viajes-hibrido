package ar.edu.tadp.viajes.transporte

import ar.edu.tadp.viajes.Direccion

abstract class Transporte {
  def compania : String
}
case class Colectivo(linea: String, compania: String) extends Transporte
case class Tren(linea: String, compania: String) extends Transporte
case class Subte(linea: String, compania: String) extends Transporte