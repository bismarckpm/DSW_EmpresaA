import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modelos/user';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { LoginComponent } from '../../login/login.component';

@Component({
  selector: 'app-vistasolicitud',
  templateUrl: './vistasolicitud.component.html',
  styleUrls: ['./vistasolicitud.component.css']
})
export class VistasolicitudComponent implements OnInit {

  public user: User;
  public identity: any;
  public solicitudes: any;
  constructor(
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService
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
    this.obtenerSolicitud(this.user.id);
    console.log(this.user.id);
  }

  obtenerSolicitud(idUser: number){
    this._solicitudService.obtenerSolicitud(idUser).subscribe(
      response => {
        this.solicitudes = response;
        console.log(response);

      },error => {
        console.log(<any>error);
      }
    )
  }

}