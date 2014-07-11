package ar.edu.tadp.viajes.facturacion

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.modulo.CDirs
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.descuento.Tarjeta

class FacturacionTest {

  val facturacion = Facturacion

  @Test
  def facturacion1 {

    val tramos = List(
      Tramo(Tren("A"), CDirs.A_000, CDirs.B_000),
      Tramo(Subte("B"), CDirs.B_000, CDirs.B_400))

    val costoSinDescuento = this.facturacion.calcularCostoTotal(tramos)
    Assert.assertEquals(6.5f, costoSinDescuento, 0.1f)

    var costoConDescuento = this.facturacion.calcularCostoTotal(tramos, Tarjeta.Discapacitado _)
    Assert.assertEquals(0.0f, costoConDescuento, 0.1f)

    costoConDescuento = this.facturacion.calcularCostoTotal(tramos, Tarjeta.Turismo _)
    Assert.assertEquals(5.85f, costoConDescuento, 0.1f)

    costoConDescuento = this.facturacion.calcularCostoTotal(tramos, Tarjeta.YendoAlTrabajo _)
    Assert.assertEquals(6.5f, costoConDescuento, 0.1f)
  }

}

