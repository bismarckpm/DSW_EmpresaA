import { Component, OnInit } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import { ProductoService } from 'src/app/services/producto.service';


@Component({
  selector: 'app-dashboardproducto',
  templateUrl: './dashboardproducto.component.html',
  styleUrls: ['./dashboardproducto.component.css']
})
export class DashboardproductoComponent implements OnInit {

  productos: GetProducto[] = [];
  constructor(
    private _productoService: ProductoService,
  ) { }

  ngOnInit(): void {
    this.get();
  }

  get(): void {
    this._productoService.getProductos().subscribe(data => {this.productos = data;});
  }

  delete(producto: GetProducto): void {


      const delProducto: Producto = {
        id: producto._id,
        nombre: producto._nombre,
        estado: "I",
        descripcion: producto._descripcion,
        subcategoriaDto: producto._subcategoria._id,
        marcaDto: producto._marca._id
      };
  
      if(confirm("Estas seguro de eliminar "+producto._nombre)) {
        this._productoService.editProducto(delProducto).subscribe(() => {this.get();});
      }
    } 
  

}
