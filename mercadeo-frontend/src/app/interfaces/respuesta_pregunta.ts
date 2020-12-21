import { Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
export class Respuesta_Pregunta{
    constructor(
      id: number
    )

    constructor(
       id: number,
       nombre: string,
       estado: string,
       preguntaEncuestaDto: Pregunta_Encuesta
    )

    constructor(
        public id?: number,
        public nombre?: string,
        public estado?: string,
        public preguntaEncuestaDto?: Pregunta_Encuesta
    ){}
}
