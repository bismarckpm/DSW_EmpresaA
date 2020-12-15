export class Dato_Usuario{
    constructor(
        public id: number,
        public cedula: string,
        public primerNombre: string,
        public segundoNombre: string,
        public primerApellido: string,
        public segundoApellido: string,
        public sexo: string,
        public fechaNacimiento: any,
        public estadoCivil: string,
        public disponibilidadEnLinea: string,
        public conCuantasPersonasVive: number,
        public lugarDto: number,
        public nivelAcademicoDto: number,
        public ocupacionDto: number,
        public nivelEconomicoDto: number
    ){}
}
