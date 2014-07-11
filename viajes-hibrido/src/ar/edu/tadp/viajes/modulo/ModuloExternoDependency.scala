package ar.edu.tadp.viajes.modulo

trait ModuloExternoDependency {

  private var _modulo: IModuloExterno = ModuloExterno

  def modulo = this._modulo
  def modulo(mod: IModuloExterno) = this._modulo = mod
}