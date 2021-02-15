import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OcupacionServicioService {

  constructor(private httpClient: HttpClient) { }

  onCargarOcupacion(): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8282/mercadeo-backend/api/ocupacion/buscar`);
  }
}
