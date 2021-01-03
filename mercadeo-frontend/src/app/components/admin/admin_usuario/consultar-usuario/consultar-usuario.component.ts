import { MatDialog, MatDialogConfig, MatDialogModule, _MatDialogBase } from '@angular/material/dialog';
import { DialogMostrarUsuarioComponent } from '../dialog-mostrar-usuario/dialog-mostrar-usuario.component';
import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { RolServicioService } from '../../../../services/rol-servicio.service';

import { ModificarUsuarioComponent } from '../../../modificar-usuario/modificar-usuario.component';

import { GetUsuario, Usuario } from '../../../../interfaces/usuario';
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
  users: Usuario[] = [];
  indice: number = 0;
  roles: GetRol[] = [];
  rolId = 0;

  constructor(private usuarioService: UsuarioServicioService, private navegacion: Router,
              private rol: RolServicioService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.rol.onCargarRoles().subscribe(
      (roles: GetRol[]) => {
        this.roles = roles;
      }
    );
  }

  busquedaUsuario() {
    /* this.users = this.usuarioService.UserSearch(this.busqueda); */
      this.usuarioService.onBuscarUsuarioRol(this.rolId).subscribe(
     (usuarios: Usuario[]) => {
       this.users = usuarios;
       console.log(this.users);
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
  openDialogU(user: Usuario): void {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: user.id,
      nombreUsuario: user.nombreUsuario,
      correo: user.correo,
      estado: user.estado,
      password: user.password,
      rolDto: user.rolDto,
      datoUsuarioDto: user.datoUsuarioDto

    }

    const dialogRef = this.dialog.open(DialogMostrarUsuarioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe((result) => {
        console.log('Dialog closed');
      });
  }

}
