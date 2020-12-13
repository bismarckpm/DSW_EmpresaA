import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Respuesta_Pregunta } from '../models/respuesta_pregunta';
import  { global } from '../services/global';


@Injectable({
  providedIn: 'root'
})
export class RespuestapreguntaService {


  public url: string; 

  constructor(
    private _http: HttpClient
  ) {
    this.url = global.url;
   }


   registrarRespuesta(respuesta: Respuesta_Pregunta): Observable<any>{
    let json = JSON.stringify(respuesta);
    let params =  json;
    const url = '/api/respuesta_pregunta';
    let headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this._http.post(url, params, { headers: headers });
   }

   obtenerRespuestas(id: number): Observable<any>{
     const url_api = '/api/respuesta_pregunta?fk_preguntaEncuesta='+`${id}`;
     let headers = new HttpHeaders().set('Content-Type','application/json');
     return this._http.get(url_api, {headers: headers});
   }

   eliminarRespuesta(respuesta: Respuesta_Pregunta): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    let json = JSON.stringify(respuesta);
    const id = typeof respuesta === 'number' ? respuesta : respuesta.id;
    const url_api = "/api/respuesta_pregunta/"+`${id}`;
    return this._http.put(url_api, json, {headers: headers});

   }

   obtenerRespuesta(id: number): Observable<any>{
     const url_api = '/api/respuesta_pregunta/'+`${id}`;
     let headers = new HttpHeaders().set('Content-Type','application/json');
     return this._http.get(url_api, {headers: headers});
   }

   actualizarRespuesta(respuesta: Respuesta_Pregunta): Observable<any>{
     let headers = new HttpHeaders().set('Content-Type', 'application/json');
     let json = JSON.stringify(respuesta);
     const id = typeof respuesta === 'number' ? respuesta : respuesta.id;
     const url_api = "/api/respuesta_pregunta/"+`${id}`;
     return this._http.put(url_api,json,{headers: headers});
   }

}
