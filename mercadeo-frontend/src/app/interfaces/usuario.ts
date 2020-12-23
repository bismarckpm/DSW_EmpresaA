import { Dato_Usuario } from "./dato_usuario";
import { Rol } from "./rol";

export interface Usuario{
   id?: number;
   nombreUsuario: string;
   correo: string;
   estado: string;
   codigoRecuperacion: string;
   password: string;
   rolDto: number;
   datoUsuarioDto: number;
}
