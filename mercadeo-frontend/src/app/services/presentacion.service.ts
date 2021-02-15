import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { GetPresentacion, Presentacion } from '../interfaces/presentacion';

@Injectable({
  providedIn: 'root'
})
export class PresentacionService {

  presentaciones: Presentacion[] = [];
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', Authorization: 'my-auth-token' })
  };
  readonly ROOT_URL = '//45.76.60.252:8080/mercadeo-backend/api/presentacion';
  constructor(private http: HttpClient) { }


  getPresentaciones(): Observable<any> {
    return this.http.get<GetPresentacion[]>(this.ROOT_URL+"/showPresentacion").pipe(retry(1),
      catchError(this.handleError<GetPresentacion[]>('getPresentaciones', []))
    );
  }



  getPresentacion(id: number): Observable<any> {
    const url = `${this.ROOT_URL}/consultar/${id}`;
    return this.http.get<GetPresentacion>(url).pipe(
      tap(_ => this.log(`fetched Presentacion id=${id}`)),
      catchError(this.handleError<GetPresentacion>(`getPresentacion id=${id}`))
    );

}

  createPresentacion(presentacion: Presentacion): Observable<any>{
    console.log(JSON.stringify(presentacion));

    return this.http.post<Presentacion>(this.ROOT_URL+"/addPresentacion", presentacion, this.httpOptions).pipe(
      tap((newPresentacion: Presentacion) => this.log(`added presentacion w/ id=${newPresentacion.id}`)),
      catchError(this.handleError<Presentacion>('createPresentacion'))
    );
  }

  deletePresentacion(presentacion: Presentacion | number): Observable<any>{
    console.log(JSON.stringify(presentacion));
    const id = typeof presentacion === 'number' ? presentacion : presentacion.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Presentacion>(url).pipe(
      tap(_ => this.log(`deleted presentacion id=${id}`)),
      catchError(this.handleError<Presentacion>('deletePresentacion'))
    );

  }


  editPresentacion(presentacion: Presentacion): Observable<any>{
    console.log(JSON.stringify(presentacion));
    const id = typeof presentacion === 'number' ? presentacion : presentacion.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<any>(this.ROOT_URL+"/updatePresentacion/"+id, presentacion, this.httpOptions).pipe(
      tap(_ => this.log(`updated presentacion id=${presentacion.id}`)),
      catchError(this.handleError<any>('editPresentacion'))
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
