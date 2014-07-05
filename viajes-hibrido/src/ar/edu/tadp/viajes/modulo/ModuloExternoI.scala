package ar.edu.tadp.viajes.modulo

import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.DireccionTransporte
import ar.edu.tadp.viajes.transporte.Transporte


trait ModuloExternoI {
	def getTransportesCercanos(direccion: Direccion): Array[DireccionTransporte]
	def combinan(a: Transporte, b: Transporte) : (Boolean, Direccion)
	def getDistanciaEntre(origen: Direccion,destino :Direccion,transporte: Transporte) : Float
}