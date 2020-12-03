import { Marca } from './marca';
import { Subcategoria } from './subcategoria';

export interface Producto  {
    id: number;
    nombre: string;
    descripcion: string;
    idSubcategoria: Subcategoria;
    idMarca: Marca;
}