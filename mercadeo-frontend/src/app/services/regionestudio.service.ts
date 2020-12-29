import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import  { global } from '../services/global';

@Injectable({
  providedIn: 'root'
})
export class RegionEstudioService {


    public url: string; 

  constructor(private _http: HttpClient) {
      this.url = global.url;
   }

   //Registro de regiones N-N
   registrarRegionEstudio(idSolicitud: number, regiones: any): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url+'api/region_estudio/addRegionesASolicitud/'+`${idSolicitud}`, regiones, { headers: headers });
   }




}
