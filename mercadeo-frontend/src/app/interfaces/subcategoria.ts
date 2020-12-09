import { Categoria } from './categoria';

export interface Subcategoria  {
    id: number;
    nombre: string;
    estado: string;
    descripcion: string;
    idCategoria: number;
}