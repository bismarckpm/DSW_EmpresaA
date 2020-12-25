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
  estado: string;
  cantidadHijos: number;
  generoHijos: string;
  edadMinimaHijos: string;
  edadMaximaHijos: string;
  conCuantasPersonasVive: number;
  disponibilidadEnLinea: string;
  solicitudNivelEconomicoDto: number;
  solicitudProductoDto: number;
  solicitudUsuarioDto: number;
  solicitudOcupacionDto: number;
}
