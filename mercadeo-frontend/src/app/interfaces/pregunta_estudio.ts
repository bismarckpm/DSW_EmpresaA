import { Pregunta_Encuesta } from './pregunta_encuesta';
import { Estudio } from 'src/app/interfaces/estudio';
export interface Pregunta_Estudio{
  id?: number;
  pregunta: string;
  estado: string;
  estudioDto: number;
  preguntaEncuestaDto: number;
}

export interface GetPregunta_Estudio{
  idPreguntaEncuesta: number;
  idPreguntaEstudio: number;
  pregunta: string;
  subcategoria?: string;
  tipoPregunta: string;
}
