import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';
import  { global } from './global';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  public url: string;

  constructor(
    public _http: HttpClient
  ) {
    this.url = global.url;
  }


  iniciarSesion(usuario: Usuario): Observable<any> {

    let json = JSON.stringify(usuario);
    let params = 'json=' + json;
    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');

    return this._http.post(this.url + 'iniciarSesion', params, { headers: headers });
  }


  
}
