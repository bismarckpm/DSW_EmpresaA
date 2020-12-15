import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServicioService {

  constructor(private httpClient: HttpClient) { }

  onGuardarUser(user: Usuario) {
    this.httpClient.post('http://localhost:3000/usuario', user)
    .subscribe(
      response => {
        console.log('resultado de guardar usuarios' + response);
      },
      error => console.log('Error al guardar usuarios' + error)
    );
}

  onCargarUsuarios(busqueda: string): Observable<any>{
    if (busqueda === ''){
      return this.httpClient.get('http://localhost:3000/usuario');
    }else{
      return this.httpClient.get(`http://localhost:3000/usuario?nombreUsuario=${busqueda}`);
    }
  }

   getUsuariosAnalista(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:3000/usuario?fk_rol=${id}`);
  }

  getUsuariosEncuestados(id: number): Observable<any>{
    return this.httpClient.get(`http://localhost:3000/usuario?id=respuesta?fk_usuario&respuesta?fk_preguntaEstudio=pregunta_estudio?id&pregunta_estudio?fk_estudio=estudios?id&estudios?id=${id}`);
  }

   onBuscarUsuario(indice: number): Observable<any> {
    return this.httpClient.get(`http://localhost:3000/usuario/${indice}`);
  }

  onBuscarUsuarioRol(indice: number): Observable<any>{
    return this.httpClient.get(`http://localhost:3000/usuario?fk_rol=${indice}`);
  }

  onModificarUsuario(indice: number, usuario: Usuario) {
    this.httpClient.put('http://localhost:3000/usuario/' + indice, usuario)
    .subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }

  onBorrarUsuario(indice: number) {
    this.httpClient.delete('http://localhost:3000/usuario/' + indice)
    .subscribe(
      response => console.log('borrado exitosamente' + response),
      error => console.log('error borrando' + error),
    );
  }
}
