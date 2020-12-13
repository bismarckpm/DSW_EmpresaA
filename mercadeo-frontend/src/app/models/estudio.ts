export class Estudio{
    constructor(
        public id: number,
        public nombre: string,
        public tipoInstrumento: string,
        public fechaInicio: string,
        public fechaFin: string,
        public estatus: string,
        public estado: string,
        public fk_solicitud: number,
        public fk_usuario: number
    ){}
}