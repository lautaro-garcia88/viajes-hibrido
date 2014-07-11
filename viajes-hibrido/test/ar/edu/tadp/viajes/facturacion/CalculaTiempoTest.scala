package ar.edu.tadp.viajes.facturacion

import org.junit.Before
import org.junit.Test
import org.junit.Assert

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.facturacion.CalculaTiempo;
import ar.edu.tadp.viajes.modulo.CDirs

class CalculaTiempoTest {

  val calculador = CalculaTiempo

  @Test
  def tiempoColectivoColectivo {

    val tramos = List(
      Tramo(Colectivo("25"), CDirs.A_000, CDirs.A_700),
      Tramo(Colectivo("107"), CDirs.A_700, CDirs.B_400))

    Assert.assertEquals(30.0f, this.calculador.calcularTiempoTramos(tramos), 0.1f)
    Assert.assertEquals(0.0f, this.calculador.calcularTiempoCombinaciones(tramos), 0.1f)
    Assert.assertEquals(30.0f, this.calculador.calcular(tramos), 0.1f)
  }

  @Test
  def tiempoTrenSubte {

    val tramos = List(
      Tramo(Tren("A"), CDirs.A_000, CDirs.B_000),
      Tramo(Subte("B"), CDirs.B_000, CDirs.B_400))

    Assert.assertEquals(5.0f, this.calculador.calcularTiempoTramos(tramos), 0.1f)
    Assert.assertEquals(5.0f, this.calculador.calcularTiempoCombinaciones(tramos), 0.1f)
    Assert.assertEquals(10.0f, this.calculador.calcular(tramos), 0.1f)
  }

  @Test
  def tiempoColectivoSubte {

    val tramos = List(
      Tramo(Subte("B"), CDirs.B_000, CDirs.B_400),
      Tramo(Colectivo("135"), CDirs.B_400, CDirs.C_700))

    Assert.assertEquals(25.0f, this.calculador.calcularTiempoTramos(tramos), 0.1f)
    Assert.assertEquals(0.0f, this.calculador.calcularTiempoCombinaciones(tramos), 0.1f)
    Assert.assertEquals(25.0f, this.calculador.calcular(tramos), 0.1f)
  }
}