import { GetRespuesta } from './../../interfaces/respuesta';
import { RespuestaServiceService } from './../../services/respuesta-service.service';
import { Component, OnInit } from '@angular/core';
import { GetPregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { Respuesta } from 'src/app/interfaces/respuesta';
import { GetRespuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';

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
    idE: number = 0;
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

    // Usuarios
    public identity: any;
    isUser = false;
    public user: User = {
      id:0,
      nombreUsuario:'',
      correo:'',
      estado:'',
      idRol:0
    };

    idEstudio: any;

  constructor(private rsp: RespuestaServiceService,
    private location: Location,
    private _loginService: LoginService,
    private route: ActivatedRoute,
    private navegacion: Router) { }

  ngOnInit(): void {
    this.getUser();
    this.idE = Number(this.route.snapshot.params['idEstudio']);
    this.idU = Number(this.route.snapshot.params['idUsuario']);

    /* this.route.queryParams.subscribe(
      response =>
      {
        this.idEstudio = response;
        console.log('Estoy en encuestado respondido', this.idEstudio);
      }); */


    this.rsp.getRespuestasEncuestados( this.idU, this.idE).subscribe(
      (rep) => {
        this.respuestas3 = rep.objeto;
        console.log(this.respuestas3)
        // console.log(rep[0]._listaRespuestas[0]._descripcion);
        // console.log(this.respuestas3);
        // console.log(this.respuestas3[0]._enunciado);
       /*  console.log(this.respuestas3[0]._listaRespuestas.); */
      }
  );
  }

  goBack(): void {
    this.location.back();
  }


    // Obtener el User
    getUser(): void {
      this.identity = JSON.parse(this._loginService.getIdentity());
      this.user = new User(
        this.identity.id,
        this.identity.nombreUsuario,
        this.identity.correo,
        this.identity.estado,
        this.identity.idRol )

        if (this.user) {
          this.isUser = true;
          console.log(this.user)
        }
   }

   atras(){
     if (this.user.idRol == 4) {
      this.navegacion.navigate(['home']);
     } else {
       this.goBack();
     }
  }

}
