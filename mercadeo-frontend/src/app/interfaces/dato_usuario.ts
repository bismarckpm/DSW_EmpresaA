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
   fechaNacimiento: string;
   estadoCivil: string;
   disponibilidadEnLinea: string;
   conCuantasPersonasVive: number;
   lugarDto: number;
   nivelAcademicoDto: number;
   ocupacionDto: number;
   nivelEconomicoDto: number;
}

