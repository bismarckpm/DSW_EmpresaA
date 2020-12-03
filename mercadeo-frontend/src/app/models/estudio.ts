import { Usuario } from './usuario';
import { Solicitud_Estudio } from './solicitud_estudio';
export class Estudio{
  constructor(
    id: number
  )

  constructor(
     id: number,
     nombre: string,
     tipoInstrumento: string,
     fechaInicio: Date,
     fechaFinal: Date,
     status: string,
     estado: string,
     estudioSolicitudDto: Solicitud_Estudio,
     estudioUsuarioDto: Usuario
  )

    constructor(
        public id?: number,
        public nombre?: string,
        public tipoInstrumento?: string,
        public fechaInicio?: Date,
        public fechaFinal?: Date,
        public status?: string,
        public estado?: string,
        public estudioSolicitudDto?: Solicitud_Estudio,
        public estudioUsuarioDto?: Usuario
    ){}
}
