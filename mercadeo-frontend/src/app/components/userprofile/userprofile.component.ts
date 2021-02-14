import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { AlertService } from 'src/app/services/alert.service';
import { LoginService } from 'src/app/services/login.service';
import { UserprofileService } from 'src/app/services/userprofile.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  cla: any = false; 
  public identity;
  cambiaClaveForm: any;

  hide = true;

  claveErronea = false;

  constructor(
    private fb: FormBuilder,
    public _loginService: LoginService,
    public _userProfileService: UserprofileService,
    private _router: Router,
    private _alertService: AlertService

  ) { 
    this.identity = JSON.parse(_loginService.getIdentity());

    //No se está usando
    const user =  new User(
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


  cambiaClave(contra:any){

    if(contra == true){
      this.cla = true;
    }else {
      this.cla = false; 
    }
}

buildForm(): void {
  this.cambiaClaveForm = this.fb.group({
    clave: ["",
    Validators.compose([
      Validators.required]),
   ],
    claveRepetida: ["",
    Validators.compose([
      Validators.required]),
   ]
})
}


cambiarClave(){
  let contra = this.cambiaClaveForm.get("clave").value
  let contraRep = this.cambiaClaveForm.get("claveRepetida").value

  if (contra == contraRep){
    //cambia la clave
    this._userProfileService.cambiarClave(this.identity.id,contra).subscribe(
      response => {
        this._alertService.info(response.mensaje + '   Estado: '+ response.estado)
        location.reload();
      }
    )
  }else {
    this.claveErronea = true; 
  }
}

}
