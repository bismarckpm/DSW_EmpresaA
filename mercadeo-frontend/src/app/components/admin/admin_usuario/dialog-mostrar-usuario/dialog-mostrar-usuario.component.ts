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
  roless: GetRol[] = [];
  /* rols: Rol = {id: this.fkRol}; */
  /* dUsu = new Dato_Usuario(this.fkDu); */
  constructor(@Inject(MAT_DIALOG_DATA) public data: GetUsuario2, private rol: RolServicioService) { }

  ngOnInit(): void {

    this.id = this.data.id!;
    this.nombreU = this.data.nombreUsuario;
    this.correo = this.data.correo;
    this.estado = this.data.estado;
    this.codR = this.data.codigoRecuperacion;
    this.pass = this.data.password;
    this.fkRol = this.data.idRol;
    this.fkDu = this.data.datoUsuarioDto!;
    console.log(this.fkRol);

    this.rol.onCargarRol(this.fkRol).subscribe(
      (roles: GetRol) => {
        this.roless.push(roles);
        console.log(this.roless);
        this.r = this.roless[0]._nombre;
        console.log(this.r);
      }
    );

  }

}
