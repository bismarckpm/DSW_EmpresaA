import { Categoria, GetCategoria } from './categoria';

export interface Subcategoria  {
    id: number;
    nombre: string;
    estado: string;
    descripcion: string;
    categoriaDto: number;
}

export interface GetSubcategoria  {
    _id: number;
    _nombre: string;
    _estado: string;
    _descripcion: string;
    _categoria: GetCategoria;
}