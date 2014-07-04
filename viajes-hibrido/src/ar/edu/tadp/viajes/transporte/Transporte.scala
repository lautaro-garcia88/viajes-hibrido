package ar.edu.tadp.viajes.transporte

abstract class Transporte

case class Colectivo(linea: Int) extends Transporte
case class Subte(linea: String) extends Transporte
case class Tren(linea: String) extends Transporte