import { Categoria } from './categoria';

export interface Subcategoria  {
    id: number;
    nombre: string;
    estado: string;
    descripcion: string;
    idCategoria: number;
}

export interface GetSubcategoria  {
    _id: number;
    _ombre: string;
    _estado: string;
    _descripcion: string;
    _idCategoria: number;
}