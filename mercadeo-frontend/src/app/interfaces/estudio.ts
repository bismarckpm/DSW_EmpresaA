import { GetUsuario, Usuario } from '../interfaces/usuario';
import { GetSolicitud_Estudio, Solicitud_Estudio } from '../interfaces/solicitud_estudio';
export interface Estudio{
  id?: number;
  nombre: string;
  fechaInicio: Date;
  fechaFin?: Date;
  estatus: string;
  estado: string;
  conclusion: string;
  solicitudEstudioDto: number;
  usuarioDto: number;
}

export interface SetEstudio{
  nombre: string;
  fechaInicio: Date;
  fechaFin: Date;
  estatus: string;
  estado: string;
  conclusion: string;
  solicitudEstudioDto: number;
  usuarioDto: number;
}

export interface GetEstudioEncuestado{
  idEstudio: number;
  nombre: string;
  estatus: string;
  fechaI: Date;
}

export interface GetEstudiosRecomendados{
  idEstudio: number;
  nombre: string;
  estatus: string;
  fechaI: Date;
}

export interface GetEstudio{
  _id?: number;
  _nombre: string;
  _fechaInicio: Date;
  _fechaFin: Date;
  _estatus: string;
  _estado: string;
  _conclusion: string;
  _solicitudEstudio: GetSolicitud_Estudio;
  _usuario: GetUsuario;
}

