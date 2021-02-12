import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';
import { LoginService } from 'src/app/services/login.service';
import { RegionEstudioService } from 'src/app/services/regionestudio.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';

@Component({
  selector: 'app-dialog-consulta-solicitud',
  templateUrl: './dialog-consulta-solicitud.component.html',
  styleUrls: ['./dialog-consulta-solicitud.component.css']
})
export class DialogConsultaSolicitudComponent implements OnInit {

  // Variables
  isEmpty: boolean = false;

  // Solicitud
  solicitud: any;

  // Estudios Asociados
  estudios: any[] = [];

  // Regiones
  regiones: any[] = [];
  region: any;

  constructor(
    // Dialogs
    public dialogRef: MatDialogRef<DialogConsultaSolicitudComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    // Router
    private _router: Router,
    // Services
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _estudioService: EstudioclienteService,
    private _regionEstudioService: RegionEstudioService,
  ) { }

  ngOnInit(): void {
    console.log(this.data.solicitud)
    this.getUser();
    this.consultarSolicitud();
    this.obtenerEstudiosAsociados(this.data.solicitud);
    this.buscarRegionesSolicitud(this.data.solicitud);
  }


  onNoClick(): void {
    this.dialogRef.close();
  }


  // Obtener Solicitud
  // Paso Id Solicitud
  // Returns = Datos de la solicitud 
  consultarSolicitud(){
    this._solicitudService.getSolicitud(this.data.solicitud).subscribe(
      response => {
        this.solicitud = response;
        
        console.log('DialogConsultaSolicitud', this.solicitud);

      },error => {
        console.log(<any>error);
      }
    )
  }

  // Obtener Estudios
  // Paso Id Solicitud
  // Returns = Datos de la solicitud 
  obtenerEstudiosAsociados(idSolicitud: number) {
    // this._estudioService.getEstudios(this.user.id).subscribe( (response) => {
    //   this.estudios = response;

    //   this.estudios = this.estudios.filter(item => item._solicitudEstudio._id === idSolicitud);

    this._solicitudService.getEstudiosDeSolicitud(idSolicitud).subscribe( (response) => {
      this.estudios = response;

      console.log('DialogObtenerEstudiosAsociados', this.estudios)

      // Si esta vacio el array
      // isEmpty = true
      if (this.estudios.length == 0) {
        this.isEmpty = true;
      } else {
        this.isEmpty = false;
      }

    })
  }


  // Obtener Regiones
  // Paso Id Solicitud
  // Returns = Regiones dentro de una solicitud
  buscarRegionesSolicitud(idSolicitud: number){
    this._regionEstudioService.buscaRegionesSolicitud(idSolicitud).subscribe(
      response => {
        this.regiones = response;
        this.regiones = this.regiones.map(item => item = item._nombre)

        console.log('DialogBuscarRegionesSolicitud', this.regiones);

        if (this.regiones.length > 1) {
          this.region = this.regiones.join(', ')
        } else {
          this.region = this.regiones.join('')
        }

      }
    )
  }


  // Dirigirse a los resultados de un estudio
  // Condicion = Que este finalizado
  // Paso Id Estudio
  // Returns = Resultados de un Estudio
  verEstudio(estudio: number | undefined){
    console.log(estudio);
    this.onNoClick();
    this._router.navigate(['/resultadosEstudio'], { queryParams: {
      estudio: estudio
    }});

  }



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

  // Obtener el User
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
        console.log(this.user)
      }
  }
  
}
