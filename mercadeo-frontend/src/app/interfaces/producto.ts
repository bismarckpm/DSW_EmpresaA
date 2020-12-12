import { Marca } from './marca';
import { Subcategoria } from './subcategoria';

export interface Producto  {
    id?: number;
    nombre: string;
    descripcion: string;
    idSubcategoria: number;
    idMarca: number;
}

export interface GetProducto  {
    _id?: number;
    _nombre: string;
    _descripcion: string;
    _idSubcategoria: number;
    _idMarca: number;
}