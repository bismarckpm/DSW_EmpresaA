import { GetDato_Usuario } from './../../../../interfaces/dato_usuario';
import { GetUsuario2 } from './../../../../interfaces/usuario';
import { ConsultarUsuarioComponent } from '../consultar-usuario/consultar-usuario.component';
import { Dato_Usuario } from '../../../../interfaces/dato_usuario';
import { RolServicioService } from '../../../../services/rol-servicio.service';
import { Component, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Usuario } from 'src/app/interfaces/usuario';
import { GetRol, Rol } from 'src/app/interfaces/rol';

@Component({
  selector: 'app-dialog-mostrar-usuario',
  templateUrl: './dialog-mostrar-usuario.component.html',
  styleUrls: ['./dialog-mostrar-usuario.component.css']
})
export class DialogMostrarUsuarioComponent implements OnInit {

  id = 0;
  nombreU = '';
  correo = '';
  estado = '';
  codR = '';
  pass = '';
  fkRol = 0;
  fkDu = 0;
  r = '';
  rols: GetRol[] = [];
  roless: GetRol[] = [];
  dUsuario: GetDato_Usuario[] = [];
  /* rols: Rol = {id: this.fkRol}; */
  /* dUsu = new Dato_Usuario(this.fkDu); */
  constructor(@Inject(MAT_DIALOG_DATA) public data: GetUsuario2, private rol: RolServicioService) { }

  ngOnInit(): void {

    this.id = this.data._id;
    this.nombreU = this.data._nombreUsuario;
    console.log(this.nombreU);
    this.correo = this.data._correo;
    this.estado = this.data._estado;
    this.codR = this.data._codigoRecuperacion;
    this.pass = this.data._password;
    console.log(this.pass);
    this.rols.push(this.data._rol);
    this.r = this.rols[0]._nombre;
    console.log(this.rols);
    /* this.dUsuario.push(this.data._datoUsuario); */
    /* console.log(this.dUsuario); */

    /* this.rol.onCargarRol(this.fkRol).subscribe(
      (roles: GetRol) => {
        this.roless.push(roles);
        console.log(this.roless);
        this.r = this.roless[0]._nombre;
        console.log(this.r);
      }
    ); */

  }

}
