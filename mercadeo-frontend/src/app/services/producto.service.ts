import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { GetProducto, Producto, ProductoTipoPresentacion } from '../interfaces/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', Authorization: 'my-auth-token' })
  };

  readonly ROOT_URL = '//localhost:8181/mercadeo-backend/api/producto';
  constructor(private http: HttpClient) { }

  productos: Producto[] = [];


  getProductos(): Observable<GetProducto[]> {
    return this.http.get<GetProducto[]>(this.ROOT_URL+"/buscar").pipe(retry(1),
      catchError(this.handleError<GetProducto[]>('getProductos', []))
    );
  }


  
  getProducto(id: number): Observable<GetProducto> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<GetProducto>(url).pipe(
      tap(_ => this.log(`fetched Producto id=${id}`)),
      catchError(this.handleError<GetProducto>(`getProducto id=${id}`))
    );
  }


  createProducto(producto: Producto, tipo_presentacion: ProductoTipoPresentacion[]): Observable<any>{
    // console.log(JSON.stringify(producto));
    // console.log(JSON.stringify(tipo_presentacion));

  
    return this.http.post<any>(this.ROOT_URL+"/agregar", producto, this.httpOptions).pipe(
      tap((newProducto: Producto) => {this.log(`added producto w/ id=${newProducto.id}`)
    }
      ),
      catchError(this.handleError<Producto>('createProducto')),
    );
  }

  deleteProducto(producto: Producto | number): Observable<Producto>{
    console.log(JSON.stringify(producto));
    const id = typeof producto === 'number' ? producto : producto.id;
    const url = `${this.ROOT_URL}/deleteProducto/${id}`;

    return this.http.delete<Producto>(url,  this.httpOptions).pipe(
      tap(_ => this.log(`deleted producto id=${id}`)),
      catchError(this.handleError<Producto>('deleteProducto'))
    );

  }


  editProducto(producto: Producto): Observable<Producto>{
    console.log(JSON.stringify(producto));
    const id = typeof producto === 'number' ? producto : producto.id;
    const url = `${this.ROOT_URL}/actualizar/${id}`;

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

// const product: Producto = { id: producto.id, nombre: producto.nombre, estado: producto.estado,
//   descripcion: producto.descripcion, marcaDto: producto.marcaDto, subcategoriaDto: producto.subcategoriaDto  };

//   const tp: ProductoTipoPresentacion= {  estado: tipo_presentacion.estado, productoDto: tipo_presentacion.productoDto,
//   tipoDto: tipo_presentacion.tipoDto, presentacionDto: tipo_presentacion.presentacionDto  };
  

//   let httpParams: HttpParams = new HttpParams();
//   httpParams = httpParams.append('nombre', product.nombre);
//   httpParams = httpParams.append('estado', product.estado);
//   httpParams = httpParams.append('descripcion', product.descripcion.toString());
//   httpParams = httpParams.append('subcategoriaDto', product.subcategoriaDto.toString());
//   httpParams = httpParams.append('marcaDto', product.marcaDto.toString());

//   let httpT: HttpParams = new HttpParams();
//   httpParams = httpParams.append('productoDto', tp.productoDto.toString());
//   httpParams = httpParams.append('estado', tp.estado);
//   httpParams = httpParams.append('tipoDto', tp.tipoDto.toString());
//   httpParams = httpParams.append('presentacionDto', tp.presentacionDto.toString());

