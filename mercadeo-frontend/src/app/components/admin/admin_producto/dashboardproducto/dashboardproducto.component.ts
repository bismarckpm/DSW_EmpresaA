import { Component, OnInit } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { ProductoService } from 'src/app/services/producto.service';


@Component({
  selector: 'app-dashboardproducto',
  templateUrl: './dashboardproducto.component.html',
  styleUrls: ['./dashboardproducto.component.css']
})
export class DashboardproductoComponent implements OnInit {

  // Producto
  productos: GetProducto[] = [];

  productosCliente: GetProducto[] = [];

  // Usuarios
  public identity: any;
  public user: User;

  constructor(
    private _productoService: ProductoService,
    private _loginService: LoginService

  ) {

    this.identity = JSON.parse(_loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
   }

  ngOnInit(): void {
    this.get();
    this.getProductoCliente();
    console.log(this.user);
    console.log(this.productos);
  }

  // Metodos

  get(): void {
    this._productoService.getProductos().subscribe(data => {this.productos = data;
      console.log('Producto',  this.productos);
    });
  }

  getProductoCliente(): void {
    this._productoService.getProductosCliente(this.identity.id).subscribe(data => {
      this.productosCliente = data;
      console.log('User',  this.identity.id);
      console.log('ProductoC',  this.productosCliente);
   
    });
  }

  delete(producto: GetProducto): void {


      const delProducto: Producto = {
        id: producto._id,
        nombre: producto._nombre,
        estado: "I",
        descripcion: producto._descripcion,
        subcategoriaDto: producto._subcategoria._id,
        marcaDto: producto._marca._id,
        usuarioDto: producto._usuario.id
      };
  
      if(confirm("Estas seguro de eliminar "+producto._nombre)) {
        this._productoService.editProducto(delProducto).subscribe(() => {this.get();});
      }
    } 
  

}
