export class Solicitud_Estudio{
    constructor(
        public id: number,
        public descripcionSolicitud: string,
        public generoPoblacional: string,
        public fechaPeticion: Date,
        public edadMinimaPoblacion: string,
        public edadMaximaPoblacion: string,
        public estado: string,
        public cantidadHijos: number,
        public generoHijos: string,
        public edadMinimaHijos: string,
        public edadMaximaHijos: string,
        public conCuantasPersonasVive: number,
        public disponibilidadEnLinea: string,
        public nivelEconomicoDto: number,
        public productoDto: number,
        public usuarioDto: number,
        public ocupacionDto: number
    ){}
}