import { PreguntaEstudioServicioService } from './../../services/pregunta-estudio-servicio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { GetPregunta_Estudio, Pregunta_Estudio } from 'src/app/interfaces/pregunta_estudio';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


@Component({
  selector: 'app-preguntas-generales',
  templateUrl: './preguntas-generales.component.html',
  styleUrls: ['./preguntas-generales.component.css']
})
export class PreguntasGeneralesComponent implements OnInit {

  isWait = false;
  idEst = 0;
  preguntas: GetPregunta_Estudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  @ViewChild('title') title: any;
  constructor(private pg: PreguntaEncuestaServiceService,
              private route: ActivatedRoute,
              private pe: PreguntaEstudioServicioService,
              private navegacion: Router,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.title.nativeElement.click();
      }, 500);
    this.idEst = this.route.snapshot.params['idEstudio'];

    this.busquedaPreguntas();
  }


  busquedaPreguntas() {
    this.isWait=true;
    this.pg.getPreguntasGenerales(this.idEst).subscribe(
     (pregunta: GetPregunta_Estudio[]) => {
        this.preguntas =  pregunta;
        console.log(this.preguntas);
        this.isWait=false;
     }
   );
 }

 agregarPreguntaEstudio(id: number, preg: string) {
    this.isWait=true;
    const pre: Pregunta_Estudio = {
    estudioDto: Number(this.idEst),
    pregunta: preg,
    estado: 'A',
    preguntaEncuestaDto: id};

    this.pe.createPreguntaEstudio(pre);
    this.isWait=false;

    this._snackBar.open('Pregunta asignada exitosamente', undefined, {
      duration: 3000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

    setTimeout(() => {
      this.busquedaPreguntas();
      }, 1000);


}

atras(){
  this.navegacion.navigate(['asignarpreguntasaestudio', this.idEst]);
}
}
