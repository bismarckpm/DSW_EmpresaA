import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pregunta_Encuesta } from '../interfaces/pregunta_encuesta';
import { Subcategoria } from '../interfaces/subcategoria';

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
    
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    console.log(pregunta);

    return this._http.post(this.url + 'api/pregunta_encuesta/add',pregunta, { headers: headers });
  }

  actualizarPregunta(pregunta: Pregunta_Encuesta): Observable<any>{
    //const id = typeof pregunta === 'number' ? pregunta : pregunta.id;
    const id = pregunta.id;
    console.log(pregunta);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + 'api/pregunta_encuesta/update/'+`${id}`,pregunta,{headers: headers});
  }

  consultaPregunta(id: number){
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'api/pregunta_encuesta/consultar/'+`${id}` ,  {headers: headers});
  }



  eliminarPregunta(pregunta: Pregunta_Encuesta): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    const id = pregunta.id;
    return this._http.put(this.url + 'api/pregunta_encuesta/inactivar/'+`${id}`,pregunta,{headers: headers});

  }


  listaPreguntas(): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'api/pregunta_encuesta/show', {headers: headers});
  }


  listaSubcategoria(): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');

    return this._http.get(this.url + 'api/subcategoria/buscar', {headers: headers});
  }


  getPreguntasTipo(): Observable<any>{

    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this.url + 'api/pregunta_encuesta/showConOpciones', {headers: headers})
  }


}

