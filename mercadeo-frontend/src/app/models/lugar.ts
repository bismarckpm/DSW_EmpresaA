export class Lugar{
    constructor(
        public id: number,
        public nombre: string,
        public tipo: string,
        public categoriaSocioEconomica: string,
        public fk_lugar: number
    ){}
}
