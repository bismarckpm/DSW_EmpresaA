import { Component, OnInit } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import { ProductoService } from 'src/app/services/producto.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { TipoService } from 'src/app/services/tipo.service';
import { MarcaService } from 'src/app/services/marca.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { GetSubcategoria, Subcategoria } from 'src/app/interfaces/subcategoria';
import { GetMarca, Marca } from 'src/app/interfaces/marca';
import { GetTipo, Tipo } from 'src/app/interfaces/tipo';
import { GetPresentacion, Presentacion } from 'src/app/interfaces/presentacion';
import { GetProductoTipoPresentacion, ProductoTipoPresentacion } from 'src/app/interfaces/producto';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.css']
})
export class DetalleProductoComponent implements OnInit {


  productos: GetProducto[] = [];

  constructor(
    private _productoService: ProductoService,
  ) { }

  ngOnInit(): void {
  }

  get(): void {
    this._productoService.getProductos().subscribe(data => {this.productos = data;});
  }

}
