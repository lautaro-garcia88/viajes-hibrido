package ar.edu.tadp.viajes.facturacion

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.facturacion._
import ar.edu.tadp.viajes.modulo.CDirs
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.Tramo

class CalculaCostoTest {

  def calculaCosto: ICalculador = CalculaCosto

  @Test
  def costoTrenSubte {

    val tramos = List(
      Tramo(Tren("A"), CDirs.A_000, CDirs.B_000),
      Tramo(Subte("B"), CDirs.B_000, CDirs.B_400))

    val costo = this.calculaCosto.calcular(tramos)

    Assert.assertEquals(6.5f, costo, 0.1f)
  }

  @Test
  def costoColectivoColectivo {

    val tramos = List(
      Tramo(Colectivo("25"), CDirs.A_000, CDirs.A_700),
      Tramo(Colectivo("107"), CDirs.A_700, CDirs.B_400))

    val costo = this.calculaCosto.calcular(tramos)

    Assert.assertEquals(5.0f, costo, 0.1f)
  }

}