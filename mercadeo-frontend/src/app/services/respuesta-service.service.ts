import { Respuesta } from 'src/app/interfaces/respuesta';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RespuestaServiceService {

  constructor(private httpClient: HttpClient) { }

  createRespuestas(respuesta: Respuesta[]) {
    console.log("lleegue aca");
    return this.httpClient.post(`http://localhost:8080/mercadeo-backend/api/respuesta/agregar`, respuesta)
    .subscribe(
      response => {
        console.log('resultado de guardar respuestas' + response);
      },
      error => console.log('Error al guardar respuestas' + error)
    );
  }
}
