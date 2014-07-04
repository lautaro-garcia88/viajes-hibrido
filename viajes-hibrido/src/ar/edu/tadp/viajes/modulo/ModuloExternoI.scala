package ar.edu.tadp.viajes.modulo

import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.transporte.Transporte

trait ModuloExternoI {
	def getTransportes(direccion: Direccion): List[Transporte]
}