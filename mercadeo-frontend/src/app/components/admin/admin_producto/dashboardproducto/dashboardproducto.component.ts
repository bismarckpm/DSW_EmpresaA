import { Component, OnInit } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import { User } from 'src/app/interfaces/user';
import { AlertService } from 'src/app/services/alert.service';
import { LoginService } from 'src/app/services/login.service';
import { ProductoService } from 'src/app/services/producto.service';


@Component({
  selector: 'app-dashboardproducto',
  templateUrl: './dashboardproducto.component.html',
  styleUrls: ['./dashboardproducto.component.css']
})
export class DashboardproductoComponent implements OnInit {

  //Estados
  isEmpty = false;

  // Producto
  productos: any[] = [];
  page = 10;
  pageSize = 5;

  // Usuarios
  public identity: any;
  public user: any;

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    private _productoService: ProductoService,
    private _loginService: LoginService,
    private _alertService: AlertService,

  ) {
    this.getUser();
   }

  ngOnInit(): void {
    if (this.user.idRol == 1){ 
      console.log('Soy Admin');
      this.get();
    }else {
      console.log('Soy Cliente');
      this.getProductoCliente();
    }

    console.log(this.user);
    console.log(this.productos);
  }

  // Metodos

  get(): void {
    this._productoService.getProductos().subscribe(data => {
      this.productos = data.objeto;

      this.productos = this.productos.sort((a, b) => a._estado.localeCompare(b._estado));  
      console.log('Producto',  this.productos);

        // Si esta vacio el array
        // isEmpty = true
        if (this.productos.length == 0) {
          this.isEmpty = true;
        } else {
          this.isEmpty = false;
        }

    });
  }

  getProductoCliente(): void {
    this._productoService.getProductosCliente(this.identity.id).subscribe(data => {

      this.productos = data.objeto;

      this.productos = this.productos.sort((a, b) => a._estado.localeCompare(b._estado));  

        // Si esta vacio el array
        // isEmpty = true
        if (this.productos.length == 0) {
          this.isEmpty = true;
        } else {
          this.isEmpty = false;
        }

      console.log('User',  this.identity.id);
      console.log('ProductoC',  this.productos);
    });
  }

  delete(producto: any): void {


      const delProducto: Producto = {
        id: producto._id,
        nombre: producto._nombre,
        estado: "I",
        descripcion: producto._descripcion,
        subcategoriaDto: producto._subcategoria._id,
        marcaDto: producto._marca._id,
        usuarioDto: producto._usuario._id
      };
  
      if(confirm("Estas seguro de eliminar "+producto._nombre)) {
        this._productoService.editProducto(delProducto).subscribe((response) => {
          console.log(response)
          this._alertService.error(response.mensaje+ '   Estado: '+ response.estado, this.options)

          if (this.user.idRol == 1){ 
            console.log('Soy Admin');
            this.get();
          }else {
            console.log('Soy Cliente');
            this.getProductoCliente();
          }
        }, error => this._alertService.error(error, this.options)
        );
      }
    } 



  // Obtener el User
  getUser(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol )

      if (this.user) {
        console.log(this.user)
      }
  }
  

}
