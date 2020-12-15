import { DialogMostrarUsuarioComponent } from './../../components/dialog-mostrar-usuario/dialog-mostrar-usuario.component';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { RolServicioService } from './../../services/rol-servicio.service';

import { ModificarUsuarioComponent } from './../modificar-usuario/modificar-usuario.component';

import { Usuario } from './../../models/usuario';
import { Component, OnInit } from '@angular/core';
import { faCheck, faEdit, faStop, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { Rol } from 'src/app/models/rol';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-consultar-usuario',
  templateUrl: './consultar-usuario.component.html',
  styleUrls: ['./consultar-usuario.component.css']
})
export class ConsultarUsuarioComponent implements OnInit {

  faCheck = faCheck;
  faTrash = faTrash;
  faWarning = faStop;
  /* busqueda: string = ''; */
  users: Usuario[] = [];
  indice: number = 0;
  roles: Rol[] = [];
  rolId = 0;
  constructor(private usuarioService: UsuarioServicioService, private navegacion: Router,
              private rol: RolServicioService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.rol.onCargarRoles().subscribe(
      (roles: Rol[]) => {
        this.roles = roles;
      }
    );
  }

  busquedaUsuario() {
    /* this.users = this.usuarioService.UserSearch(this.busqueda); */
      this.usuarioService.onBuscarUsuarioRol(this.rolId).subscribe(
     (usuarios: Usuario[]) => {
       this.users = usuarios;
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

  openDialogU(user: Usuario): void {

    const dialogRef = this.dialog.open(DialogMostrarUsuarioComponent, {
      width: '30rem',
      data: {id: user.id,
            nombreUsuario: user.nombreUsuario,
            correo: user.correo,
            estado: user.estado,
            password: user.password,
            rolDto: user.rolDto,
            datoUsuarioDto: user.datoUsuarioDto
            }
      });

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }

}
