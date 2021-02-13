import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Presentacion } from 'src/app/interfaces/presentacion';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { AlertService } from 'src/app/services/alert.service';


@Component({
  selector: 'app-create-presentacion',
  templateUrl: './create-presentacion.component.html',
  styleUrls: ['./create-presentacion.component.css']
})
export class CreatePresentacionComponent implements OnInit {

  presentacion: Presentacion[] = [];
  presentacionForm: any;
  isWait = false;

  // Usuarios
  public identity: any;
  public user: User;

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };
  
  constructor(
    private _location: Location,
    private fb: FormBuilder,
    private _presentacionService: PresentacionService,
    private _loginService: LoginService,
    private alertService: AlertService,

  ) { 
    this.identity = JSON.parse(_loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
  }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm(): void {
    this.presentacionForm = this.fb.group({
     titulo: ["",
     Validators.compose([
       Validators.required,
     ]),],
     caracteristicas: ["",
     Validators.compose([
       Validators.required]),
     ]
   });
 }

 
 add(): void {
  this.isWait = true;

  const newP: Presentacion = {
    id: 0,
    titulo: this.presentacionForm.get("titulo").value,
    estado: 'A',
    caracteristicas: this.presentacionForm.get("caracteristicas").value
  };

  this._presentacionService.createPresentacion(newP).subscribe((response) => {   
    this.isWait = false;
    this.alertService.success(response, this.options)
    this.goBack() ;
  }, error =>  this.alertService.error(error, this.options)
  );
 }

 goBack(): void {
  this._location.back();
}

}
