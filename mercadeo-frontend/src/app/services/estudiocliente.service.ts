import { HttpHeaders, HttpClient, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import  { Observable } from "rxjs";
import { tap } from 'rxjs/operators';
import { Estudio } from '../interfaces/estudio';
import { global } from '../services/global';


@Injectable({
  providedIn: 'root'
})
export class EstudioclienteService {


 public _url: any;

  readonly ROOT_URL = '/api/estudio'

  readonly URL_ESTUDIOS = '/api/estudio?fk_usuario='

  constructor(
    private _http: HttpClient,

  ) {
    this._url = global.url;
   }

  getEstudios(idUsuario: number | undefined): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/getEstudiosCliente/'+`${idUsuario}`, {headers: httpOptions})
  }


  //Observables para estudios
  resultadoEstudio(id: number): Observable<any> {
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/resultadosEstudio/'+`${id}`, {headers: httpOptions});
  }

  getUsuarios(id: number): Observable<any> {
    const url_api = "/api/usuario/"+`${id}`;
    return this._http.get(url_api);
  }

  getEstudioEspecifico(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/consultar/'+`${idEstudio}`, {headers: httpOptions})
  }

  getProductoEstudio(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/producto/getProductoEstudio/'+`${idEstudio}`, {headers: httpOptions})
  }

  cantidadParticipantes(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/contarParticipantes/'+`${idEstudio}`, {headers: httpOptions})
  }




  getEstudioRecomendados(idEstudio: number): Observable<any>{
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');
    return this._http.get(this._url + 'api/estudio/estudiosRecomendados/'+`${idEstudio}`, {headers: httpOptions}).pipe(
      tap(_ => this.log(`get estudios recomendados id=${idEstudio}`))
    );
  }


  createEstudioRecomendado(idEstudio: number, estudio: Estudio) {
    let httpOptions = new HttpHeaders().set('Content-Type','application/json');

    this._http.put('http://45.76.60.252:8282/mercadeo-backend/api/estudio/addEstudioPorRecomendacion/'+`${idEstudio}`, estudio, {headers: httpOptions})
      .subscribe(
        response => {
          console.log('crear estudio' + response);
        },
        error => console.log('Error al crear estudio' + error)
      );

  }

  private log(message: string) {
    console.log(`CategoriaService: ${message}`);
  }
}

