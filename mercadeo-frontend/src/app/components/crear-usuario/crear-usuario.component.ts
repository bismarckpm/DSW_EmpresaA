import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { Usuario } from 'src/app/interfaces/usuario';
import { RolServicioService } from './../../services/rol-servicio.service';
import { Rol } from 'src/app/interfaces/rol';
import { Component, OnInit } from '@angular/core';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent implements OnInit {

  roles: Rol[] = [];
  usuarios: Usuario[] = [];
  codigo: number = 0;
  nombreU: string = '';
  correo: string = '';
  passw: string = '';
  estado: string = '';
  codigoR: string = '';
  fkrol: number = 0;
  fkdatoU: number = 0;
  constructor(private rol: RolServicioService, private user: UsuarioServicioService) { }

  ngOnInit(): void {
    this.rol.onCargarRoles().subscribe(
      (roles: Rol[]) => {
        this.roles = roles;
      }
    );
    this.user.traerUsuarios().subscribe(
      (users: Usuario[]) => {
        this.usuarios = users;
      }
    )
  }

  createUsuario() {
    let enc = new Dato_Usuario(this.fkdatoU);
    let rol = new Rol(this.fkrol);

    console.log(this.usuarios.slice(-1)[0].id);
    let usu = new Usuario(this.usuarios.slice(-1)[0].id! + 1, this.nombreU, this.correo,
                this.estado, this.codigoR, this.passw, rol);

    this.user.onGuardarUser(usu);
  }
}
