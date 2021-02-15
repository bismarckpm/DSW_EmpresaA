import { Router, ActivatedRoute } from '@angular/router';
import { EstudioService } from '../../../../services/estudio.service';
import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { SolicitudesServicioService } from '../../../../services/solicitudes-servicio.service';
import { GetUsuario, Usuario } from '../../../../interfaces/usuario';
import { Solicitud_Estudio } from '../../../../interfaces/solicitud_estudio';
import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/interfaces/estudio';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { PoblacionService } from 'src/app/services/poblacion.service';
import { AlertService } from 'src/app/services/alert.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';


@Component({
  selector: 'app-crear-estudio',
  templateUrl: './crear-estudio.component.html',
  styleUrls: ['./crear-estudio.component.css']
})
export class CrearEstudioComponent implements OnInit {

  nombreEs = '';
  tipoIns = '';
  fechaI = new Date();
  fechaF = new Date();
  estatus = '';
  estado = '';
  idSolicitud = 0;
  idAnalista = 0;
  estudioId: any;
  analistas: Usuario[] = [];
  estudios: Estudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(private solicitud: SolicitudestudioService,
              private user: UsuarioServicioService, private estudio: EstudioService,
              private navegacion: Router,
              private route: ActivatedRoute,
              private _snackBar: MatSnackBar,
              private poblacionService: PoblacionService,
              private alertService: AlertService,

              ) { }

  ngOnInit(): void {
    this.idSolicitud = Number(this.route.snapshot.params['idSolicitud']);
    console.log(this.idSolicitud);

    this.user.getUsuariosAnalista(3).subscribe(
      (analista) => {
        this.analistas = analista.objeto;
      }
    );
    this.actualizarEstadoSolicitud(this.idSolicitud);
  }

  asignarEstudio(){

    console.log(this.fechaI);

    let estudio: Estudio = {
      nombre: this.nombreEs,
      fechaInicio: this.fechaI,
      estatus: 'En Espera',
      estado: 'A',
      conclusion: '',
      solicitudEstudioDto: Number(this.idSolicitud),
      usuarioDto: this.idAnalista
    };

    this.estudio.createEstudio(estudio).subscribe(
      (data) => {
      this.estudioId = data
      console.log(this.estudioId)

      this.asignarPoblacionEstudio(this.idSolicitud, this.estudioId.objeto._id);

      this.guardar(this.solicitudAGuardar);
      this._snackBar.open('Estudio Creado exitosamente ' + data.estado, undefined, {
      duration: 1000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

      this.navegacion.navigate(['asignarpreguntasaestudio', this.estudioId.objeto._id]);
    });

  }

  asignarPoblacionEstudio(idSolicitud: any, idEstudio: any) {

    this.poblacionService.addPoblacionInicial(idSolicitud,idEstudio).subscribe((response)=> {
        this.alertService.info(response.mensaje + ' ' + response.estado, this.options)

    })
  }



  // Obtengo la solicitud para editar su estado a En PROCESO
  solicitudAGuardar : any
  actualizarEstadoSolicitud(idSolicitud: any) {
    this.solicitud.getSolicitud(idSolicitud).subscribe(response =>
      {
        this.solicitudAGuardar = response.objeto;
        console.log(this.solicitudAGuardar)
      }
      )
  }


  guardar(Solicitud: any){

    console.log('Solicitud' ,Solicitud)
  
    const NewS: Solicitud_Estudio = {
      id: Solicitud._id,
      descripcionSolicitud: Solicitud._descripcionSolicitud,
      generoPoblacional: Solicitud._generoPoblacional,
      fechaPeticion: Solicitud._fechaPeticion,
      edadMinimaPoblacion: Solicitud._edadMinimaPoblacion,
      edadMaximaPoblacion: Solicitud._edadMaximaPoblacion,
      estatus: 'En Proceso',
      estado: Solicitud._estado,
      conCuantasPersonasVive: Solicitud._conCuantasPersonasVive,
      disponibilidadEnLinea: Solicitud._disponibilidadEnLinea,
      nivelEconomicoDto: Solicitud._nivelEconomico._id,
      productoDto:  Solicitud._producto._id,
      ocupacionDto: Solicitud._ocupacion._id,
      usuarioDto: Solicitud._usuario._id,
    }
  
    console.log(NewS)

    this.solicitud.actualizarSolicitud(NewS).subscribe(response =>{
      console.log(response)
    }
    )

  }

  atras(){
    this.navegacion.navigate(['listasolicitudes']);
  }
 }
