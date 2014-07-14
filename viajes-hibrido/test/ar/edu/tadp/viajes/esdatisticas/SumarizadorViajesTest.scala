package ar.edu.tadp.viajes.esdatisticas

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.Recorrido
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.Viaje
import ar.edu.tadp.viajes.modulo.CDirs
import ar.edu.tadp.viajes.estadistica.SumarizadorViaje

class EstadisticaTest {

  val sumarizador = SumarizadorViaje

  @Test
  def sumarizar {

    val viajeA1 = new Viaje(Recorrido(List(
      Tramo(Colectivo("25", "Retiro"), CDirs.A_000, CDirs.A_200),
      Tramo(Colectivo("53", ""), CDirs.A_200, CDirs.B_400))), CDirs.A_000, CDirs.B_400, 6.0f)

    val viajeA2 = new Viaje(Recorrido(List(
      Tramo(Tren("A", ""), CDirs.A_000, CDirs.C_000))), CDirs.A_000, CDirs.C_000, 8.0f)

    val viajeA3 = new Viaje(Recorrido(List(
      Tramo(Colectivo("107", ""), CDirs.A_700, CDirs.B_400),
      Tramo(Subte("B", ""), CDirs.B_400, CDirs.B_000))), CDirs.A_700, CDirs.B_000, 4.0f)

    val viajesA = List(viajeA1, viajeA2, viajeA3)

    Assert.assertEquals(6.0f, this.sumarizador.costoPromedio(viajesA), 0.1f)
    Assert.assertEquals(18.0f, this.sumarizador.facturacionTotal(viajesA), 0.1f)
    Assert.assertEquals(6.66f, this.sumarizador.tiempoPromedio(viajesA), 0.1f)
    Assert.assertEquals(3.0f, this.sumarizador.cantidadViajes(viajesA), 0.1f)

  }

}