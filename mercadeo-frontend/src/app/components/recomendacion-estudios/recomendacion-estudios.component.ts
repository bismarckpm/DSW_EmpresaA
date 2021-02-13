import { Estudio, GetEstudio, GetEstudiosRecomendados } from './../../interfaces/estudio';
import { Subscription } from 'rxjs';
import { EstudioService } from 'src/app/services/estudio.service';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import { DialogPreviewestudioComponent } from '../dialog-previewestudio/dialog-previewestudio.component';


@Component({
  selector: 'app-recomendacion-estudios',
  templateUrl: './recomendacion-estudios.component.html',
  styleUrls: ['./recomendacion-estudios.component.css']
})
export class RecomendacionEstudiosComponent implements OnInit {
  isWait=false;
  idSolicitud = 0;
  estudios: GetEstudiosRecomendados[] = [];
  estudioR: GetEstudio[] = [];
  fkUser = 0;
  idEstudio = 0;
  conclusion = '';
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private route: ActivatedRoute,
              private estudiosR: EstudioService,
              private navegacion: Router,
              private _snackBar: MatSnackBar,
              private estudioService: EstudioService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.idSolicitud = Number(this.route.snapshot.params['idSolicitud']);
    console.log('Id de solicitud' + this.idSolicitud);

    this.isWait = true;
    this.estudiosR.getPlantilla(this.idSolicitud).subscribe(
      (estudios) => {
        this.estudios = estudios.objeto;
        this.isWait = false;
        console.log(this.estudios);

      }
    );



  }

  Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async crearEstudio(est: GetEstudiosRecomendados) {

    this.estudioService.getEstudio(est.idEstudio).subscribe(

      (estudio) => {
        this.estudioR.push(estudio.objeto);
        console.log(estudio);
        console.log(this.estudioR);
        this.fkUser = this.estudioR[0]._usuario._id;
        this.idEstudio = this.estudioR[0]._id!;
        this.conclusion = this.estudioR[0]._conclusion;
        console.log('id de usuario' + this.fkUser);

      }
    );

    await this.Delay(5000);
    const estudio: Estudio = {
      id: this.idEstudio,
      nombre: 'Estudio creado con Plantilla',
      fechaInicio: est.fechaI,
      estatus: 'En Espera',
      estado: 'A',
      conclusion: this.conclusion,
      solicitudEstudioDto: Number(this.idSolicitud),
      usuarioDto: this.fkUser,
    };

    setTimeout(() => {
      this.estudiosR.createEstudioRecomendacion(this.idSolicitud, estudio);
      }, 1000);

    this._snackBar.open('Estudio Creado exitosamente', undefined, {
        duration: 1000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });

    this.navegacion.navigate(['asignarpreguntasaestudio', this.idEstudio]);

  }

  atras(){
    this.navegacion.navigate(['listasolicitudes']);
  }


  //Dialogo de Preview de estudio Recomendado
  previewEstudioRecomendado(idEstudio: number){
    const dialogRef = this.dialog.open(DialogPreviewestudioComponent, 
      {
        width: '45rem',
        height: '25rem',
        data: { idEstudio: idEstudio, idSolicitud:this.idSolicitud }
      });

      dialogRef.afterClosed().subscribe(
        result => {
          console.log("El dialogo se cerró");
          dialogRef.close();
        }
      );



  }
}
