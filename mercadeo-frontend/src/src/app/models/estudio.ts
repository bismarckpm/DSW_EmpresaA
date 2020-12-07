export class Estudio{
    constructor(
        public codigo: number,
        public nombre: string,
        public tipoInstrumento: string,
        public fechaInicio: string,
        public fechaFin: string,
        public estatus: string,
        public estado: string,
        public fk_estudio_solicitud: number,
        public fk_estudio_usuario: number
    ){}
}