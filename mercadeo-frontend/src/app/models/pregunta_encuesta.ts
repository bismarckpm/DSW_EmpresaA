import { Subcategoria } from '../interfaces/subcategoria';
import { Usuario } from './usuario';

export class Pregunta_Encuesta{
    constructor(
        public id: number,
        public descripcion: string,
        public tipoPregunta: string,
        public estado: string,
        public fk_subcategoria: Subcategoria,
        public fk_usuario: Usuario
    ){}
}