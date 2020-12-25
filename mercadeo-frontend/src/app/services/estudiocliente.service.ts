import { HttpHeaders, HttpClient, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import  { Observable } from "rxjs";
import { Estudio } from '../interfaces/estudio';


@Injectable({
  providedIn: 'root'
})
export class EstudioclienteService {



  readonly ROOT_URL = '/api/estudio'

  readonly URL_ESTUDIOS = '/api/estudio?fk_usuario='

  constructor(
    private _http: HttpClient
  ) { }

  getEstudios(idUsuario: number | undefined): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this.URL_ESTUDIOS+`${idUsuario}`, {headers: httpOptions})
  }







  //Observables para estudios
  getEstudio(id: number): Observable<any> {
    const url_api =  "/api/estudio/"+`${id}`;
    return this._http.get(url_api);
  }

  getUsuarios(id: number): Observable<any> {
    const url_api = "/api/usuario/"+`${id}`;
    return this._http.get(url_api);
  }


}

