import { Estudio, GetEstudio, GetEstudiosRecomendados } from './../../interfaces/estudio';
import { Subscription } from 'rxjs';
import { EstudioService } from 'src/app/services/estudio.service';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


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
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private route: ActivatedRoute,
              private estudiosR: EstudioService,
              private navegacion: Router,
              private _snackBar: MatSnackBar,
              private estudioService: EstudioService) { }

  ngOnInit(): void {
    this.idSolicitud = Number(this.route.snapshot.params['idSolicitud']);
    console.log('Id de solicitud' + this.idSolicitud);

    this.isWait = true;
    this.estudiosR.getPlantilla(this.idSolicitud).subscribe(
      (estudios: GetEstudiosRecomendados[]) => {
        this.estudios = estudios;
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

      (estudio: GetEstudio) => {
        this.estudioR.push(estudio);
        console.log(estudio);
        console.log(this.estudioR);
        this.fkUser = this.estudioR[0]._usuario._id;
        this.idEstudio = this.estudioR[0]._id!;
        console.log('id de usuario' + this.fkUser);

      }
    );

    await this.Delay(5000);
    const estudio: Estudio = {
      id: this.idEstudio,
      nombre: 'Estudio creado con Plantilla',
      fechaInicio: est.fechaI,
      /* fechaFin: this.fechaF, */
      estatus: 'En Espera',
      estado: 'A',
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
}
