import { RespuestaServiceService } from './../../../../services/respuesta-service.service';
import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';
import { GetRespuesta_Pregunta, Respuesta_Pregunta } from './../../../../interfaces/respuesta_pregunta';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { GetPregunta_Encuesta, Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
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
    preguntas2: GetPregunta_Encuesta[] = [];
    respuestas: GetRespuesta_Pregunta[] = [];
    respuestas2: Respuesta[] = [];
    resps = <any>[];
    // tipos de pregunta
    // Abierta(campo de texto), seleccion simple, verdadero y falso, escala(radio button),
    //seleccion multiple(checkbox o radio button)
    //pregunta y respuestas centradas a la izquierda
    //el titulo de la pregunta deberia ser de mayor tamaño
  constructor(private _formBuilder: FormBuilder, private pe: PreguntaEncuestaServiceService,
              private re: RespuestapreguntaService,
              private rs: RespuestaServiceService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });



    this.pe.getPreguntas(this.idE).subscribe(
        (pre: GetPregunta_Encuesta[]) => {
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
    let respuestas2: Respuesta[] = [];
    let h = 0;
    for(let j = 0; j < this.resps.length; j++){
      if (this.resps[j] === undefined){
        this.resps.splice(j, 1);
      }
    }

    for(let k = 0; k < this.preguntas2.length; k++){

      if (this.preguntas2[k].tipoPregunta === 'Abierta') {

        let r: Respuesta = {
          pregunta: this.preguntas2[k].descripcion,
          estado: 'Activo',
          respuertaAbierta: this.resps[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        h++;

        respuestas2.push(r);
      }

      if (this.preguntas2[k].tipoPregunta === 'Seleccion Simple') {

        let r: Respuesta = {
          pregunta: this.preguntas2[k].descripcion,
          estado: 'Activo',
          respuestaSimple: this.resps[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        h++;

        respuestas2.push(r);
      }

      if (this.preguntas2[k].tipoPregunta === 'Verdadero o Falso') {

        let r: Respuesta = {
          pregunta: this.preguntas2[k].descripcion,
          estado: 'Activo',
          verdaderoFalso: this.resps[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        h++;

        respuestas2.push(r);
      }

      if ( this.preguntas2[k].tipoPregunta === 'Escala') {

        let r: Respuesta = {
          pregunta: this.preguntas2[k].descripcion,
          estado: 'Activo',
          escala: this.resps[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        h++;

        respuestas2.push(r);
      }

      if (this.preguntas2[k].tipoPregunta === 'Seleccion Multiple'){
          for (let i =0; i < this.respuestas.length; i++){
           //for (let j = 0; j < this.preguntas2.length; j++){
            if ((this.respuestas[i].fkPregunta === this.preguntas2[k].idPreguntaEncuesta)
            && this.respuestas[i].completado === true){

              let r: Respuesta = {
                pregunta: this.preguntas2[k].descripcion,
                estado: 'Activo',
                respuestaMultiple: this.respuestas[i].pregunta,
                usuarioDto: 1,
                preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
              };
              respuestas2.push(r);
            }
         // }
          }
        }
    }
    console.log(this.resps);
    console.log(respuestas2);
    this.rs.createRespuestas(respuestas2);
    }
}

