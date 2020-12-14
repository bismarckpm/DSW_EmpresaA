import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Solicitud_Estudio } from '../models/solicitud_estudio';

@Injectable({
  providedIn: 'root'
})
export class SolicitudestudioService {

  public identity;
  constructor(
    private _http: HttpClient
  ) { }

  getNivelEconomico(): Observable<any>{
    const url_api = '/api/nivel_economico';
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(url_api, {headers: headers});
  }

  getOcupacion(): Observable<any>{
    const url_api = "/api/ocupacion";
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(url_api, {headers: headers});
  }

  getProductos(idUsuario: number): Observable<any>{
    const url_api = "/api/producto?fk_usuario="+`${idUsuario}`;
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(url_api,{headers: headers});
  }

  registrarSolicitud(solicitudEstudio: Solicitud_Estudio): Observable<any>{
    const url_api = "/api/solicitud_estudio";
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.post(url_api,solicitudEstudio,{headers: headers});
  }

}
