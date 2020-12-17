import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/modelos/user';
import { Solicitud_Estudio } from 'src/app/modelos/solicitud_estudio';
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
    private _solicitudService: SolicitudestudioService,
    private _router: Router,
    private _route: ActivatedRoute
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

  editarSolicitud(solicitud: any){
    const idSolicitud = solicitud;
    this._router.navigate(['/editaSolicitud'], { queryParams: {
      idSolicitud: idSolicitud
    }});
  }

  eliminarSolicitud(solicitud: any){

    let Solicitud = new Solicitud_Estudio(
      solicitud.id = solicitud._id,
      solicitud.descripcionSolicitud = solicitud._descripcionSolicitud,
      solicitud.generoPoblacional = solicitud._generoPoblacional,
      solicitud.fechaPeticion = solicitud._fechaPeticion,
      solicitud.edadMinimaPoblacion = solicitud._edadMinimaPoblacion,
      solicitud.edadMaximaPoblacion = solicitud._edadMaximaPoblacion,
      solicitud.estado = 'I',
      solicitud.cantidadHijos = solicitud._cantidadHijos,
      solicitud.generoHijos = solicitud._generoHijos,
      solicitud.edadMinimaHijos = solicitud._edadMinimaHijos,
      solicitud.edadMaximaHijos = solicitud._edadMaximaHijos,
      solicitud.conCuantasPersonasVive = solicitud._conCuantasPersonasVive,
      solicitud.disponibilidadEnLinea = solicitud._disponibilidadEnLinea,
      solicitud.nivelEconomicoDto = solicitud._nivelEconomico._id,
      solicitud.productoDto = solicitud._producto._id,
      solicitud.usuarioDto = solicitud._usuario._id,
      solicitud.ocupacionDto = solicitud._ocupacion._id
    );

    console.log(Solicitud);

    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._solicitudService.deleteSolicitud(Solicitud).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(<any> error);
        }
      );
    }
    location.reload();
  }

}