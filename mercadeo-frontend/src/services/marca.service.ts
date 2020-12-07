import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of , throwError} from "rxjs";
import { catchError, map, tap, retry } from 'rxjs/operators';
import { Marca } from 'src/interfaces/marca';


@Injectable({
  providedIn: 'root'
})
export class MarcaService {

  constructor(private http: HttpClient) { }

  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '/api/marca'


  //CRUD

  getMarcas(): Observable<Marca[]> {
    return this.http.get<Marca[]>(this.ROOT_URL)
    .pipe(retry(1),catchError(this.handleError<Marca[]>('getMarca', []))
    );
  }


  createMarca(marca: Marca): Observable<Marca>{
    console.log(JSON.stringify(marca));

    return this.http.post<Marca>(this.ROOT_URL, marca, this.httpOptions).pipe(
      tap((newMarca: Marca) => this.log(`added categoria w/ id=${newMarca.id}`)),
      catchError(this.handleError<Marca>('createMarca'))
    );
  }

  editMarca(marca: Marca): Observable<Marca>{
    console.log(JSON.stringify(marca));
    const id = typeof marca === 'number' ? marca : marca.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Marca>(url, marca, this.httpOptions).pipe(
      tap(_ => this.log(`updated marca id=${marca.id}`)),
      catchError(this.handleError<any>('editMarca'))
    );
  }

  deleteMarca(marca: Marca | number): Observable<Marca>{
    console.log(JSON.stringify(marca));
    const id = typeof marca === 'number' ? marca : marca.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Marca>(url).pipe(
      tap(_ => this.log(`deleted marca id=${id}`)),
      catchError(this.handleError<Marca>('deleteMarca'))
    );
  }


  getMarca(id: number): Observable<Marca> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<Marca>(url).pipe(
      tap(_ => this.log(`fetched marca id=${id}`)),
      catchError(this.handleError<Marca>(`getMarca id=${id}`))
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
