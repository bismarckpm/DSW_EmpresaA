import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modelos/user';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-sidebar2',
  templateUrl: './sidebar2.component.html',
  styleUrls: ['./sidebar2.component.css']
})
export class Sidebar2Component implements OnInit {
  
  constructor( private _loginService: LoginService) { }

  // Usuarios
  public identity: any;
  isUser = false;
  public user: User = {
    id:0,
    nombreUsuario:'',
    correo:'',
    estado:'',
    idRol:0
  };

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol )
      if (this.user) {
        this.isUser = true;
      }
      console.log(this.user)
  }



}
