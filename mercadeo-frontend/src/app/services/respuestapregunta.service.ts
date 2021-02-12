import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Respuesta_Pregunta } from '../interfaces/respuesta_pregunta';
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
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url+'api/respuesta_pregunta/add', respuesta, { headers: headers });
   }

   registraRespuestaConPregunta(idPregunta: number, respuesta: any): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url+ 'api/respuesta_pregunta/addListaRespuestas/'+`${idPregunta}`,respuesta, {headers: headers} );
   }

   obtenerRespuestas(id: number): Observable<any>{
     let headers = new HttpHeaders().set('Content-Type','application/json');
     return this._http.get(this.url + 'api/respuesta_pregunta/showRespuestasPregunta/'+`${id}`, {headers: headers});
   }

   eliminarRespuesta(respuesta: Respuesta_Pregunta): Observable<any>{
    const id = respuesta.id;
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.put(this.url + 'api/respuesta_pregunta/inactivar/'+`${id}`, respuesta, {headers: headers});

   }

   obtenerRespuesta(id: number | undefined): Observable<any>{
     let headers = new HttpHeaders().set('Content-Type','application/json');
     return this._http.get(this.url + 'api/respuesta_pregunta/consultar/'+`${id}`, {headers: headers});
   }

   actualizarRespuesta(respuesta: any): Observable<any>{
     let headers = new HttpHeaders().set('Content-Type', 'application/json');
     const id = respuesta.id;
     return this._http.put(this.url + 'api/respuesta_pregunta/update/'+`${id}`,respuesta,{headers: headers});
   }


   getRespuestas(id: number): Observable<any>{
    return this._http.get(`http://localhost:8080/mercadeo-backend/api/respuesta/respuestas/${id}`);
   }
}
