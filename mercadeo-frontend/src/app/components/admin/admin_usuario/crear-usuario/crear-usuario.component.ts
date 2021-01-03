import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { Usuario } from 'src/app/interfaces/usuario';
import { RolServicioService } from '../../../../services/rol-servicio.service';
import { GetRol, Rol } from 'src/app/interfaces/rol';
import { Component, OnInit } from '@angular/core';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent implements OnInit {


  roles: GetRol[] = [];
  usuarios: Usuario[] = [];
  codigo: number = 0;
  nombreU: string = '';
  correo: string = '';
  passw: string = '';
  estado: string = '';
  codigoR: string = '';
  fkrol: number = 0;
  fkdatoU: number = 0;
  datoUfk = 0;
  constructor(private rol: RolServicioService, private user: UsuarioServicioService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.datoUfk = this.route.snapshot.params['fk_datoUsuario'];
    console.log(this.datoUfk);
    this.rol.onCargarRoles().subscribe(
      (roles: GetRol[]) => {
        this.roles = roles;
      }
    );
    /* this.user.traerUsuarios().subscribe(
      (users: Usuario[]) => {
        this.usuarios = users;
      }
    ) */
  }

  createUsuario() {
    /* let enc = new Dato_Usuario(this.fkdatoU);
    let rol = new Rol(this.fkrol);
 */
    /* console.log(this.usuarios.slice(-1)[0].id); */
    if (this.datoUfk === 0){
      let usu: Usuario = {
        nombreUsuario: this.nombreU,
        correo: this.correo,
        estado: this.estado,
        codigoRecuperacion: this.codigoR,
        password: this.passw,
        rolDto: this.fkrol,
      };

      this.user.onGuardarUser(usu);
    }
    else{

      let usu: Usuario = {
        nombreUsuario: this.nombreU,
        correo: this.correo,
        estado: this.estado,
        codigoRecuperacion: this.codigoR,
        password: this.passw,
        rolDto: 4,
        datoUsuarioDto: Number(this.datoUfk) 
      };

      this.user.onGuardarUser(usu);
    }

  }
}
