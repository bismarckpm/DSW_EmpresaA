import { Injectable } from '@angular/core';
import { RecuperarpasswordComponent } from '../components/recuperarpassword/recuperarpassword.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from '../services/global';
import { FormBuilder, Validators } from '@angular/forms';
import { Usuario } from '../interfaces/usuario';

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
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    //let email = JSON.stringify(correo);
    return this._http.post(this.url + 'api/mailer/enviarCodigo/'+`${correo}`, correo , {headers: headers});
  }

  validarCodigo(usuario: Usuario): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + 'api/mailer/validarCodigo',usuario, {headers: headers});
  }

  cambiarClaveRecuperada(usuario: Usuario): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.put(this.url + 'api/mailer/cambiarPasswordCodigo', usuario, {headers: headers} );
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







 insercionPost(){
   let headers = new HttpHeaders().set('Content-Type', 'application/json');
   return this._http.post(this.url + 'api/usuario/popular', { headers: headers });
 }





}
