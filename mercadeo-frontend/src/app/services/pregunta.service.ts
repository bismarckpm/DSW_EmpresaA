import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pregunta_Encuesta } from '../models/pregunta_encuesta';
import { Subcategoria } from '../models/subcategoria';

import  { global } from '../services/global';

@Injectable({
  providedIn: 'root'
})
export class PreguntaService {

  public url: string;
  
  constructor(
    public _http: HttpClient
  ) { 
    this.url = global.url;
    
  }

//Métodos para guardar la data a través de JSON

  registrarPregunta(pregunta: Pregunta_Encuesta): Observable<any> {
    let json = JSON.stringify(pregunta);
    let params =  json;
    const url = '/api/preguntas';
    let headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this._http.post(url, params, { headers: headers });
  }

  actualizarPregunta(pregunta: Pregunta_Encuesta): Observable<any>{
    let json = JSON.stringify(pregunta);
    let params =  json;
    const id = typeof pregunta === 'number' ? pregunta : pregunta.id;
    const url_api =  "/api/preguntas/"+`${id}`;
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(url_api,params,{headers: headers});
  }

  consultaPregunta(id: number){
    
    const url_api =  "/api/preguntas/"+`${id}`;
    return this._http.get(url_api);
  }



  eliminarPregunta(pregunta: Pregunta_Encuesta): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    let json = JSON.stringify(pregunta);
    const id = typeof pregunta === 'number' ? pregunta : pregunta.id;
    const url_api = "/api/preguntas/"+`${id}`;
    return this._http.put(url_api,json,{headers: headers});

  }


  listaPreguntas(): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    const url_api = "/api/preguntas";
    return this._http.get(url_api, {headers: headers});
  }


  listaSubcategoria(): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded');
    const url_api = "/api/subcategorias";
    return this._http.get(url_api, {headers: headers});
  }
  
}

