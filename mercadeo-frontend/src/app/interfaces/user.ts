export class User{
    constructor(
        public id: number,
        public nombreUsuario: string,
        public correo: string,
        public estado: string,
        public idRol: number,
    ){}
}