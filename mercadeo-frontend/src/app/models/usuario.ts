export class Usuario{
    constructor(
        public codigo: number,
        public nombreUsuario: string,
        public correo: string,
        public estado: string,
        public codigoRecuperacion: string,
        public password: string,
        public fk_rol: number,
        public fk_datoUsuario: number
    ){}
}