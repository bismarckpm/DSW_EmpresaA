export class Producto{
    constructor(
        public codigo: number,
        public nombre: string,
        public descripcion: string,
        public estado: string,
        public fk_marca: number,
        public fk_subcategoria: number
    ){}
}