package ar.edu.tadp.viajes.modulo

import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.transporte._

trait IModuloExterno {

  def getTransportesCercanos(direccion: Direccion): List[(Transporte, Direccion)]
  def combinan(a: Transporte, b: Transporte): (Boolean, Option[Direccion])
  def getDistanciaEntre(origen: Direccion, destino: Direccion, transporte: Transporte): Float
  def getDistanciaEntre(origen: Direccion, destino: Direccion): Float

}