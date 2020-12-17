import { Injectable } from '@angular/core';
import { RecuperarpasswordComponent } from '../components/recuperarpassword/recuperarpassword.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from '../services/global';
import { FormBuilder, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public url: string;
  public identity:any;
  loginForm: any;
  

  constructor(
    private _http: HttpClient,
    
  ) { 

    this.url = global.url; // URL de la api;
  }

  validarCorreo(correo: any): Observable<any> {
    let email = JSON.stringify(correo);
    const url_api = '/api/usuario?correo='+`${email}`;
    return this._http.get(url_api);
  }

  iniciarSesion(user: any): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + 'api/usuario/autenticar', user, { headers: headers });
  }


getIdentity() {
    let identity = localStorage.getItem('identity');

    if (identity && identity != 'undefined') {
        this.identity = identity;
    } else {
        this.identity = null;
    }
    return this.identity;
}







}
