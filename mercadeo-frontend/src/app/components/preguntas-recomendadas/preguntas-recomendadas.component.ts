import { PreguntaEstudioServicioService } from './../../services/pregunta-estudio-servicio.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { GetPregunta_Estudio, Pregunta_Estudio } from 'src/app/interfaces/pregunta_estudio';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Estudio } from 'src/app/interfaces/estudio';
import { EstudioService } from 'src/app/services/estudio.service';


@Component({
  selector: 'app-preguntas-recomendadas',
  templateUrl: './preguntas-recomendadas.component.html',
  styleUrls: ['./preguntas-recomendadas.component.css']
})
export class PreguntasRecomendadasComponent implements OnInit {
  isWait=false;
  idEst = 0;
  estudios: Estudio[] = [];

  preguntas: GetPregunta_Estudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';


  @ViewChild('title') title!: ElementRef;
  constructor(private route: ActivatedRoute,
              private estudio: EstudioService, 
              private pr: PreguntaEncuestaServiceService,
              private pe: PreguntaEstudioServicioService,
              private navegacion: Router,
              private _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    setTimeout(() => {
      this.title.nativeElement.click();
      }, 500);
    this.idEst = this.route.snapshot.params['idEstudio'];
    this.getEstudio();

    this.busquedaPreguntas();
  }

  busquedaPreguntas() {
    this.isWait=true;
    this.pr.getPreguntasRecomendadas(this.idEst).subscribe(
     (pregunta) => {
        this.preguntas =  pregunta.objeto;
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


getEstudio() {
  this.estudio.getEstudio(this.idEst).subscribe((data) => {
    this.estudios = data.objeto._nombre;
    console.log( this.estudios);
  })

}
}
