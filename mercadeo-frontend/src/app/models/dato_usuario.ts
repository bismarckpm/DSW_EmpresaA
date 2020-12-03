export class Dato_Usuario{
    constructor(
        public codigo: number,
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
        public fk_lugar: number,
        public fk_nivelAcademico: number,
        public fk_ocupacion: number,
        public fk_nivelEconomico: number
    ){}
}