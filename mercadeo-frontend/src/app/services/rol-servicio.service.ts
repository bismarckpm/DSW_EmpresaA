import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolServicioService {

  constructor(private httpClient: HttpClient) { }

  onCargarRoles(): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/rol/buscar`);
  }

  onCargarRol(Id: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/rol/consultar/${Id}`);
  }
}
