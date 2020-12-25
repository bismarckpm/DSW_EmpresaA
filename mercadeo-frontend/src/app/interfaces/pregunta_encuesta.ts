import { Usuario } from 'src/app/interfaces/usuario';

export interface Pregunta_Encuesta{
  id?: number;
  descripcion: string;
  tipoPregunta: string;
  estado: string;
  subcategoriaDto: number;
  usuarioDto: number;
}
