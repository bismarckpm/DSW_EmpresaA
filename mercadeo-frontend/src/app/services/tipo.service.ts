import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { GetTipo, Tipo } from '../interfaces/tipo';


@Injectable({
  providedIn: 'root'
})
export class TipoService {

  tipos: Tipo[] = [];
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8181/mercadeo-backend/api/tipo';
  constructor(private http: HttpClient) { }


  getTipos(): Observable<GetTipo[]> {
    return this.http.get<GetTipo[]>(this.ROOT_URL+"/buscar").pipe(retry(1),
      catchError(this.handleError<GetTipo[]>('getTipos', []))
    );
  }



  getTipo(id: number): Observable<GetTipo> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<GetTipo>(this.ROOT_URL+"/consultar/"+id).pipe(
      tap(_ => this.log(`fetched Tipo id=${id}`)),
      catchError(this.handleError<GetTipo>(`getTipo id=${id}`))
    );

}

  createTipo(tipo: Tipo): Observable<Tipo>{
    console.log(JSON.stringify(tipo));

    return this.http.post<Tipo>(this.ROOT_URL+"/agregar", tipo, this.httpOptions).pipe(
      tap((newTipo: Tipo) => this.log(`added tipo w/ id=${newTipo.id}`)),
      catchError(this.handleError<Tipo>('createTipo'))
    );
  }

  deleteTipo(tipo: Tipo | number): Observable<Tipo>{
    console.log(JSON.stringify(tipo));
    const id = typeof tipo === 'number' ? tipo : tipo.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Tipo>(url).pipe(
      tap(_ => this.log(`deleted tipo id=${id}`)),
      catchError(this.handleError<Tipo>('deleteTipo'))
    );

  }


  editTipo(tipo: Tipo): Observable<Tipo>{
    console.log(JSON.stringify(tipo));
    const id = typeof tipo === 'number' ? tipo : tipo.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Tipo>(url, tipo, this.httpOptions).pipe(
      tap(_ => this.log(`updated tipo id=${tipo.id}`)),
      catchError(this.handleError<any>('editTipo'))
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
  console.log(`Tipo Service: ${message}`);
}

}
