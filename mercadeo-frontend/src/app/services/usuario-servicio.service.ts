import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../interfaces/usuario';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServicioService {

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
      private httpClient: HttpClient,
      private alertService: AlertService,
      ) {}

  onGuardarUser(user: Usuario) {
    this.httpClient.post('http://45.76.60.252:8080/mercadeo-backend/api/usuario/crear', user)
    .subscribe(
      response => {
        console.log('resultado de guardar usuarios' + response);
        this.alertService.success('Guardado', this.options);
      },
      error => { 
        this.alertService.error(error, this.options);
        console.log('Error al guardar usuarios' + error);
      } 
    );
}

  onCargarUsuarios(busqueda: string): Observable<any>{

      return this.httpClient.get(`http://localhost:3000/usuario?nombreUsuario=${busqueda}`);

  }

  traerUsuarios(): Observable<any> {
    return this.httpClient.get('http://45.76.60.252:8080/mercadeo-backend/api/usuario/listar/2');
  }

   getUsuariosAnalista(id: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/usuario/listar/${id}`);
  }

  getUsuariosEncuestados(id: number): Observable<any>{
    return this.httpClient.get(`http://localhost:3000/usuario?id=respuesta?fk_usuario&respuesta?fk_preguntaEstudio=pregunta_estudio?id&pregunta_estudio?fk_estudio=estudios?id&estudios?id=${id}`);
  }

   onBuscarUsuario(indice: number): Observable<any> {
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/usuario/consultar/${indice}`);
  }

  onBuscarUsuarioRol(indice: number): Observable<any>{
    return this.httpClient.get(`http://45.76.60.252:8080/mercadeo-backend/api/usuario/buscarUsuario/${indice}`);
  }

  onModificarUsuario(indice: number, usuario: Usuario) {
    this.httpClient.put(`http://45.76.60.252:8080/mercadeo-backend/api/usuario/updateUsuario/${indice}`, usuario)
    .subscribe(
      response => {
        this.alertService.success('Guardado', this.options);
        console.log('modificado exitosamente' + response)},
      error => {console.log('error modificando' + error);
      this.alertService.error(error.mensaje, this.options);
    },
    );
  }
  
  onBorrarUsuario(indice: number, usuario: Usuario) {
    this.httpClient.put(`http://45.76.60.252:8080/mercadeo-backend/api/usuario/updateUsuario/${indice}`, usuario)
    .subscribe(
      response => {
        this.alertService.success('Guardado', this.options);
        console.log('borrado exitosamente' + response)},
      error => console.log('error borrando' + error),
    );
  }
}
