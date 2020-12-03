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

  constructor(
    private _userService: UserService
  ){
    this.usuario = new Usuario(1,'','','Activo','01234','',1,1);
    this.status= "";
   }

  ngOnInit(): void {
    console.log(this._userService.test());
  }

  onSubmit(form: any){
    this._userService.iniciarSesion(this.usuario).subscribe(
      response=> {
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
