import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pregunta_Encuesta } from '../interfaces/pregunta_encuesta';
import { Subcategoria } from '../modelos/subcategoria';

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
    const url = '//localhost:8080/mercadeo-backend/api/pregunta_encuesta/add';
    let headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this._http.post(url,pregunta, { headers: headers });
  }

  actualizarPregunta(pregunta: Pregunta_Encuesta): Observable<any>{
    //const id = typeof pregunta === 'number' ? pregunta : pregunta.id;
    const id = pregunta.id;
    console.log(pregunta);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + 'api/pregunta_encuesta/update/'+`${id}`,pregunta,{headers: headers});
  }

  consultaPregunta(id: number){
    return this._http.get(this.url + 'api/pregunta_encuesta/consultar/'+`${id}`);
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

