import { GetLugar, Lugar } from "./lugar";
import { GetNivel_Academico, Nivel_Academico } from "./nivel_academico";
import { GetNivel_Economico, Nivel_Economico } from "./nivel_economico";
import { GetOcupacion, Ocupacion } from "./ocupacion";

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
   estado: string;
   disponibilidadEnLinea: string;
   conCuantasPersonasVive: number;
   medioComunicacion?: string;
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
  _medioComunicacion: string;
  _lugar: GetLugar;
  _nivelAcademico: GetNivel_Academico;
  _ocupacion: GetOcupacion;
  _nivelEconomico: GetNivel_Economico;
}
