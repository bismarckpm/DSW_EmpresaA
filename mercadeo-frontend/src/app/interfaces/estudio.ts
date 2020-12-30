import { Usuario } from '../interfaces/usuario';
import { Solicitud_Estudio } from '../interfaces/solicitud_estudio';
export interface Estudio{
  id?: number;
  nombre: string;
  fechaInicio: Date;
  fechaFin: Date;
  estatus: string;
  estado: string;
  solicitudEstudioDto: number;
  usuarioDto: number;
}


export interface GetEstudio{
  _id?: number;
  _nombre: string;
  _fechaInicio: Date;
  _fechaFin: Date;
  _estatus: string;
  _estado: string;
  _solicitudEstudioDto: number;
  _usuarioDto: number;
}

