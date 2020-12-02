export class Pregunta_Encuesta{
    constructor(
        public codigo: number,
        public nombre: string,
        public estado: string,
        public fk_preguntaEncuesta: number
    ){}
}