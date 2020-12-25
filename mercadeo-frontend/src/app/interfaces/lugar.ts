export interface Lugar{
  id?: number;
  nombre: string;
  tipo: string;
  categoriaSocioEconomica: string;
  lugarDto: number;
}

export interface GetLugar{
  _id?: number;
  _nombre: string;
  _tipo: string;
  _categoriaSocioEconomica: string;
  _lugarDto: number;
}
