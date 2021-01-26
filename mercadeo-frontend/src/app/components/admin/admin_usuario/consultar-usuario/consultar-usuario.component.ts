import { MatDialog, MatDialogConfig, MatDialogModule, _MatDialogBase } from '@angular/material/dialog';
import { DialogMostrarUsuarioComponent } from '../dialog-mostrar-usuario/dialog-mostrar-usuario.component';
import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { RolServicioService } from '../../../../services/rol-servicio.service';

import { ModificarUsuarioComponent } from '../../../modificar-usuario/modificar-usuario.component';

import { GetUsuario, GetUsuario2, Usuario } from '../../../../interfaces/usuario';
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { GetRol, Rol } from 'src/app/interfaces/rol';



@Component({
  selector: 'app-consultar-usuario',
  templateUrl: './consultar-usuario.component.html',
  styleUrls: ['./consultar-usuario.component.css']
})
export class ConsultarUsuarioComponent implements OnInit {


  /* busqueda: string = ''; */
  users: GetUsuario2[] = [];
  indice: number = 0;
  roles: GetRol[] = [];
  rolId = 0;
  isWait = false;

  constructor(private usuarioService: UsuarioServicioService, private navegacion: Router,
              private rol: RolServicioService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.busquedaUsuario();

    this.rol.onCargarRoles().subscribe(
      (roles: GetRol[]) => {
        this.roles = roles;
      }
    );
  }

  busquedaUsuario() {
    /* this.users = this.usuarioService.UserSearch(this.busqueda); */
    this.isWait=true;
    this.usuarioService.onBuscarUsuarioRol(this.rolId).subscribe(
     (usuarios: GetUsuario2[]) => {
       this.users = usuarios;
       this.isWait=false;
       console.log(this.users);
       console.log(this.users[0]._rol._id);
       /* console.log(this.users[0]._datoUsuario!._id); */
     }
   );

   /* this.rol.recibirRol(this.rolId).subscribe(
    (roles: Rol[]) => {
      this.roles = roles;
    }
  ); */
  }

   eliminarUsuario(indice: number) {
    this.usuarioService.onBorrarUsuario(indice);
    /* this.usuarioService.recibirUsuarios(this.busqueda).subscribe(
        (usuarios: Usuario[]) => {
          this.users = usuarios;
        }
      ); */
  }
  ///FUNCIONA
  openDialogU(user: GetUsuario2): void {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      _id: user._id,
      _nombreUsuario: user._nombreUsuario,
      _correo: user._correo,
      _estado: user._estado,
      _password: user._password,
      _rol: user._rol
      /* datoUsuario: user._datoUsuario */

    }

    const dialogRef = this.dialog.open(DialogMostrarUsuarioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe((result) => {
        console.log('Dialog closed');
      });
  }

}
