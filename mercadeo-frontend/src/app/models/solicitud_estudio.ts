export class Solicitud_Estudio{
    constructor(
        public codigo: number,
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
        public fk_solicitud_nivelEconomico: number,
        public fk_solicitud_producto: number,
        public fk_solicitud_usuario: number,
        public fk_solicitud_ocupacion: number
    ){}
}