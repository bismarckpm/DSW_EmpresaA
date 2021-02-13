import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { Usuario } from 'src/app/interfaces/usuario';
import { RolServicioService } from '../../../../services/rol-servicio.service';
import { GetRol, Rol } from 'src/app/interfaces/rol';
import { Component, OnInit } from '@angular/core';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

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
  rppassw: string = '';
  estado: string = '';
  codigoR: string = '';
  fkrol: number = 0;
  fkdatoU: number = 0;
  datoUfk = 0;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private rol: RolServicioService, private user: UsuarioServicioService,
              private route: ActivatedRoute, private _snackBar: MatSnackBar,
              private navegacion: Router) { }

  ngOnInit(): void {
    this.datoUfk = Number(this.route.snapshot.params['fk_datoUsuario']);
    console.log(this.datoUfk);
    this.rol.onCargarRoles().subscribe(
      (roles) => {
        this.roles = roles.objeto;

        this.roles.splice(3, 1);
      }
    );

  }

  createUsuario() {

  if (this.passw === this.rppassw){

    if (this.datoUfk === 0){
      let usu: Usuario = {
        nombreUsuario: this.nombreU,
        correo: this.correo,
        estado: 'A',
        codigoRecuperacion: this.codigoR,
        password: this.passw,
        rolDto: this.fkrol,
      };

      this.user.onGuardarUser(usu);
    }
    else if (this.datoUfk !== 0){

      let usu: Usuario = {
        nombreUsuario: this.nombreU,
        correo: this.correo,
        estado: 'A',
        codigoRecuperacion: this.codigoR,
        password: this.passw,
        rolDto: 4,
        datoUsuarioDto: this.datoUfk,
      };

      this.user.onGuardarUser(usu);
    }

    this._snackBar.open('Usuario Ingresado Exitosamente', undefined, {
      duration: 1000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
  });

    this.navegacion.navigate(['admin']);

} else if (this.passw !== this.rppassw){


  this._snackBar.open('Contraseñas no son iguales', undefined, {
    duration: 2000,
    horizontalPosition: this.horizontalPosition,
    verticalPosition: this.verticalPosition,
    panelClass: 'red-snackbar',
});
}


}
}
