export class Telefono{
    constructor(
        public codigo: number,
        public numero: string,
        public fk_datoUsuario: number
    ){}
}