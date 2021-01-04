import { Component, OnInit } from '@angular/core';
import { GetPregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { Respuesta } from 'src/app/interfaces/respuesta';
import { GetRespuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';

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
    idE: number = 2;
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
  constructor() { }

  ngOnInit(): void {
  }

}
