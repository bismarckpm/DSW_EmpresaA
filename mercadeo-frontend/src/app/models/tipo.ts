export class Tipo{
    constructor(
        public codigo: number,
        public nombre: string,
        public descripcion: string,
        public estado: string,
        public fk_producto: number
    ){}
}