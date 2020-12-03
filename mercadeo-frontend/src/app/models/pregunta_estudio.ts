export class Pregunta_Estudio{
    constructor(
        public id: number,
        public fk_estudio: number,
        public fk_preguntaEncuesta: number
    ){}
}
