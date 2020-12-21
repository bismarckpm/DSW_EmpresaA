import { Dato_Usuario } from './dato_usuario';
export class Hijo{
    constructor(
      id: number
    )
    constructor(
       id: number,
       fechaNacimiento: string,
       genero: string,
       datoUsuarioDto: Dato_Usuario
    )

    constructor(
        public id?: number,
        public fechaNacimiento?: string,
        public genero?: string,
        public datoUsuarioDto?: Dato_Usuario
    ){}
}
