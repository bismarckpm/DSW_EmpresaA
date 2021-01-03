import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hijo } from '../interfaces/hijo';

@Injectable({
  providedIn: 'root'
})
export class HijoServicioService {

  constructor(private httpClient: HttpClient) { }

  createHijo(hijos: Hijo[]) {
    console.log("lleegue aca");
    return this.httpClient.post(`http://localhost:8080/mercadeo-backend/api/hijo/addHijo`, hijos)
    .subscribe(
      response => {
        console.log('resultado de guardar hijos' + response);
      },
      error => console.log('Error al guardar hijos' + error)
    );
  }
}
