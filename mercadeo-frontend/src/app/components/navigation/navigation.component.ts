import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modelos/user';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  // Usuarios
  public identity: any;
  public user: User;

    
  constructor(
    private _loginService: LoginService
    ) 
    {
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
    
  }

}
