import { Pregunta_Estudio } from './../../models/pregunta_estudio';
import { PreguntaEstudioServicioService } from './../../services/pregunta-estudio-servicio.service';
import { PreguntaEncuestaServiceService } from './../../services/pregunta-encuesta-service.service';

import { EstudioService } from 'src/app/services/estudio.service';
import { Estudio } from './../../models/estudio';
import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from 'src/app/models/pregunta_encuesta';


@Component({
  selector: 'app-asignar-preguntas-estudio',
  templateUrl: './asignar-preguntas-estudio.component.html',
  styleUrls: ['./asignar-preguntas-estudio.component.css']
})
export class AsignarPreguntasEstudioComponent implements OnInit {

  idA = 5;
  idR = 2;
  cat = '';
  estId = 0;
  idP = 0;
  categorias: string[] =  ["seleccion simple","verdadero o falso","respuesta libre"];
  preguntas: Pregunta_Encuesta[] = []; ///almacena las preguntas del tipo seleccionado
  estudios: Estudio[] = [];
  
  pre: Pregunta_Estudio[] = [];

  // aca estan todas las preguntas
  constructor(private estudio: EstudioService, private pregunta: PreguntaEncuestaServiceService,
    private preguntaE: PreguntaEstudioServicioService) { }

  ngOnInit(): void {

    this.preguntaE.getPreguntas().subscribe(
      (pr: Pregunta_Estudio[]) => {
        this.pre = pr;
        this.idP = this.pre.slice(-1)[0].id!;
        console.log(this.idP);
      }
    );

    this.estudio.getEstudios(this.idA).subscribe(
      (estudios: Estudio[]) => {
        this.estudios = estudios;
      }
    );


  }

  busquedaPreguntas() {
     this.pregunta.onCategoriaPregunta(this.cat).subscribe(
      (pregunta: Pregunta_Encuesta[]) => {
         this.preguntas =  pregunta;
      }
    );
  }

  agregarPreguntaEstudio(id: number) {

    let estudio = new Estudio(this.estId);
    let preEnc = new Pregunta_Encuesta(id);

    let pre = new Pregunta_Estudio(this.idP + 1, estudio, preEnc);

    this.preguntaE.createPreguntaEstudio(pre);

  }
}
