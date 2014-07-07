package ar.edu.tadp.viajes

import ar.edu.tadp.viajes.transporte.TipoVehiculo
import ar.edu.tadp.viajes.Direccion

case class Tramo (vehiculo: TipoVehiculo,origen: Direccion, destino: Direccion)