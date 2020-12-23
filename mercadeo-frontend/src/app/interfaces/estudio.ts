import { Usuario } from '../interfaces/usuario';
import { Solicitud_Estudio } from '../interfaces/solicitud_estudio';
export interface Estudio{
  id?: number;
  nombre: string;
  tipoInstrumento: string;
  fechaInicio: Date;
  fechaFinal: Date;
  status: string;
  estado: string;
  estudioSolicitudDto: number;
  estudioUsuarioDto: number;
}
