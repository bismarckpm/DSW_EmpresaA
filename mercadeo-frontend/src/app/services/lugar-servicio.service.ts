import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class LugarServicioService {

  constructor(private httpClient: HttpClient) { }

  /* onCargarLugar(): Observable<any>{
    return this.httpClient.get('http://localhost:8080/usuarios');
  } */

onCargarLugar(): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8282/mercadeo-backend/api/lugar/buscar`);
  }

obtenerEstados(): Observable<any>{
  return this.httpClient.get(`http://45.76.60.252:8282/mercadeo-backend/api/lugar/buscar`);
}

obtenerMunicipios(): Observable<any>{
  return this.httpClient.get(`http://45.76.60.252:8282/mercadeo-backend/api/lugar/getMunicipios`);
}

}
