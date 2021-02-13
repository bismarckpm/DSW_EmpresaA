import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of , throwError} from "rxjs";
import { catchError, map, tap, retry } from 'rxjs/operators';
import { Categoria, GetCategoria } from '../interfaces/categoria';
import { AlertService } from './alert.service';
import { createTrue } from 'typescript';


@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8080/mercadeo-backend/api/categoria';

  constructor(
    public http: HttpClient,
    private alertService: AlertService,
    ) { }


  getCategorias(): Observable<any> {
    return this.http.get<any>(this.ROOT_URL+'/buscar').pipe(retry(2),
      catchError(this.handleError<any>('getCategoria', []))
    );
  }

  createCategoria(categoria: any): Observable<any>{
    console.log(JSON.stringify(categoria));

    return this.http.post<any>(this.ROOT_URL+'/agregar', categoria, this.httpOptions).pipe(
      tap((newCategoria: any) => this.log(`added categoria w/ id=${newCategoria.id}`)),
      catchError(this.handleError<any>('createCategoria'))
    );
  }

  editCategoria(categoria: any): Observable<any>{
    console.log('Service edit', JSON.stringify(categoria));
    
    return this.http.put<any>(this.ROOT_URL+'/actualizar/'+ categoria.id , categoria, this.httpOptions).pipe(
      tap(_ => this.log(`updated categoria id=${categoria.id}`)),
      catchError(this.handleError<any>('editCategoria'))
    );
  }

  deleteCategoria(categoria: Categoria | number): Observable<Categoria>{
    console.log(JSON.stringify(categoria));
    const id = typeof categoria === 'number' ? categoria : categoria.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Categoria>(url).pipe(
      tap(_ => this.log(`deleted categoria id=${id}`)),
      catchError(this.handleError<Categoria>('deleteCategoria'))
    );
  }



getCategoria(id: number): Observable<any> {
  return this.http.get<any>(this.ROOT_URL+'/consultar/'+ id).pipe(
    tap(_ => this.log(`fetched categoria id=${id}`)),
    catchError(this.handleError<any>(`getCategoria id=${id}`))
  );
}


/* GET categorias por nombre */
searchCategorias(term: string): Observable<any[]> {
  if (!term.trim()) {
    // if not search term, return empty hero array.
    return of([]);
  }
  return this.http.get<any[]>(`${this.ROOT_URL}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found categorias matching "${term}"`) :
       this.log(`no categorias matching "${term}"`)),
    catchError(this.handleError<any[]>('searchCategorias', []))
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
  console.log(`CategoriaService: ${message}`);
}


}
