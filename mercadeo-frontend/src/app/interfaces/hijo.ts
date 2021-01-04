import { Dato_Usuario, GetDato_Usuario } from './dato_usuario';
export interface Hijo{
  id?: number;
  fechaNacimiento: string;
  genero: string;
  estado: string;
  datoUsuarioDto?: number;
}

export interface GetHijo{
  _id: number;
  _fechaNacimiento: string;
  _genero: string;
  _estado: string;
  _datoUsuarioDto: GetDato_Usuario;
}
