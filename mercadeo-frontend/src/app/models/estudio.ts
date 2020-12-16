export class Estudio{
    constructor(
        public codigo: number,
        public nombre: string,
        public tipoInstrumento: string,
        public fechaInicio: string,
        public fechaFinal: string,
        public estatus: string,
        public estado: string,
        public fk_estudio_solicitud: number,
        public fk_estudio_usuario: number
    ){}
}