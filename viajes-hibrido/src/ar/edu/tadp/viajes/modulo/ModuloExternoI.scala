package ar.edu.tadp.viajes.modulo

import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.transporte.Transporte

trait ModuloExternoI {
	def getTransportes(direccion: Direccion): Array[(Transporte,Direccion)]
	def combinan(a: Transporte, b: Transporte) : (Boolean, Direccion)
	def getDistanciaEntre(origen: Direccion,destino :Direccion,transporte: Transporte) : Float
}