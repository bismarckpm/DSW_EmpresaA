import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { LoginComponent } from '../../../../login/login.component';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-vistasolicitud',
  templateUrl: './vistasolicitud.component.html',
  styleUrls: ['./vistasolicitud.component.css']
})
export class VistasolicitudComponent implements OnInit {

  public user: User;
  public identity: any;
  public solicitudes: any;

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _router: Router,
    private _route: ActivatedRoute,
    private _alertService: AlertService
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
        
        
        this.solicitudes = response.objeto; 

        console.log(this.solicitudes);

        this._alertService.success(response.mensaje + '' + response.estado, this.options);

      },error => {
        console.log(<any>error);
        this._alertService.error(error.mensaje + '' + error.estado);
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

    let Solicitud:  Solicitud_Estudio = {
      id: solicitud._id,
      descripcionSolicitud: solicitud._descripcionSolicitud,
      generoPoblacional: solicitud._generoPoblacional,
      fechaPeticion: solicitud._fechaPeticion,
      edadMinimaPoblacion: solicitud._edadMinimaPoblacion,
      edadMaximaPoblacion: solicitud._edadMaximaPoblacion,
      estatus: solicitud._estatus,
      estado:'I',
      conCuantasPersonasVive: solicitud._conCuantasPersonasVive,
      disponibilidadEnLinea: solicitud._disponibilidadEnLinea,
      nivelEconomicoDto: solicitud._nivelEconomico._id,
      productoDto: solicitud._producto._id,
      usuarioDto: solicitud._usuario._id,
      ocupacionDto: solicitud._ocupacion._id
    };

    console.log(Solicitud);

    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._solicitudService.deleteSolicitud(Solicitud).subscribe(
        response => {
          console.log(response);
          this._alertService.success(response.mensaje + '' + response.estado, this.options);
        },
        error => {
          console.log(<any> error);
          this._alertService.error(error.mensaje + '' + error.estado, this.options);
        }
      );
    }
    location.reload();
  }

}