import { Component, OnInit } from '@angular/core';
import { Respuesta_Pregunta } from '../../interfaces/respuesta_pregunta';
import { RespuestapreguntaService } from '../../services/respuestapregunta.service';
import { PreguntaService } from '../../services/pregunta.service';
import { Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { Router } from '@angular/router';

@Component({
  selector: 'app-respuestapregunta',
  templateUrl: './respuestapregunta.component.html',
  styleUrls: ['./respuestapregunta.component.css'],
  providers: [RespuestapreguntaService, PreguntaService]
})
export class RespuestapreguntaComponent implements OnInit {

  
  public preguntas: any;
  
  respuesta_pregunta: Respuesta_Pregunta = {
    id: 0,
    nombre: '',
    estado: 'A', 
    preguntaEncuestaDto: 0
  };
  

  constructor(
    public _respuestaPreguntaService: RespuestapreguntaService,
    public _preguntaService: PreguntaService,
    public _router: Router
  ) { 
    
    
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
