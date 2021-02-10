import { Respuesta } from 'src/app/interfaces/respuesta';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RespuestaServiceService {

  constructor(private httpClient: HttpClient) { }

  createRespuestas(respuesta: Respuesta) {
    console.log("lleegue aca");
    return this.httpClient.post(`http://localhost:8080/mercadeo-backend/api/respuesta/agregar`, respuesta)
    .subscribe(
      response => {
        console.log('resultado de guardar respuestas' + response);
      },
      error => console.log('Error al guardar respuestas' + error)
    );
  }

  getRespuestasEstudio(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/resultadosEstudio/${id}`);
  }


  getRespuestasEncuestados(id: number, id2: number): Observable<any> {
    console.log('getRespuestasEncuestados', id, id2)
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/resultadosEncuestado/${id}/${id2}`);
  }



}
