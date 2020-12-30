export interface Respuesta{
  id?: number;
  pregunta: string;
  estado: string;
  respuestaSimple?: string;
  respuestaMultiple?: string;
  respuertaAbierta?: string;
  escala?: string;
  verdaderoFalso?: string;
  usuarioDto: number;
  preguntaEstudioDto: number;
}

