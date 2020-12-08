import { Producto } from './producto';
import { Presentacion } from './presentacion';

export interface ProductoPresentacion  {
    idProducto: Producto;
    idPresentacion: Presentacion;
}