import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Solicitud_Estudio } from '../modelos/solicitud_estudio';

import { global } from './global';

@Injectable({
  providedIn: 'root'
})
export class SolicitudestudioService {

  public _url;
  public identity:any;
  constructor(
    private _http: HttpClient,
    
  ) { 
    this._url = global.url;
  }

  getNivelEconomico(): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + "api/nivelEconomico/buscar", {headers: headers});
  }

  getOcupacion(): Observable<any>{
    
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + "api/ocupacion/buscar", {headers: headers});
  }

  getProductos(idUsuario: number): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this._url + 'api/producto/productosCliente/'+`${idUsuario}`,{headers: headers});
  }

  registrarSolicitud(solicitudEstudio: Solicitud_Estudio): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.post(this._url + "api/solicitud_estudio/agregar",solicitudEstudio,{headers: headers});
  }

  obtenerSolicitud(idUser: number): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/solicitud_estudio/showSolicitudUsuario/'+`${idUser}`, {headers: headers})
  }

  actualizarSolicitud(solicitudEstudio: Solicitud_Estudio): Observable<any>{
    const id = solicitudEstudio.id;
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.put(this._url + "api/solicitud_estudio/actualizar/"+ `${id}`, solicitudEstudio, {headers: headers});
  }

  getSolicitud(idSolicitud: number): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/solicitud_estudio/consultar/'+`${idSolicitud}`, {headers: headers});
  }

  deleteSolicitud(solicitudEstudio: Solicitud_Estudio): Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this._url + 'api/solicitud_estudio/inactivar/'+`${solicitudEstudio.id}`, solicitudEstudio, {headers: headers});
  }
}
