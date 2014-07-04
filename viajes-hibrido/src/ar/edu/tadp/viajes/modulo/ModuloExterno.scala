package ar.edu.tadp.viajes.modulo

import ar.edu.tadp.viajes.transporte.Transporte
import ar.edu.tadp.viajes.Direccion

object ModuloExterno extends ModuloExternoI {
  	def getTransportes(direccion: Direccion): List[Transporte] = {
  	  return List.empty
  	}

}