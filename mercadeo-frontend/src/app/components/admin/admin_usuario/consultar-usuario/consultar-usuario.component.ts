import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';
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

  users: GetUsuario2[] = [];
  indice: number = 0;
  roles: GetRol[] = [];
  rolId = 0;
  isWait = false;

  constructor(private usuarioService: UsuarioServicioService, private navegacion: Router,
              private rol: RolServicioService, public dialog: MatDialog,
              private datoU: EncuestadoServicioService) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.busquedaUsuario();
    }, 1000);

    this.rol.onCargarRoles().subscribe(
      (roles: GetRol[]) => {
        this.roles = roles;
      }
    );
  }

  busquedaUsuario() {

    this.isWait=true;
    this.usuarioService.onBuscarUsuarioRol(this.rolId).subscribe(
     (usuarios: GetUsuario2[]) => {
       this.users = usuarios;
       this.isWait=false;
       console.log(this.users);
       console.log(this.users[0]._rol._id);
     }
   );
  }

   eliminarUsuario(indice: number, usuario: GetUsuario2) {

    console.log(usuario._datoUsuario);
    if(usuario._datoUsuario! === null){
      let user: Usuario = {
        id: indice,
        nombreUsuario: usuario._nombreUsuario,
        correo: usuario._correo,
        estado: 'I',
        codigoRecuperacion: usuario._codigoRecuperacion,
        password: usuario._password,
        rolDto: usuario._rol._id,
        datoUsuarioDto: null!
      }
      console.log('usuario sin foranea de dato usuario');
      console.log(user);

      this.usuarioService.onBorrarUsuario(indice, user);

      setTimeout(() => {
          this.busquedaUsuario();
        }, 1000);
    }

    else{
      let user: Usuario = {
        id: indice,
        nombreUsuario: usuario._nombreUsuario,
        correo: usuario._correo,
        estado: 'I',
        codigoRecuperacion: usuario._codigoRecuperacion,
        password: usuario._password,
        rolDto: usuario._rol._id,
        datoUsuarioDto: usuario._datoUsuario!._id
      }

      let encuestado: Dato_Usuario = {
        id: usuario._datoUsuario?._id,
        cedula: usuario._datoUsuario?._cedula!,
        primerNombre: usuario._datoUsuario?._primerNombre!,
        segundoNombre: usuario._datoUsuario?._segundoNombre!,
        primerApellido: usuario._datoUsuario?._primerApellido!,
        segundoApellido: usuario._datoUsuario?._segundoApellido!,
        sexo: usuario._datoUsuario?._sexo!,
        fechaNacimiento: usuario._datoUsuario?._fechaNacimiento!,
        estadoCivil: usuario._datoUsuario?._estadoCivil!,
        estado: 'I',
        disponibilidadEnLinea: usuario._datoUsuario?._disponibilidadEnLinea!,
        conCuantasPersonasVive: usuario._datoUsuario?._conCuantasPersonasVive!,
        medioComunicacion: usuario._datoUsuario?._medioComunicacion!,
        lugarDto: usuario._datoUsuario?._lugar._id!,
        nivelAcademicoDto: usuario._datoUsuario?._nivelAcademico._id!,
        ocupacionDto: usuario._datoUsuario?._ocupacion._id!,
        nivelEconomicoDto: usuario._datoUsuario?._nivelEconomico._id!
      }

      console.log('usuario con foranea de dato usuario');
      console.log(user);
      console.log(encuestado);
      setTimeout(() => {
        this.usuarioService.onBorrarUsuario(indice, user);
      }, 1000);

      setTimeout(() => {
      this.datoU.onBorrarEncuestado(usuario._datoUsuario?._id!, encuestado);
    }, 1000);

      setTimeout(() => {
      this.busquedaUsuario();
      }, 1000);
    }
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

    }

    const dialogRef = this.dialog.open(DialogMostrarUsuarioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe((result) => {
        console.log('Dialog closed');
      });
  }

}
