import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';
import { GetRespuesta_Pregunta, Respuesta_Pregunta } from './../../../../interfaces/respuesta_pregunta';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { Respuesta } from 'src/app/interfaces/respuesta';

@Component({
  selector: 'app-contestar-encuesta',
  templateUrl: './contestar-encuesta.component.html',
  styleUrls: ['./contestar-encuesta.component.css']
})
export class ContestarEncuestaComponent implements OnInit {

  /* preguntas = [{type: "name", description: "Cual es tu nombre?", isHidden: false},
  {type: "email", description: "Cual es tu correo?", isHidden: true},
  {type: "message", description: "Cual es tu mensaje al mundo?", isHidden: true}] */

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
    numeros: number[] = [1, 2 , 3, 4, 5];
    /* preguntas = [{p: 'pregunta1', tipoPregunta: 'Abierta'}, {p: 'pregunta2', tipoPregunta: 'Abierta'}, {p: 'pregunta3', tipoPregunta: 'Escala'}]; */
    preguntas2: Pregunta_Encuesta[] = [];
    respuestas: GetRespuesta_Pregunta[] = [];
    respuestas2: Respuesta[] = [];
    // tipos de pregunta
    // Abierta(campo de texto), seleccion simple, verdadero y falso, escala(radio button),
    //seleccion multiple(checkbox o radio button)
    //pregunta y respuestas centradas a la izquierda
    //el titulo de la pregunta deberia ser de mayor tamaño
  constructor(private _formBuilder: FormBuilder, private pe: PreguntaEncuestaServiceService,
              private re: RespuestapreguntaService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });



    this.pe.getPreguntas(this.idE).subscribe(
        (pre: Pregunta_Encuesta[]) => {
          this.preguntas2 = pre;
          console.log(this.preguntas2);
        }
    );

    this.re.getRespuestas(this.idE).subscribe(
      (res: GetRespuesta_Pregunta[]) => {
        this.respuestas = res;
        console.log(this.respuestas);
      }
    );
  }

  mandarRespuestas() {
    for(let i =0; i < this.respuestas.length; i++){
    console.log(this.respuestas[i].completado);
    }
  }
}
