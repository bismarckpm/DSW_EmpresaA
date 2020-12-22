import { Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
export interface Respuesta_Pregunta{
  id?: number;
  nombre: string;
  estado: string;
  preguntaEncuestaDto: number;
}
