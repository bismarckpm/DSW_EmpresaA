export class Region_Estudio{
    constructor(
        public codigo: number,
        public fk_lugar: number,
        public fk_solicitudEstudio: number
    ){}
}