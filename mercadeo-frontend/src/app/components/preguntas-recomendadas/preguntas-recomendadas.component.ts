import { PreguntaEstudioServicioService } from './../../services/pregunta-estudio-servicio.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { GetPregunta_Estudio, Pregunta_Estudio } from 'src/app/interfaces/pregunta_estudio';

@Component({
  selector: 'app-preguntas-recomendadas',
  templateUrl: './preguntas-recomendadas.component.html',
  styleUrls: ['./preguntas-recomendadas.component.css']
})
export class PreguntasRecomendadasComponent implements OnInit {
  isWait=false;
  idEst = 0;
  preguntas: GetPregunta_Estudio[] = [];
  constructor(private route: ActivatedRoute,
              private pr: PreguntaEncuestaServiceService,
              private pe: PreguntaEstudioServicioService) { }

  ngOnInit(): void {
    this.idEst = this.route.snapshot.params['idEstudio'];
  }

  busquedaPreguntas() {
    this.isWait=true;
    this.pr.getPreguntasRecomendadas(this.idEst).subscribe(
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
