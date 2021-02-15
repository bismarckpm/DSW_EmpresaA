import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of , throwError} from "rxjs";
import { catchError, map, tap, retry } from 'rxjs/operators';
import { GetMarca, Marca } from '../interfaces/marca';


@Injectable({
  providedIn: 'root'
})
export class MarcaService {

  constructor(private http: HttpClient) { }


  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', Authorization: 'my-auth-token' })
  };
  readonly ROOT_URL = '//45.76.60.252:8282/mercadeo-backend/api/marca';


  //CRUD

  getMarcas(): Observable<any> {
    return this.http.get<GetMarca[]>(this.ROOT_URL+"/buscar")
    .pipe(retry(1),catchError(this.handleError<GetMarca[]>('getMarca', []))
    );
  }


  createMarca(marca: Marca): Observable<any>{
    console.log(JSON.stringify(marca));

    return this.http.post<Marca>(this.ROOT_URL+"/agregar", marca, this.httpOptions).pipe(
      tap((newMarca: Marca) => this.log(`added categoria w/ id=${newMarca.id}`)),
      catchError(this.handleError<Marca>('createMarca'))
    );
  }

  editMarca(marca: Marca): Observable<any>{
    console.log(JSON.stringify(marca));
    const id = typeof marca === 'number' ? marca : marca.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Marca>(this.ROOT_URL+"/actualizar/"+marca.id, marca, this.httpOptions).pipe(
      tap(_ => this.log(`updated marca id=${marca.id}`)),
      catchError(this.handleError<any>('editMarca'))
    );
  }

  deleteMarca(marca: Marca | number): Observable<any>{
    console.log(JSON.stringify(marca));
    const id = typeof marca === 'number' ? marca : marca.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Marca>(url).pipe(
      tap(_ => this.log(`deleted marca id=${id}`)),
      catchError(this.handleError<Marca>('deleteMarca'))
    );
  }


  getMarca(id: number): Observable<any> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<GetMarca>(this.ROOT_URL+"/consultar/"+id).pipe(
      tap(_ => this.log(`fetched marca id=${id}`)),
      catchError(this.handleError<GetMarca>(`getMarca id=${id}`))
    );
  }



    /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

private log(message: string) {
  console.log(`MarcaService: ${message}`);
}



}
