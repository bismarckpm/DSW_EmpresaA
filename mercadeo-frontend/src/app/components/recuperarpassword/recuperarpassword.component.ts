import { Component, OnInit } from '@angular/core';
import { error } from 'protractor';
import { LoginService } from 'src/app/services/login.service';
import { Usuario } from '../../modelos/usuario';
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

  showAlert(cerrar){
    this.Alert = false;
  }


  usuario: Usuario = {
    id: 0,
    nombreUsuario: '',
    correo: '',
    estado: 'A',
    codigoRecuperacion: '',
    password: '',
    fk_rol: 0,
    fk_datoUsuario: 0
  }


  constructor(
    private _loginService: LoginService
  ) {

    
   }

  ngOnInit(): void {
    
  }

onCorreo(correo: any){
    
    this._loginService.validarCorreo(correo.value.correo).subscribe(
      response => {
        if (response = null){
        this.usuario = response;
        console.log(this.usuario);
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

  }
}
