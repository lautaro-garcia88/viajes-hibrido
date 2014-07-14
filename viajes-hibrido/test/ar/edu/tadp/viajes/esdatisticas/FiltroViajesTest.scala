package ar.edu.tadp.viajes.esdatisticas

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.modulo.CDirs
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.Recorrido
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.Viaje
import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.estadistica.FiltroViaje

class FiltroTest {

  val filtros = FiltroViaje

  @Test
  def filtroLinea {
    val viaje = new Viaje(Recorrido(List(
      Tramo(Colectivo("25", ""), CDirs.A_000, CDirs.A_400),
      Tramo(Colectivo("107", ""), CDirs.A_000, CDirs.A_400))), CDirs.A_000, CDirs.A_700, 0.0f)

    Assert.assertEquals(true, filtros.porLineaTransporte(Colectivo("25", ""))(viaje))
    Assert.assertEquals(false, filtros.porLineaTransporte(Colectivo("135", ""))(viaje))

  }

  @Test
  def filtroTipoTransporte {
    val viaje = new Viaje(Recorrido(List(
      Tramo(Colectivo("25", ""), CDirs.A_000, CDirs.A_400),
      Tramo(Colectivo("107", ""), CDirs.A_000, CDirs.A_400))), CDirs.A_000, CDirs.A_700, 0.0f)

    Assert.assertEquals(true, filtros.porTipoTransporte[Colectivo](viaje))
    Assert.assertEquals(false, filtros.porTipoTransporte[Subte](viaje))
  }

  @Test
  def filtroZona {
    val viaje = new Viaje(Recorrido(List(
      Tramo(Colectivo("25", ""), CDirs.A_000, CDirs.A_400),
      Tramo(Colectivo("107", ""), CDirs.A_000, Direccion("Dir", 123, "Centro")))), CDirs.A_000, CDirs.A_700, 0.0f)

    Assert.assertEquals(true, filtros.porZona("Centro")(viaje))
    Assert.assertEquals(false, filtros.porZona("Devoto")(viaje))
  }

  @Test
  def filtroCompania {
    val viaje = new Viaje(Recorrido(List(
      Tramo(Colectivo("25", "Plaza"), CDirs.A_000, CDirs.A_400),
      Tramo(Colectivo("107", "Dota"), CDirs.A_000, Direccion("Dir", 123, "Centro")))), CDirs.A_000, CDirs.A_700, 0.0f)

    Assert.assertEquals(true, filtros.porCompania("Plaza")(viaje))
    Assert.assertEquals(false, filtros.porCompania("Devoto")(viaje))
  }

}