import { ConsultarUsuarioComponent } from './../consultar-usuario/consultar-usuario.component';
import { Dato_Usuario } from './../../models/dato_usuario';
import { RolServicioService } from './../../services/rol-servicio.service';
import { Component, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Usuario } from 'src/app/models/usuario';
import { Rol } from 'src/app/models/rol';

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
  roless: Rol[] = [];
  rols = new Rol(this.fkRol);
  dUsu = new Dato_Usuario(this.fkDu);
  constructor(@Inject(MAT_DIALOG_DATA) public data: Usuario, private rol: RolServicioService) { }

  ngOnInit(): void {

    this.id = this.data.id!;
    this.nombreU = this.data.nombreUsuario!;
    this.correo = this.data.correo!;
    this.estado = this.data.estado!;
    this.codR = this.data.codigoRecuperacion!;
    this.pass = this.data.password!;
    this.rols = this.data.rolDto!;
    this.dUsu = this.data.datoUsuarioDto!;

    this.rol.onCargarRol(this.rols.id!).subscribe(
      (roles: Rol[]) => {
        this.roless = roles
        this.r = this.roless[0].nombre!;
      }
    );

  }

}
