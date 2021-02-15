import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { delay, map, retry, retryWhen, take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEncuestaServiceService {
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) { }


  listarPreguntas(id: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/pregunta_estudio/mostrarPregunta_estudio/${id}`);
  }
  getPreguntas(idE: number, idU: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/respuesta/preguntas/${idE}/${idU}`);
  }

  getPreguntasGenerales(id: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/pregunta_estudio/preguntasGenerales/${id}`);
  }

  getPreguntasRecomendadas(id: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/pregunta_estudio/preguntasRecomendadas/${id}`);
  }

  // validarPreguntas(idE: number, idU: number): Observable<any> {
  //   return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/respuesta/validarEstatus/${idE}/${idU}`, this.httpOptions
  //     )
  // }
  //  { responseType: 'text'}


  validarPreguntas(idE: number, idU: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/respuesta/validarEstatus/${idE}/${idU}`, this.httpOptions
      ).pipe(
        map((response:any) => {
          if (response.objeto == null) {
            throw new Error(); // Will be caught by `map` and reemitted as an error notification.
          }
          return response;
        }),
        retryWhen(errors => errors.pipe(take(2), delay(1000))),
      )
  }
}
