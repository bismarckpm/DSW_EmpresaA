import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  public usuario: Usuario;
  public status: string;
  public token: string;
  public identity: string;

  constructor(
    private _userService: UserService
  ){
    this.usuario = new Usuario(1,'','','Activo','01234','',1,1);
    this.status="";
    this.identity="";
    this.token="";
   }

  ngOnInit(): void {
    
  }

  onSubmit(form: any){
    this._userService.iniciarSesion(this.usuario).subscribe(
      response=> {
        this.identity = response;

        console.log(response);
      },
      error => {
        this.status = 'error';
        console.log(<any>error);
        console.log(this.usuario); // Con esto muestro lo que envío desde el formulario. 
      }

    )
  }

}
