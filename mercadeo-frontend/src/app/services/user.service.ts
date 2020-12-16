import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../modelos/usuario';
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



  
}
