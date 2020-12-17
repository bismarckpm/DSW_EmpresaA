export class Medio_Comunicacion{
    constructor(
        public codigo: number,
        public nombre: string,
        public fk_datoUsuario: number,
        public fk_solicitudEstudio: number
    ){}
}