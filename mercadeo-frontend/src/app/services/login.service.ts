import { Injectable } from '@angular/core';
import { RecuperarpasswordComponent } from '../components/recuperarpassword/recuperarpassword.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private _http: HttpClient
  ) { 

  }

  validarCorreo(correo: any): Observable<any> {
    let email = JSON.stringify(correo);
    const url_api = '/api/usuario?correo='+`${email}`;
    return this._http.get(url_api);
  }






}
