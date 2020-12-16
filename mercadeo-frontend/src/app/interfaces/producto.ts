import { GetMarca } from './marca';
import { GetSubcategoria } from './subcategoria';
import { GetTipo } from './tipo';
import { GetPresentacion } from './presentacion';

export interface Producto  {
    id?: number;
    nombre: string;
    descripcion: string;
    estado: string;
    subcategoriaDto: number;
    marcaDto: number;
}

export interface GetProducto  {
    _id: number;
    _nombre: string;
    _descripcion: string;
    _estado: string;
    _subcategoria: GetSubcategoria;
    _marca: GetMarca;
}


export interface ProductoTipoPresentacion{
    id: number,
    estado: string;
    productoDto: number;
    presentacionDto: number;
    tipoDto: number;
}

export interface GetProductoTipoPresentacion  {
    _id: number,
    _estado: string;
    _producto: GetProducto;
    _presentacion: GetPresentacion;
    _tipo: GetTipo;
}