import { Usuario } from './usuario';
import { Solicitud_Estudio } from './solicitud_estudio';
export class Estudio{
  constructor(
    id: number
  )

  constructor(
     id?: number,
     nombre?: string,
     tipoInstrumento?: string,
     fechaInicio?: string,
     fechaFin?: string,
     estatus?: string,
     estado?: string,
     estudioSolicitudDto?: Solicitud_Estudio,
     estudioUsuarioDto?: Usuario
  )

    constructor(
        public id?: number,
        public nombre?: string,
        public tipoInstrumento?: string,
        public fechaInicio?: string,
        public fechaFin?: string,
        public estatus?: string,
        public estado?: string,
        public estudioSolicitudDto?: Solicitud_Estudio,
        public estudioUsuarioDto?: Usuario
    ){}
}
