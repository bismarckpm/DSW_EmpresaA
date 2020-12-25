import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NivelAcademicoServicioService {

  constructor(private httpClient: HttpClient) { }

  onCargarNivel(): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/nivelAcademico/buscar`);
  }
}
