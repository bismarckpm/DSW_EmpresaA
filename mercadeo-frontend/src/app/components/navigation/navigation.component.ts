import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

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
    
  constructor(
    private _loginService: LoginService
    ) 
    { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    if (this.identity != null) {
      this.user = new User(
        this.identity.id,
        this.identity.nombreUsuario,
        this.identity.correo,
        this.identity.estado,
        this.identity.idRol )
        if (this.user) {
          this.isUser = true;
          console.log(this.user)
        }
    }
  }


}
