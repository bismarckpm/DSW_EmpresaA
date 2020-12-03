export class Hijo{
    constructor(
        public id: number,
        public fechaNacimiento: string,
        public genero: string,
        public fk_datoUsuario: number
    ){}
}
