import { Dato_Usuario } from './dato_usuario';
export interface Telefono{
  id?: number;
  numero: string;
  estado: string;
  datoUsuarioDto?: number;
}

export interface GetTelefono{
  _id: number;
  _numero: string;
  _estado: string;
  _datoUsuarioDto?: number;
}
