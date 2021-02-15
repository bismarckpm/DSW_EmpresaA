import { Medio_Comunicacion } from './../interfaces/medio_comunicacion';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MedioComunicacionService {

  constructor(private httpClient: HttpClient) { }

  addMedio(medio: Medio_Comunicacion){
    console.log("lleegue aca");
    return this.httpClient.post(`http://45.76.60.252:8282/mercadeo-backend/api/medio_comunicacion/addMedio_comunicacion`, medio)
    .subscribe(
      response => {
        console.log('resultado de guardar telefonos' + response);
      },
      error => console.log('Error al guardar telefonos' + error)
    );
  }
}
