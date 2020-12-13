

export class Subcategoria{
    constructor(
        public id: number,
        public nombre: string,
        public descripcion: string,
        public estado: string,
        public fk_categoria: number
    ){}
}