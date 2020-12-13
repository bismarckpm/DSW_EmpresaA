export class Solicitud_Estudio{
    constructor(
        public id: number,
        public descripcionSolicitud: string,
        public generoPoblacional: string,
        public fechaPeticion: string,
        public edadMinimaPoblacion: string,
        public edadMaximaPoblacion: string,
        public estado: string,
        public cantidadHijos: number,
        public generoHijos: string,
        public edadMinimaHijos: string,
        public edadMaximaHijos: string,
        public conCuantasPersonasVive: number,
        public disponibilidadEnLinea: string,
        public fk_nivelEconomico: number,
        public fk_producto: number,
        public fk_usuario: number,
        public fk_ocupacion: number
    ){}
}