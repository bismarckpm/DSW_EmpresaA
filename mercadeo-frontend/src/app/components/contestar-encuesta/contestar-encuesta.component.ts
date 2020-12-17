import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';


@Component({
  selector: 'app-contestar-encuesta',
  templateUrl: './contestar-encuesta.component.html',
  styleUrls: ['./contestar-encuesta.component.css']
})
export class ContestarEncuestaComponent implements OnInit {

    isLinear = false;
    enable = false;
    showStep = false;
    firstFormGroup: FormGroup;
    secondFormGroup: FormGroup;
    favoriteSeason: string;
    seasons: string[] = ['Winter', 'Spring', 'Summer', 'Autumn'];
    preguntas = [{p: 'pregunta1', r: 'respuesta1',tipo: 'cerrada'}, {p: 'pregunta2', r: 'respuesta2',tipo: 'abierta'}, {p: 'pregunta3', r: 'respuesta3', tipo: 'seleccion'}];
  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit(): void {

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  siguiente(stepper: MatStepper) {
    stepper;

  }

}



