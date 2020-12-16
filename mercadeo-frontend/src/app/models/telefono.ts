import { Dato_Usuario } from './dato_usuario';
export class Telefono{
    constructor(
      id: number,
    )

    constructor(
        id: number,
        numero: string,
        datoUsuarioDto: Dato_Usuario
    )

    constructor(
        public id?: number,
        public numero?: string,
        public datoUsuarioDto?: Dato_Usuario
    ){}
}
