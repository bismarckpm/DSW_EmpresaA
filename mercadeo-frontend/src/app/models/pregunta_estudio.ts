export class Pregunta_Estudio{
    constructor(
        public codigo: number,
        public fk_estudio: number,
        public fk_preguntaEncuesta: number
    ){}
}