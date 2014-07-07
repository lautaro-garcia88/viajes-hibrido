package ar.edu.tadp.viajes.transporte

abstract class TipoVehiculo

case class Colectivo(linea: String) extends TipoVehiculo
case class Tren(linea: String) extends TipoVehiculo
case class Subte(linea: String) extends TipoVehiculo