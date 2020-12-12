export interface Presentacion  {
    id: number;
    titulo: string;
    caracteristicas: string;
    estado: string;
}

export interface GetPresentacion  {
    _id: number;
    _titulo: string;
    _caracteristicas: string;
    _estado: string;
}