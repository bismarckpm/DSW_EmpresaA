import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { global } from '../services/global';

@Injectable({
  providedIn: 'root'
})
export class UserprofileService {

  _url: any;
  constructor(
    private _http: HttpClient
  ) { 
    this._url = global.url;
  }

  cambiarClave(idUsuario: number, clave: string): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.put(this._url + 'api/usuario/cambiarPassword/' + `${idUsuario}`, clave , {headers: headers});
  }









}
