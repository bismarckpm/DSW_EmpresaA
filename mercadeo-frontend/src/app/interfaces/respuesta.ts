export interface Respuesta{
  id?: number;
  pregunta: string;
  estado: string;
  respuestaSimple?: string;
  respuestaMultiple?: string;
  respuestaAbierta?: string;
  escala?: string;
  verdaderoFalso?: string;
  usuarioDto: number;
  preguntaEstudioDto: number;
}

