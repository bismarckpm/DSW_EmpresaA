
export class Pregunta_Encuesta{
    constructor(
        public id: number,
        public descripcion: string,
        public tipoPregunta: string,
        public estado: string,
        public subcategoriaDto: number,
        public usuarioDto: number
    ){}
}