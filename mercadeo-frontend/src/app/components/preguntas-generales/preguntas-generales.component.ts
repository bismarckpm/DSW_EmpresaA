import { PreguntaEstudioServicioService } from './../../services/pregunta-estudio-servicio.service';
import { ActivatedRoute } from '@angular/router';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { Component, OnInit } from '@angular/core';
import { GetPregunta_Estudio, Pregunta_Estudio } from 'src/app/interfaces/pregunta_estudio';

@Component({
  selector: 'app-preguntas-generales',
  templateUrl: './preguntas-generales.component.html',
  styleUrls: ['./preguntas-generales.component.css']
})
export class PreguntasGeneralesComponent implements OnInit {

  isWait = false;
  idEst = 0;
  preguntas: GetPregunta_Estudio[] = [];
  constructor(private pg: PreguntaEncuestaServiceService,
              private route: ActivatedRoute,
              private pe: PreguntaEstudioServicioService) { }

  ngOnInit(): void {
    this.idEst = this.route.snapshot.params['idEstudio'];
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
    estado: 'Activo',
    preguntaEncuestaDto: id};

    this.pe.createPreguntaEstudio(pre);
    this.isWait=false;

}
}
