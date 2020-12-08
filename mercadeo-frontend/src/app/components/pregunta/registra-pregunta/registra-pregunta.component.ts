import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../models/pregunta_encuesta';
import { PreguntaService } from '../../../services/pregunta.service';
import { global } from '../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-registra-pregunta',
  templateUrl: './registra-pregunta.component.html',
  styleUrls: ['./registra-pregunta.component.css']
})
export class RegistraPreguntaComponent implements OnInit {

  public subcategorias: string[];
  public seleccionado: string;
  public pregunta_encuesta: Pregunta_Encuesta;
  public url: string;
  public status: string;

  constructor(
    private _preguntaService: PreguntaService,
    public _http: HttpClient,
    private _router: Router,
    private _route: ActivatedRoute

  ) {
    this.subcategorias = ['Cuidado personal', 'Ropa', 'Zapatos'];
    this.seleccionado = '';
    this.pregunta_encuesta = new Pregunta_Encuesta(0, '', '', 'Activo', 1, 1);
    this.url = global.url;
    this.status = '';
  }

  ngOnInit(): void {
  }

  captura() {

    console.log(this.seleccionado);
  }



  onSubmit(form: any) {
    console.log(this.pregunta_encuesta);
    this._preguntaService.registrarPregunta(this.pregunta_encuesta).subscribe(
      response => {
        console.log(response)
        
      }, error =>{
        this.status = 'error';
        console.log(<any>error);
      }
    )
  }


}
