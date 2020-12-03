import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Telefono } from '../models/telefono';

@Injectable({
  providedIn: 'root'
})
export class TelefonoServicioService {

  constructor(private httpClient: HttpClient) { }

  createTelefono(telefono: Telefono){
    console.log("lleegue aca");
    return this.httpClient.post(`http://localhost:3000/telefono`, telefono)
    .subscribe(
      response => {
        console.log('resultado de guardar telefonos' + response);
      },
      error => console.log('Error al guardar telefonos' + error)
    );
  }
}
