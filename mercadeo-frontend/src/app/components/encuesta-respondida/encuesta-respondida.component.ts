import { GetRespuesta } from './../../interfaces/respuesta';
import { RespuestaServiceService } from './../../services/respuesta-service.service';
import { Component, OnInit } from '@angular/core';
import { GetPregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { Respuesta } from 'src/app/interfaces/respuesta';
import { GetRespuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-encuesta-respondida',
  templateUrl: './encuesta-respondida.component.html',
  styleUrls: ['./encuesta-respondida.component.css']
})
export class EncuestaRespondidaComponent implements OnInit {

  isLinear = false;
    enable = false;
    showStep = false;
    isCompleted = false;
    idU: number = 0;
    idE = 0;
    checkeado = false;
    firstFormGroup: any;
    secondFormGroup: any;
    respuesta = '';
    favoriteSeason: string = '';
    vT: string[] = ['Verdadero' , 'Falso'];
    escalas: number[] = [1, 2 , 3, 4, 5];
    preguntas2: GetPregunta_Encuesta[] = [];
    respuestas: GetRespuesta_Pregunta[] = [];
    respuestas2: Respuesta[] = [];
    resps = <any>[];
    respuestas3: GetRespuesta[] = [];

  constructor(private rsp: RespuestaServiceService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idE = this.route.snapshot.params['idEstudio'];
    this.rsp.getRespuestasEstudio(this.idE).subscribe(
      (rep: GetRespuesta[]) => {
        this.respuestas3 = rep;
        console.log(rep[0]._listaRespuestas[0]._descripcion);
        console.log(this.respuestas3);
        console.log(this.respuestas3[0]._enunciado);
       /*  console.log(this.respuestas3[0]._listaRespuestas.); */
      }
  );
  }

}
