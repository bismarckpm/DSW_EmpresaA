import { RolServicioService } from './../../services/rol-servicio.service';
import { EncuestadoServicioService } from './../../services/encuestado-servicio.service';
import { Dato_Usuario } from '../../interfaces/dato_usuario';
import { ConsultarUsuarioComponent } from '../admin/admin_usuario/consultar-usuario/consultar-usuario.component';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GetUsuario, GetUsuario2, Usuario } from 'src/app/interfaces/usuario';
import { GetRol, Rol } from 'src/app/interfaces/rol';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-modificar-usuario',
  templateUrl: './modificar-usuario.component.html',
  styleUrls: ['./modificar-usuario.component.css']
})
export class ModificarUsuarioComponent implements OnInit {

  @Input() indice: number= 0;
  @Input() indiceEn: number= 0;
  nombreU: string = '';
  correo: string = '';
  estado: string = '';
  codigoR: string = '';
  password: string = '';
  fkRol: number = 0;
  fkUsu: number = 0;
  usuario: GetUsuario2[] = [];
  encuestado: Dato_Usuario[] = [];
  roles: GetRol[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private route: ActivatedRoute, private usuarioService: UsuarioServicioService,
              private datoUsuario: EncuestadoServicioService, private rol: RolServicioService,
              private _snackBar: MatSnackBar,private navegacion: Router) { }

  ngOnInit(): void {
    this.indice = Number(this.route.snapshot.params['idUsuario']);
    this.indiceEn = Number(this.route.snapshot.params['idEncuestado']);

    console.log(this.indice);
    console.log(this.indiceEn);

    this.usuarioService.onBuscarUsuario(this.indice).subscribe(
      (user) => {
        console.log(user);
        this.usuario.push(user.objeto);
        console.log(this.usuario);
        this.nombreU = this.usuario[0]._nombreUsuario;
        this.correo = this.usuario[0]._correo;
        this.fkRol = this.usuario[0]._rol._id;
        console.log(this.fkRol);
        this.estado = this.usuario[0]._estado;
        this.codigoR = this.usuario[0]._codigoRecuperacion;
        this.password = this.usuario[0]._password;
      }
    );

    this.rol.onCargarRoles().subscribe(
        (roles) => {
          this.roles = roles.objeto;
          console.log(roles);
          this.roles.splice(1, 1);
          this.roles.splice(2, 1);
          console.log(this.roles);
        }
      );


  }

  actualizarUsuario() {

  if (this.indiceEn === 0){

    let user: Usuario = {
      nombreUsuario: this.nombreU,
      correo: this.correo,
      estado: this.estado,
      codigoRecuperacion: this.codigoR,
      rolDto: this.fkRol,
    };

    console.log('actualizo', user)
    this.usuarioService.onModificarUsuario(this.indice, user);

  }else{

    let user: Usuario = {
      nombreUsuario: this.nombreU,
      correo: this.correo,
      estado: this.estado,
      codigoRecuperacion: this.codigoR,
      rolDto: this.fkRol,
      datoUsuarioDto: this.indiceEn
    };
    
    console.log('actualizo', user)
    this.usuarioService.onModificarUsuario(this.indice, user);
  }

  this._snackBar.open('Usuario Modificado Exitosamente', undefined, {
    duration: 1000,
    horizontalPosition: this.horizontalPosition,
    verticalPosition: this.verticalPosition,
});

  this.navegacion.navigate(['consultarpersona']);

  }


}
