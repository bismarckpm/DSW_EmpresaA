import { Dato_Usuario } from "./dato_usuario";
import { Rol } from "./rol";

export class Usuario{
  constructor(
    public id: number,
    public nombreUsuario: string,
    public correo: string,
    public estado: string,
    public codigoRecuperacion: string,
    public password: string,
    public rolDto: Rol,
    public datoUsuarioDto: Dato_Usuario
){}
}
