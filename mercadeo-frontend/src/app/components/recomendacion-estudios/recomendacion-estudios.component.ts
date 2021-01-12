import { Estudio, GetEstudio } from './../../interfaces/estudio';
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
  estudios: GetEstudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private route: ActivatedRoute,
              private estudiosR: EstudioService,
              private navegacion: Router,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.idSolicitud = this.route.snapshot.params['idSolicitud'];

    this.isWait = true;
    this.estudiosR.getPlantilla(this.idSolicitud).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
        this.isWait = false;
        console.log(this.estudios);
      }
    );

  }

  crearEstudio(est: GetEstudio) {
    const estudio: Estudio = {
      nombre: 'Estudio creado con Plantilla',
      fechaInicio: est._fechaInicio,
      /* fechaFin: this.fechaF, */
      estatus: 'En Espera',
      estado: 'A',
      solicitudEstudioDto: Number(this.idSolicitud),
      usuarioDto: 1
    };

    setTimeout(() => {
      this.estudiosR.createEstudio(estudio);
      }, 1000);

    this._snackBar.open('Estudio Creado exitosamente', undefined, {
        duration: 1000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });

    this.navegacion.navigate(['consultarestudios']);

  }

  atras(){
    this.navegacion.navigate(['listasolicitudes']);
  }
}
