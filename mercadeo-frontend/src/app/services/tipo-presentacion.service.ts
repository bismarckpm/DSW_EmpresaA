import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductoTipo } from 'src/app/interfaces/producto_tipo';
import { ProductoPresentacion } from 'src/app/interfaces/producto_presentacion';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TipoPresentacionService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  // public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  // this.url + 'categoria/allCategoria'
  readonly ROOT_URLP = '/api/producto_presentacion';
  readonly ROOT_URLT = '/api/producto_tipo'
  tipoProducto: ProductoTipo[] = [];
  presentacionProducto: ProductoPresentacion[] = [];

  constructor(private http: HttpClient) { }


  
  createProductoTipo(productoTipo: ProductoTipo): Observable<ProductoTipo>{
    console.log(JSON.stringify(productoTipo));

    return this.http.post<ProductoTipo>(this.ROOT_URLT, productoTipo, this.httpOptions).pipe(
      tap((newProducto: ProductoTipo) => {this.log(`added producto w/ id=${newProducto}`)
    }
      ),
      catchError(this.handleError<ProductoTipo>('createProductoTipo')),
    );
  }


  createProductoPresentacion(productoPresentacion: ProductoPresentacion): Observable<ProductoPresentacion>{
    console.log(JSON.stringify(productoPresentacion));

    return this.http.post<ProductoPresentacion>(this.ROOT_URLP, productoPresentacion, this.httpOptions).pipe(
      tap((newProducto: ProductoPresentacion) => {this.log(`added producto w/ id=${newProducto}`)
    }
      ),
      catchError(this.handleError<ProductoPresentacion>('createProductoPresentacion')),
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
