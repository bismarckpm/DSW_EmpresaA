import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';

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
    idE: number = 0;
    /* pre: Pregunta_Encuesta; */
    firstFormGroup: any;
    secondFormGroup: any;
    respuesta = '';
    favoriteSeason: string = '';
    seasons: string[] = ['Winter', 'Spring', 'Summer', 'Autumn'];
    preguntas = [{p: 'pregunta1', tipoPregunta: 'Abierta'}, {p: 'pregunta2', tipoPregunta: 'Abierta'}, {p: 'pregunta3', tipoPregunta: 'Escala'}];
  constructor(private _formBuilder: FormBuilder, private pe: PreguntaEncuestaServiceService) { }

  ngOnInit(): void {

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }


}
