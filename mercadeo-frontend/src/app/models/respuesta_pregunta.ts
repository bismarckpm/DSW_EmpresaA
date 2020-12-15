import { Pregunta_Encuesta } from './pregunta_encuesta';

export class Respuesta_Pregunta{
    constructor(
        public id: number,
        public nombre: string,
        public estado: string,
        public preguntaEncuestaDto: number
    ){}
}