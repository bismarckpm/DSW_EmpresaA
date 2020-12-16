import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hijo } from '../models/hijo';

@Injectable({
  providedIn: 'root'
})
export class HijoServicioService {

  constructor(private httpClient: HttpClient) { }

  createHijo(hijo: Hijo) {
    console.log("lleegue aca");
    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/hijo/addHijo`, hijo)
    .subscribe(
      response => {
        console.log('resultado de guardar hijos' + response);
      },
      error => console.log('Error al guardar hijos' + error)
    );
  }
}
