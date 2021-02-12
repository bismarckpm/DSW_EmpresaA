import { Usuario } from 'src/app/interfaces/usuario';

export interface Pregunta_Encuesta{
  id?: number;
  descripcion: string;
  tipoPregunta: string;
  estado: string;
  subcategoriaDto: number;
  usuarioDto: number;
}

export interface GetPregunta_Encuesta{
  idPreguntaEncuesta: number;
  descripcion: string;
  tipoPregunta: string;
  estado?: string;
  subcategoriaDto?: number;
  visible?: boolean;
  completada?: boolean;
  usuarioDto?: number;
  idPreguntaEstudio: number;
}
