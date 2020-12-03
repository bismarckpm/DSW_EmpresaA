export class Presentacion{
    constructor(
        public codigo: number,
        public titulo: string,
        public caracteristicas: string,
        public estado: string,
        public fk_producto: number
    ){}
}