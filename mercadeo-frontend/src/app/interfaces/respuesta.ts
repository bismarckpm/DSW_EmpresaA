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

export interface ListaRespuesta{
  _descripcion: string;
  _estado: string;
  _valor?: string;
  _preguntaAux?: string;
}

export interface GetRespuesta{
  _enunciado: string;
  _tipoPregunta: string;
  _estado: string;
  _listaRespuestas: ListaRespuesta[];
}
