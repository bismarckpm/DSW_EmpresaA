import { Dato_Usuario } from '../models/dato_usuario';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class EncuestadoServicioService {
  constructor(private httpClient: HttpClient) {
  }

  //Guadar Encuestado
  onGuardarUsuario(user: Dato_Usuario) {
      this.httpClient.post('http://localhost:8080/mercadeo-backend/api/dato-usuario/crear', user)
      .subscribe(
        response => {
          console.log('resultado de guardar encuestados' + response);
        },
        error => console.log('Error al guardar encuestados' + error)
      );
  }

  onCargarUsuarios(busqueda: string): Observable<any>{
  
    if (busqueda === ''){
    
      return this.httpClient.get('http://localhost:3000/encuestados');
  
    }else{
    
      return this.httpClient.get(`http://localhost:3000/encuestados?primerNombre=${busqueda}`);
  
    }
  
  }

  onBuscarUsuario(indice: number): Observable<any>{

    return this.httpClient.get(`http://localhost:3000/encuestados?id=${indice}`);

  }

}
