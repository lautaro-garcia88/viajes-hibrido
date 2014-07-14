package ar.edu.tadp.viajes.esdatisticas

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.estadistica._
import ar.edu.tadp.viajes.Viaje
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.Recorrido
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo.CDirs

class EstadisticasTest {

  val estadistica = Estadistica
  val sumarizador = SumarizadorViaje
  val filtros = FiltroViaje

  val viajeA1 = new Viaje(Recorrido(List(
    Tramo(Colectivo("25", "Plaza"), CDirs.A_000, CDirs.A_200),
    Tramo(Colectivo("53", "Dota"), CDirs.A_200, CDirs.B_400))), CDirs.A_000, CDirs.B_400, 6.0f)

  val viajeA2 = new Viaje(Recorrido(List(
    Tramo(Tren("A", "Ferrocarriles"), CDirs.A_000, CDirs.C_000))), CDirs.A_000, CDirs.C_000, 8.0f)

  val viajeA3 = new Viaje(Recorrido(List(
    Tramo(Colectivo("107", "Dota"), CDirs.A_700, CDirs.B_400),
    Tramo(Subte("B", "Pro"), CDirs.B_400, CDirs.B_000))), CDirs.A_700, CDirs.B_000, 4.0f)

  val viajesA = List(viajeA1, viajeA2, viajeA3)

  @Test
  def costosFiltrados {
    val costoPromedioCompaniaVacia = this.estadistica.sumarizar(viajesA, this.sumarizador.costoPromedio _, this.filtros.porCompania("") _)
    Assert.assertEquals(0.0f, costoPromedioCompaniaVacia, 0.1f)

    val costoPromedioCompaniaFerrocarriles = this.estadistica.sumarizar(viajesA, this.sumarizador.costoPromedio _, this.filtros.porCompania("Ferrocarriles") _)
    Assert.assertEquals(0.0f, costoPromedioCompaniaFerrocarriles, 8.0f)

    val costoPromedioSubte = this.estadistica.sumarizar(viajesA, this.sumarizador.costoPromedio _, this.filtros.porTipoTransporte[Subte] _)
    Assert.assertEquals(4.0f, costoPromedioSubte, 0.1f)

    val facturacionTotal25 = this.estadistica.sumarizar(viajesA, this.sumarizador.facturacionTotal _, this.filtros.porLineaTransporte(Colectivo("25", "Plaza")) _)
    Assert.assertEquals(6.0f, facturacionTotal25, 0.1f)
  }

  @Test
  def cantViajesFiltrados {
    val cantZonaVacia = this.estadistica.sumarizar(viajesA, this.sumarizador.cantidadViajes _, this.filtros.porZona("") _)
    Assert.assertEquals(3.0f, cantZonaVacia, 0.1f)
    
    val cantTren= this.estadistica.sumarizar(viajesA, this.sumarizador.cantidadViajes _, this.filtros.porTipoTransporte[Subte] _)
    Assert.assertEquals(1.0f, cantTren, 0.1f)
    
    val cantCompaniaPro = this.estadistica.sumarizar(viajesA, this.sumarizador.cantidadViajes _, this.filtros.porCompania("Dota") _)
    Assert.assertEquals(2.0f, cantCompaniaPro, 0.1f)
  }

}