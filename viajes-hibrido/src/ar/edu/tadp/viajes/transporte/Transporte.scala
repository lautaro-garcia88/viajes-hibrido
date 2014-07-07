package ar.edu.tadp.viajes.transporte

import ar.edu.tadp.viajes.Direccion

abstract class Transporte
case class Colectivo(linea: String) extends Transporte
case class Tren(linea: String) extends Transporte
case class Subte(linea: String) extends Transporte