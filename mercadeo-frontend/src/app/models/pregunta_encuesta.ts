export class Pregunta_Encuesta{
    constructor(
        public id: number,
        public descripcion: string,
        public tipoPregunta: string,
        public estado: string,
        public fk_subcategoria: number,
        public fk_usuario: number
    ){}
}
