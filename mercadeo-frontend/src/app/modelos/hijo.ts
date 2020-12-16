export class Hijo{
    constructor(
        public codigo: number,
        public fechaNacimiento: string,
        public genero: string,
        public fk_datoUsuario: number
    ){}
}