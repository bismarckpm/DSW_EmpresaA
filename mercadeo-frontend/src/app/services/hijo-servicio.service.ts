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

  getHijos(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/hijo/HijosUsuario/${id}`);
  }

  setHijos( hijos: Hijo[]) {
    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/hijo/updateHijo`, hijos)
    .subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + <any>error),
    );
  }
}
