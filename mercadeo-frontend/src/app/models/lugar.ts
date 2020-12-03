export class Lugar{
    constructor(
        public codigo: number,
        public nombre: string,
        public tipo: string,
        public categoriaSocioEconomica: string,
        public fk_lugar: number
    ){}
}