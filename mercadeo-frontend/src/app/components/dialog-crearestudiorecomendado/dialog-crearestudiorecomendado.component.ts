import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Estudio } from 'src/app/interfaces/estudio';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { Usuario } from 'src/app/interfaces/usuario';
import { AlertService } from 'src/app/services/alert.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { PoblacionService } from 'src/app/services/poblacion.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { UsuarioServicioService } from 'src/app/services/usuario-servicio.service';
import { DialogPreviewestudioComponent } from '../dialog-previewestudio/dialog-previewestudio.component';

@Component({
  selector: 'app-dialog-crearestudiorecomendado',
  templateUrl: './dialog-crearestudiorecomendado.component.html',
  styleUrls: ['./dialog-crearestudiorecomendado.component.css']
})
export class DialogCrearestudiorecomendadoComponent implements OnInit {

  isWait = false;

  //Form crear estudio recomendado
  estudioRecomendadoForm: any;


  analistas:any;
  fechaActual: any;
  
  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    public dialogRef: MatDialogRef<DialogPreviewestudioComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private fb: FormBuilder,
    private _user: UsuarioServicioService,
    private estudiosR: EstudioService,
    private poblacionService: PoblacionService,
    private _navegacion: Router,
    private _snackBar: MatSnackBar,
    private alertService: AlertService,
    private solicitud: SolicitudestudioService,
  ) { }

  ngOnInit(): void {

    console.log(this.data);
    this.buildForm();
    this.actualizarEstadoSolicitud(this.data.idSolicitud);

    this._user.getUsuariosAnalista(3).subscribe(
      (analista) => {
        this.analistas = analista.objeto;
        console.log(this.analistas);
      }
    );
    this.fechaActual = new Date();
  }


  buildForm(): void {
    this.estudioRecomendadoForm = this.fb.group({
      nombreEstudio: ["",
      Validators.compose([
        Validators.required,
      ]),],
      fechaInicio: ["",
      Validators.compose([
        Validators.required]),
      ],
      analistaAsignado: ["",
        Validators.compose([
          Validators.required]),
        ]
    });
 }

  crearEstudioRecomendado(){
    this.isWait = true;

    const estudio: Estudio = {
      id: this.data.idEstudio,
      nombre: this.estudioRecomendadoForm.get("nombreEstudio").value,
      fechaInicio: new Date(),
      estatus: 'En Espera',
      estado: 'A',
      conclusion: '',
      solicitudEstudioDto: Number(this.data.idSolicitud),
      usuarioDto: this.estudioRecomendadoForm.get("analistaAsignado").value,
    };


    this.estudiosR.createEstudioRecomendacion(this.data.idSolicitud, estudio).subscribe(
      response => {
        const idEstudioRecomendado = response.objeto._id;
        console.log(idEstudioRecomendado);
        this.isWait = false;

        this.asignarPoblacionEstudio(this.data.idSolicitud, idEstudioRecomendado);
        this.guardar(this.solicitudAGuardar);
        this.closeDialog();

        this._snackBar.open('Estudio Creado exitosamente '+ response.estado, undefined, {
          duration: 1000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
        });

        this._navegacion.navigate(['asignarpreguntasaestudio', idEstudioRecomendado]);


      },
      error =>{ console.log('error agregando estudio' + error),
      this.isWait = false;

      this._snackBar.open('Error al crear estudio Estudio ', undefined, {
        duration: 1000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
      });

    });
    console.log(estudio);

  }

  closeDialog(){
    this.dialogRef.close();
  }


  asignarPoblacionEstudio(idSolicitud: any, idEstudio: any) {

    this.poblacionService.addPoblacionInicial(idSolicitud,idEstudio).subscribe((response)=> {

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
    
      }
      )
  
    }


}
