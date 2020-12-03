export class Telefono{
    constructor(
        public id: number,
        public numero: string,
        public fk_datoUsuario: number
    ){}
}
