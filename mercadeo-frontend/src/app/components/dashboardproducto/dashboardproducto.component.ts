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

}
