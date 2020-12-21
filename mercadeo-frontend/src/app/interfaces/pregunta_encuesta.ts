import { Usuario } from 'src/app/interfaces/usuario';
import { Subcategoria } from '../models/subcategoria';
export class Pregunta_Encuesta{
    constructor(
      id: number
    )

    constructor(
       id: number,
       descripcion: string,
       tipoPregunta: string,
       estado: string,
       subcategoriaDto: Subcategoria,
       usuarioDto: Usuario
    )
    constructor(
        public id?: number,
        public descripcion?: string,
        public tipoPregunta?: string,
        public estado?: string,
        public subcategoriaDto?: Subcategoria,
        public usuarioDto?: Usuario
    ){}
}
