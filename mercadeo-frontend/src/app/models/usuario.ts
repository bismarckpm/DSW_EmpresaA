import { Dato_Usuario } from "./dato_usuario";
import { Rol } from "./rol";

export class Usuario{
  constructor(
    id: number
  )

  constructor(
     id: number,
     nombreUsuario: string,
     correo: string,
     estado: string,
     codigoRecuperacion: string,
     password: string,
      rolDto: Rol,
     datoUsuarioDto: Dato_Usuario
)


  constructor(
    public id?: number,
    public nombreUsuario?: string,
    public correo?: string,
    public estado?: string,
    public codigoRecuperacion?: string,
    public password?: string,
    public rolDto?: Rol,
    public datoUsuarioDto?: Dato_Usuario
){}
}
