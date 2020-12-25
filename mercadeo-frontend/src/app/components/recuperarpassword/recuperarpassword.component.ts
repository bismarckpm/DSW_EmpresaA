import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { error } from 'protractor';
import { LoginService } from 'src/app/services/login.service';
import { Usuario } from '../../interfaces/usuario';

import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-recuperarpassword',
  templateUrl: './recuperarpassword.component.html',
  styleUrls: ['./recuperarpassword.component.css']
})
export class RecuperarpasswordComponent implements OnInit {

  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }

  Alert = false; 

  showAlert(cerrar:any){
    this.Alert = false;
  }

  Aviso = true; 

  showAviso(aviso: any){
    this.Aviso =false; 
  }

  Codigo = false; 

  showCodigo(codigo: any){
    this.Codigo = false; 
  }

  usuario: Usuario = {
    id: 0,
    nombreUsuario: '',
    correo: '',
    estado: '',
    codigoRecuperacion: '',
    password: '',
    rolDto: 0,
    datoUsuarioDto: 0
  }


  constructor(
    private _loginService: LoginService,
    private _router: Router
  ) {

    
   }

  ngOnInit(): void {
    
  }

onCorreo(correo: any){
    
    this._loginService.validarCorreo(correo.value.correo).subscribe(
      response => {
        if (response != null){
        this.usuario = response;
        console.log(this.usuario);
        this.ShowDiv('B');
        }
        else {
          this.Alert = true;
        }
      },
      error => {
        console.log(<any>error);
      }
    );
}


  onRecupera(form: any){

    let userConfirma: Usuario = {
      id: this.usuario.id,
      nombreUsuario: this.usuario.nombreUsuario,
      correo: this.usuario.correo,
      estado: this.usuario.estado,
      codigoRecuperacion: form.value.codigoRecuperacion,
      password: this.usuario.password,
      rolDto: this.usuario.rolDto,
      datoUsuarioDto: this.usuario.datoUsuarioDto
    } 
    console.log(userConfirma);

    this._loginService.validarCodigo(userConfirma).subscribe(
      response => {
        if (response != null){
          this.usuario = response;
          console.log(this.usuario);
          this.ShowDiv('C');
        }else {
          this.Codigo = true; 
        }

      }, error => {
        console.log(<any> error);
      }
    )
      
  }

  onCambia(form: any){
    let userCambia: Usuario = {
      id: this.usuario.id,
      nombreUsuario: this.usuario.nombreUsuario,
      correo: this.usuario.correo,
      estado: this.usuario.estado,
      codigoRecuperacion: this.usuario.codigoRecuperacion,
      password: form.value.password,
      rolDto: this.usuario.rolDto,
      datoUsuarioDto: this.usuario.datoUsuarioDto
    }

    this._loginService.cambiarClaveRecuperada(userCambia).subscribe(
      response => {
        this.usuario = response; 
        console.log(this.usuario);
        this._router.navigate(['login']);
      }, error => {
        console.log(<any> error);
      }

    )
  }
}
