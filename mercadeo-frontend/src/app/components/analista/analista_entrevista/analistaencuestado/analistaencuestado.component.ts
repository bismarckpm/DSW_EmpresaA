import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { GetEstudioEncuestado } from 'src/app/interfaces/estudio';
import { GetPregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { Respuesta } from 'src/app/interfaces/respuesta';
import { GetRespuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';
import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { LoginService } from 'src/app/services/login.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { RespuestaServiceService } from 'src/app/services/respuesta-service.service';
import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';
import { Location } from '@angular/common';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-analistaencuestado',
  templateUrl: './analistaencuestado.component.html',
  styleUrls: ['./analistaencuestado.component.css']
})
export class AnalistaencuestadoComponent implements OnInit {

  identity: any;
  EncuestadoCorrespondiente: any;

  isLinear = false;
    enable = false;
    showStep = false;
    isCompleted = false;
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

  constructor(
    private estudio: EstudioService,
    private navegacion: Router,
    private _loginService: LoginService,
    private _router: Router,
    private route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private preguntaEncuesta: PreguntaEncuestaServiceService,
    private respuestaEncuesta: RespuestapreguntaService,
    private respuestaService: RespuestaServiceService,
    private _encuestadoService: EncuestadoServicioService,
    private location: Location,
    private _snackBar: MatSnackBar

  ) {
    this.identity = JSON.parse(_loginService.getIdentity());


   }


   idEstudio = +this.route.snapshot.paramMap.get('idEstudio')!;
   idUser = +this.route.snapshot.paramMap.get('idUser')!;

  ngOnInit(): void {


    console.log(this.idEstudio, this.idUser)
    this.obtenerEncuestado(this.idUser);

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.preguntaEncuesta.getPreguntas(this.idEstudio,this.idUser).subscribe(
        (pre: GetPregunta_Encuesta[]) => {
          this.preguntas2 = pre;
          this.preguntas2[0].visible = true;
          console.log(this.preguntas2);
        }
    );

    this.respuestaEncuesta.getRespuestas(this.idEstudio).subscribe(
      (res: GetRespuesta_Pregunta[]) => {
        this.respuestas = res;
        console.log(this.respuestas);
      }
    );
  }

  siguiente(index: number) {
    this.mandarRespuestas(index);

    this.preguntas2[index].visible = false;
    this.preguntas2[index + 1].visible = true;
  }

  Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async mandarRespuestas(index: number) {
    /* let respuestas2: Respuesta[] = []; */
    let h = index;
    /*for(let j = 0; j < this.resps.length; j++){
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
          usuarioDto: this.idUser,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };

        this.respuestaService.createRespuestas(r);
        /* respuestas2.push(r); */
      }

    if (this.preguntas2[index].tipoPregunta === 'Seleccion Simple') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          respuestaSimple: this.resps[h],
          usuarioDto: this.idUser,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        this.respuestaService.createRespuestas(r);
        /* respuestas2.push(r); */
      }

    if (this.preguntas2[index].tipoPregunta === 'Verdadero o Falso') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          verdaderoFalso: this.resps[h],
          usuarioDto: this.idUser,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };

        this.respuestaService.createRespuestas(r);
        /* respuestas2.push(r); */
      }

    if ( this.preguntas2[index].tipoPregunta === 'Escala') {

        let r: Respuesta = {
          pregunta: this.preguntas2[index].descripcion,
          estado: 'A',
          escala: this.resps[h],
          usuarioDto: this.idUser,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };

        this.respuestaService.createRespuestas(r);
        /* respuestas2.push(r); */
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
                usuarioDto: this.idUser,
                preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
              };
              await this.Delay(1000);
              this.respuestaService.createRespuestas(r);
              console.log(r);
              console.log(this.respuestas[i].pregunta);
              /* respuestas2.push(r); */
            }
         // }
          }
       // }
    }
    console.log(this.resps);

    if ((index + 1) === this.preguntas2.length){
      this._snackBar.open('Encuesta Completada', undefined, {
        duration: 500,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
      this.goBack();
    }
    }


    obtenerEncuestado(idUser: number){
      return this._encuestadoService.BuscarUsuario(idUser).subscribe(
        response => {
          this.EncuestadoCorrespondiente = response;
          console.log(this.EncuestadoCorrespondiente);
        }, error => {
          console.log(<any>error);
        }
      )
    }


    goBack(): void {
      this.location.back();
    }
 }

