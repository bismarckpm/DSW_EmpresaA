import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/modelos/usuario';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  
  Invalido = false;

  public identity:any;

  loginForm: any;

  constructor(
    private _userService: UserService,
    private _loginService: LoginService,
    private fb: FormBuilder,
    private _router: Router,
    private _route: ActivatedRoute
  ){

   }

  ngOnInit(): void {
    this.buildForm();
    this.logout();
  }


  buildForm(): void {
    this.loginForm = this.fb.group({
      email: ["",
      Validators.compose([
        Validators.required, 
      ]),],
      password: ["",
      Validators.compose([
        Validators.required]),
      ]
    });
}


login(){

    const user = {
       email: this.loginForm.get("email").value,
       password: this.loginForm.get("password").value
    }

   this._loginService.iniciarSesion(user).subscribe(
      response=> {
        this.identity = response;
        localStorage.setItem('identity',JSON.stringify(this.identity));
        console.log(response);

         //Redirección al inicio
         this._router.navigate(['producto']);
      },
      error => {
        console.log(<any>error);
        console.log(user);
        this.Invalido = true;         // Con esto muestro lo que envío desde el formulario. 
      }

    )
  }

  logout(){
    this._route.params.subscribe(params => {
      let logout = +params['sure']; //con el + casteo de string a integer
      if(logout == 1){
        localStorage.removeItem('identity');

        this.identity = null;

        //Redirección a Inicio

        this._router.navigate(['login']);
      }
    }

    )
  }

}
