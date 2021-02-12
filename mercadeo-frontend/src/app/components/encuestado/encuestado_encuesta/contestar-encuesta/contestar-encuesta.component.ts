import { RespuestaServiceService } from './../../../../services/respuesta-service.service';
import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';
import { GetRespuesta_Pregunta, Respuesta_Pregunta } from './../../../../interfaces/respuesta_pregunta';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { GetPregunta_Encuesta, Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { Respuesta } from 'src/app/interfaces/respuesta';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


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
    escalas: number[] = [1, 2 , 3, 4, 5];
    preguntas2: GetPregunta_Encuesta[] = [];
    respuestas: GetRespuesta_Pregunta[] = [];
    respuestas2: Respuesta[] = [];
    resps = <any>[];
    horizontalPosition: MatSnackBarHorizontalPosition = 'center';
    verticalPosition: MatSnackBarVerticalPosition = 'bottom';
    // tipos de pregunta
    // Abierta(campo de texto), seleccion simple, verdadero y falso, escala(radio button),
    //seleccion multiple(checkbox o radio button)
    //pregunta y respuestas centradas a la izquierda
    //el titulo de la pregunta deberia ser de mayor tamaño
  constructor(private _formBuilder: FormBuilder,
              private pe: PreguntaEncuestaServiceService,
              private re: RespuestapreguntaService,
              private rs: RespuestaServiceService,
              private route: ActivatedRoute,
              private navegacion: Router,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.idE = Number(this.route.snapshot.params['idEstudio']);
    this.idU = Number(this.route.snapshot.params['idUsuario']);

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });



    this.pe.getPreguntas(this.idE, this.idU).subscribe(
        (pre: GetPregunta_Encuesta[]) => {
          this.preguntas2 = pre;
          this.preguntas2[0].visible = true;
          console.log('preguntas');
          console.log(this.preguntas2);
        }
    );

    this.re.getRespuestas(this.idE).subscribe(
      (res: GetRespuesta_Pregunta[]) => {
        this.respuestas = res;
        console.log('respuestas');
        console.log(this.respuestas);
      }
    );
  }


  siguiente(index: number) {
    this.mandarRespuestas(index);

    this.preguntas2[index].visible = false;
    /* this.preguntas2[index].completada = true; */
    this.preguntas2[index + 1].visible = true;


  }

  Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async mandarRespuestas(index: number) {
    /* let respuestas2: Respuesta[] = []; */
    let h = index;

    /* for(let j = 0; j < this.resps.length; j++){
      if (this.resps[j] === undefined){
        this.resps.splice(j, 1);
      }
    } */

    /* for(let k = 0; k < this.preguntas2.length; k++){ */

    if (this.preguntas2[index].tipoPregunta === 'Abierta') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          respuertaAbierta: this.resps[h],
          usuarioDto: Number(this.idU),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.rs.createRespuestas(r);
        /* this.resps = []; */
      }

    if (this.preguntas2[index].tipoPregunta === 'Seleccion Simple') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          respuestaSimple: this.resps[h],
          usuarioDto: Number(this.idU),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.rs.createRespuestas(r);
        /* this.resps = []; */
      }

    if (this.preguntas2[index].tipoPregunta === 'Verdadero o Falso') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          verdaderoFalso: this.resps[h],
          usuarioDto: Number(this.idU),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.rs.createRespuestas(r);
        /* this.resps = []; */
      }

    if ( this.preguntas2[index].tipoPregunta === 'Escala') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          escala: this.resps[h],
          usuarioDto: Number(this.idU),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.rs.createRespuestas(r);
        /* this.resps = []; */
      }

    if (this.preguntas2[index].tipoPregunta === 'Seleccion Multiple'){
          for (let i =0; i < this.respuestas.length; i++){
           //for (let j = 0; j < this.preguntas2.length; j++){
            if ((this.respuestas[i].fkPregunta === this.preguntas2[index].idPreguntaEncuesta)
            && this.respuestas[i].completado === true){

              let r: Respuesta = {
                pregunta: this.preguntas2[index].descripcion,
                estado: 'A',
                respuestaMultiple: this.respuestas[i].pregunta,
                usuarioDto: Number(this.idU),
                preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
              };
              /* respuestas2.push(r); */
              await this.Delay(1000);
              this.rs.createRespuestas(r);
              console.log(r);
              console.log(this.respuestas[i].pregunta);

            }
         // }
          }

        //}
    }
    console.log(this.resps);
    /* console.log(respuestas2); */
    /* this.rs.createRespuestas(respuestas2); */

    if ((index + 1) === this.preguntas2.length){
    this._snackBar.open('Gracias por participar!!, Por favor espere', undefined, {
      duration: 500,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

    setTimeout(() => {
      this.navegacion.navigate(['home']);
      }, 1000);
  }
  }

    atras(){
      this.navegacion.navigate(['home']);
    }
}

