import { Ocupacion } from './ocupacion';
import { Usuario } from './usuario';
import { Producto } from './producto';
import { Nivel_Economico } from './nivel_economico';
export class Solicitud_Estudio{
  constructor(
    id: number
  )

  constructor(
        public id?: number,
        public descripcionSolicitud?: string,
        public generoPoblacional?: string,
        public fechaPeticion?: string,
        public edadMinimaPoblacion?: string,
        public edadMaximaPoblacion?: string,
        public estado?: string,
        public cantidadHijos?: number,
        public generoHijos?: string,
        public edadMinimaHijos?: string,
        public edadMaximaHijos?: string,
        public conCuantasPersonasVive?: number,
        public disponibilidadEnLinea?: string,
        public solicitudNivelEconomicoDto?: Nivel_Economico,
        public solicitudProductoDto?: Producto,
        public solicitudUsuarioDto?: Usuario,
        public solicitudOcupacionDto?: Ocupacion
    ){}
}
