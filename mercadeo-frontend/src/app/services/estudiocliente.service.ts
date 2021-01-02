import { HttpHeaders, HttpClient, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import  { Observable } from "rxjs";
import { Estudio } from '../interfaces/estudio';
import { global } from '../services/global';


@Injectable({
  providedIn: 'root'
})
export class EstudioclienteService {


 public _url: any;
  
  readonly ROOT_URL = '/api/estudio'

  readonly URL_ESTUDIOS = '/api/estudio?fk_usuario='

  constructor(
    private _http: HttpClient,
    
  ) {
    this._url = global.url;
   }

  getEstudios(idUsuario: number | undefined): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/getEstudiosCliente/'+`${idUsuario}`, {headers: httpOptions})
  }


  //Observables para estudios
  resultadoEstudio(id: number): Observable<any> {
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/resultadosEstudio/'+`${id}`, {headers: httpOptions});
  }

  getUsuarios(id: number): Observable<any> {
    const url_api = "/api/usuario/"+`${id}`;
    return this._http.get(url_api);
  }

  getEstudioEspecifico(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/consultar/'+`${idEstudio}`, {headers: httpOptions})
  }

  getProductoEstudio(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/producto/getProductoEstudio/'+`${idEstudio}`, {headers: httpOptions})
  }

  cantidadParticipantes(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/contarParticipantes/'+`${idEstudio}`, {headers: httpOptions})
  }

}

