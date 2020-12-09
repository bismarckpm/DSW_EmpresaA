import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { Producto } from '../interfaces/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  // public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  // this.url + 'categoria/allCategoria'
  readonly ROOT_URL = '/api/producto'
  constructor(private http: HttpClient) { }

  productos: Producto[] = [];


  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.ROOT_URL).pipe(retry(1),
      catchError(this.handleError<Producto[]>('getProductos', []))
    );
  }


  
  getProducto(id: number): Observable<Producto> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<Producto>(url).pipe(
      tap(_ => this.log(`fetched Producto id=${id}`)),
      catchError(this.handleError<Producto>(`getProducto id=${id}`))
    );
  }


  createProducto(producto: Producto): Observable<Producto>{
    console.log(JSON.stringify(producto));

    return this.http.post<Producto>(this.ROOT_URL, producto, this.httpOptions).pipe(
      tap((newProducto: Producto) => {this.log(`added producto w/ id=${newProducto.id}`)
    }
      ),
      catchError(this.handleError<Producto>('createProducto')),
    );
  }

  deleteProducto(producto: Producto | number): Observable<Producto>{
    console.log(JSON.stringify(producto));
    const id = typeof producto === 'number' ? producto : producto.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Producto>(url).pipe(
      tap(_ => this.log(`deleted producto id=${id}`)),
      catchError(this.handleError<Producto>('deleteProducto'))
    );

  }


  editProducto(producto: Producto): Observable<Producto>{
    console.log(JSON.stringify(producto));
    const id = typeof producto === 'number' ? producto : producto.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Producto>(url, producto, this.httpOptions).pipe(
      tap(_ => this.log(`updated producto id=${producto.id}`)),
      catchError(this.handleError<any>('editProducto'))
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
  console.log(`Producto Service: ${message}`);
}

}