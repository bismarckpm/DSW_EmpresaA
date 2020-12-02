export class Rol_Privilegio{
    constructor(
        public codigo: number,
        public fk_rol: number,
        public fk_privilegio: number
    ){}
}