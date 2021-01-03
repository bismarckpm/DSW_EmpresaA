import { Ocupacion } from './ocupacion';
import { Usuario } from './usuario';
import { Producto } from '../interfaces/producto';
import { Nivel_Economico } from './nivel_economico';
export interface Solicitud_Estudio{
  id?: number;
  descripcionSolicitud: string;
  generoPoblacional: string;
  fechaPeticion: Date;
  edadMinimaPoblacion: string;
  edadMaximaPoblacion: string;
  estatus: string;
  estado: string;
  conCuantasPersonasVive: number;
  disponibilidadEnLinea: string;
  nivelEconomicoDto: number;
  productoDto: number;
  usuarioDto: number;
  ocupacionDto: number;
}

export interface GetSolicitud_Estudio{
  _id: number;
  _descripcionSolicitud: string;
  _generoPoblacional: string;
  _fechaPeticion: Date;
  _edadMinimaPoblacion: string;
  _edadMaximaPoblacion: string;
  _estatus: string;
  _estado: string;
  _conCuantasPersonasVive: number;
  _disponibilidadEnLinea: string;
  _nivelEconomicoDto: number;
  _productoDto: number;
  _usuarioDto: number;
  _ocupacionDto: number;
}
