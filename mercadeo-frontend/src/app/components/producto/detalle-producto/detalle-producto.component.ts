import { Component, OnInit } from '@angular/core';
import { Routes, RouterModule, ActivatedRoute } from '@angular/router';
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

  productos: GetProducto[]=[]
  producto: GetProducto =
  {
    _id:0,
    _nombre:'',
    _estado:'',
    _descripcion:'',
    _marca:{
      _id:0,
      _nombre:'',
      _estado:'',
    },
    _subcategoria:{
      _id:0,
      _nombre:'',
      _estado:'',
      _descripcion:'',
      _categoria:{
        _id:0,
        _nombre:'',
        _estado:'',
      }
    }
  }

  constructor(
    private route: ActivatedRoute,
    private _productoService: ProductoService,
  ) { }

  ngOnInit(): void {
    this.getProducto();
  }


  getProducto(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this._productoService.getProducto(id).subscribe(data => {this.producto = data;});
  }

  
  get(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this._productoService.getProductos().subscribe(data => {this.productos = data;});
  }
}
