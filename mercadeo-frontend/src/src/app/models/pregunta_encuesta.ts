export class Pregunta_Encuesta{
    constructor(
        public codigo: number,
        public descripcion: string,
        public tipoPregunta: string,
        public estado: string,
        public fk_subcategoria: number,
        public fk_usuario: number
    ){}
}