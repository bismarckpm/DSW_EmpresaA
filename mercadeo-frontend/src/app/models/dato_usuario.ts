import { Lugar } from "./lugar";
import { Nivel_Academico } from "./nivel_academico";
import { Nivel_Economico } from "./nivel_economico";
import { Ocupacion } from "./ocupacion";

export class Dato_Usuario{
  constructor(
    id: number
  )

  constructor(
    id: number,
    cedula: string,
    primerNombre: string,
    segundoNombre: string,
    primerApellido: string,
    segundoApellido: string,
    sexo: string,
    fechaNacimiento: any,
    estadoCivil: string,
    disponibilidadEnLinea: string,
    conCuantasPersonasVive: number,
    lugarDto: Lugar,
    nivelAcademicoDto: Nivel_Academico,
    ocupacionDto: Ocupacion,
    nivelEconomicoDto: Nivel_Economico
  )

 constructor(
       public id?: number,
       public cedula?: string,
       public primerNombre?: string,
       public segundoNombre?: string,
       public primerApellido?: string,
       public segundoApellido?: string,
       public sexo?: string,
       public fechaNacimiento?: any,
       public estadoCivil?: string,
       public disponibilidadEnLinea?: string,
       public conCuantasPersonasVive?: number,
       public lugarDto?: Lugar,
       public nivelAcademicoDto?: Nivel_Academico,
       public ocupacionDto?: Ocupacion,
       public nivelEconomicoDto?: Nivel_Economico
   ){}
}
