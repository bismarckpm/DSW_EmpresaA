import { Pregunta_Encuesta } from './pregunta_encuesta';
import { Estudio } from 'src/app/interfaces/estudio';
export class Pregunta_Estudio{
    constructor(
      id: number
    )

    constructor(
         id: number,
         estudioDto: Estudio,
         preguntaEncuestaDto: Pregunta_Encuesta
    )

    constructor(
        public id?: number,
        public estudioDto?: Estudio,
        public preguntaEncuestaDto?: Pregunta_Encuesta
    ){}
}
