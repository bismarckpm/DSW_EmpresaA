import { Component, OnInit } from '@angular/core';
import { Respuesta_Pregunta } from '../../modelos/respuesta_pregunta';
import { RespuestapreguntaService } from '../../services/respuestapregunta.service';
import { PreguntaService } from '../../services/pregunta.service';
import { Pregunta_Encuesta } from 'src/app/modelos/pregunta_encuesta';
import { Router } from '@angular/router';

@Component({
  selector: 'app-respuestapregunta',
  templateUrl: './respuestapregunta.component.html',
  styleUrls: ['./respuestapregunta.component.css'],
  providers: [RespuestapreguntaService, PreguntaService]
})
export class RespuestapreguntaComponent implements OnInit {

  public respuesta_pregunta: Respuesta_Pregunta;
  public preguntas: any;
  
  

  constructor(
    public _respuestaPreguntaService: RespuestapreguntaService,
    public _preguntaService: PreguntaService,
    public _router: Router
  ) { 
    this.respuesta_pregunta = new Respuesta_Pregunta(0,'','A',0);
    //this.preguntas = new Pregunta_Encuesta(0,'','','',0,0);
  }

  ngOnInit(): void {
    this.getPreguntas();
    //console.log(this.getPreguntas());
    
  }


  onSubmit(form: any){
    console.log(this.respuesta_pregunta);
    this._respuestaPreguntaService.registrarRespuesta(this.respuesta_pregunta).subscribe(
      response => {
        console.log(response);
        location.reload();
      }
    )
  }

  getPreguntas(){
    this._preguntaService.getPreguntasTipo().subscribe(
      response => {
        this.preguntas = response;
        console.log(response);
      }
    )
  }

}
