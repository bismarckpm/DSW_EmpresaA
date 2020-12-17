export class Usuario{
    constructor(
        public id: number,
        public nombreUsuario: string,
        public correo: string,
        public estado: string,
        public codigoRecuperacion: string,
        public password: string,
        public rolDto: number,
        public datoUsuarioDto: number
    ){}
}