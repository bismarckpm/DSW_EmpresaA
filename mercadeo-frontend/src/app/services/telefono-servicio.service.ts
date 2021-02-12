import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Telefono } from '../interfaces/telefono';

@Injectable({
  providedIn: 'root'
})
export class TelefonoServicioService {

  constructor(private httpClient: HttpClient) { }

  createTelefono(telefonos: Telefono[]){
    console.log("lleegue aca");
    return this.httpClient.post(`http://localhost:8080/mercadeo-backend/api/telefono/addTelefono`, telefonos)
    .subscribe(
      response => {
        console.log('resultado de guardar telefonos' + response);
      },
      error => console.log('Error al guardar telefonos' + error)
    );
  }

  getTelefonos(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/telefono/TelefonosUsuario/${id}`);
  }

  setTelefonos( telefonos: Telefono[]) {
    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/telefono/updateTelefono`, telefonos)
    .subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + <any>error),
    );
  }
}
