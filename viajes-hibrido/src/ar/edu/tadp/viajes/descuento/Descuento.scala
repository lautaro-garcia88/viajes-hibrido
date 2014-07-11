package ar.edu.tadp.viajes.descuento

abstract class Descuento

case class DescuentoPorcentual(valor: Float) extends Descuento
case class DescuentoFijo(valor: Float) extends Descuento