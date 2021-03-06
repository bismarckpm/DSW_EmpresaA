import { Dato_Usuario, GetDato_Usuario } from "./dato_usuario";
import { GetRol, Rol } from "./rol";

export interface Usuario{
   id?: number;
   nombreUsuario: string;
   correo: string;
   estado: string;
   codigoRecuperacion: string;
   password?: string;
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

export interface GetUsuario2{
  _id: number;
  _nombreUsuario: string;
  _correo: string;
  _estado: string;
  _codigoRecuperacion: string;
  _password: string;
  _rol: GetRol;
  _datoUsuario?: GetDato_Usuario;
}
