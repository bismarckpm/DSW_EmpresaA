import { Lugar } from "./lugar";
import { Nivel_Academico } from "./nivel_academico";
import { Nivel_Economico } from "./nivel_economico";
import { Ocupacion } from "./ocupacion";

export interface Dato_Usuario{
   id?: number;
   cedula: string;
   primerNombre: string;
   segundoNombre: string;
   primerApellido: string;
   segundoApellido: string;
   sexo: string;
   fechaNacimiento: Date;
   estadoCivil: string;
   disponibilidadEnLinea: string;
   conCuantasPersonasVive: number;
   lugarDto: number;
   nivelAcademicoDto: number;
   ocupacionDto: number;
   nivelEconomicoDto: number;
}

export interface GetDato_Usuario{
  _id: number;
  _cedula: string;
  _primerNombre: string;
  _segundoNombre: string;
  _primerApellido: string;
  _segundoApellido: string;
  _sexo: string;
  _fechaNacimiento: Date;
  _estadoCivil: string;
  _disponibilidadEnLinea: string;
  _conCuantasPersonasVive: number;
  _lugarDto: number;
  _nivelAcademicoDto: number;
  _ocupacionDto: number;
  _nivelEconomicoDto: number;
}
