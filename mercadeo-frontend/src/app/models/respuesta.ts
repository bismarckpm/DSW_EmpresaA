export class Respuesta{
    constructor(
        public id: number,
        public estatus: string,
        public estado: string,
        public respuestaSimple: string,
        public respuestaMultiple: string,
        public respuestaAbierta: string,
        public escala: string,
        public respuestacol: string,
        public verdaderoFalso: string,
        public fk_usuario: number,
        public fk_preguntaEstudio: number
    ){}
}
