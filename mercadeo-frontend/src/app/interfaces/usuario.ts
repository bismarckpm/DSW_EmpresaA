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
   datoUsuarioDto?: number;
}

export interface GetUsuario{
  _id: number;
  _nombreUsuario: string;
  _correo: string;
  _estado: string;
  _codigoRecuperacion: string;
  _password: string;
  _rolDto: number;
  _datoUsuarioDto: number;
}
