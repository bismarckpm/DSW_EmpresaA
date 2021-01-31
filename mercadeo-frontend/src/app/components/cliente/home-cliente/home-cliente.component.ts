import { Component, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { ProductoService } from 'src/app/services/producto.service';
import { GetProducto, Producto } from 'src/app/interfaces/producto';

@Component({
  selector: 'app-home-cliente',
  templateUrl: './home-cliente.component.html',
  styleUrls: ['./home-cliente.component.css']
})
export class HomeClienteComponent implements OnInit {

  // VARIABLES

  // Estados
  isWait = false;
  isEmptyS = false;
  isEmptyP = false;
  isChecked = true;
  isChecked2 = true;

  // Usuarios
  public identity: any;
  isUser = false;
  public user: User = {
    id:0,
    nombreUsuario:'',
    correo:'',
    estado:'',
    idRol:0
  };

  // Solicitud de Estudio
  solicitudes: any[] = [];
  solicitudesNuevas: any[] = [];

  // Producto
  productos: GetProducto[] = [];

  constructor(
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _estudioService: EstudioclienteService,
    private _productoService: ProductoService,

  ) { }

  ngOnInit(): void {
    this.getUser();
    this.obtenerEstudios(this.user.id, this.isChecked, this.isChecked2);
    this.obtenerSolicitud(this.user.id)
    this.getProductoCliente();
  }


  // METODOS

  // Obtener Solicitudes de Estudios

  obtenerEstudios(idUsuario: number | undefined, finalizado: boolean, proceso: Boolean){
    this._estudioService.getEstudios(idUsuario).subscribe(
      response => {
        this.solicitudes = response;

        if (finalizado == true && proceso == false) {
          this.solicitudes = this.solicitudes.filter(item => item._estatus === 'Finalizado');
        } else if (finalizado == false && proceso == true) {
          this.solicitudes = this.solicitudes.filter(item => item._estatus === 'En Proceso');
        } else if (finalizado == true && proceso == true) {
          this.solicitudes = response;
        } else if (finalizado == false && proceso == false) {
          this.solicitudes = this.solicitudes.filter(item => item._estatus === 'Nada');
        }

        // Si esta vacio el array
        // isEmpty = true
        if (this.solicitudes.length == 0) {
          this.isEmptyS = true;
        } else {
          this.isEmptyS = false;
        }


        console.log(this.solicitudes);
      } 
    )
  }

  toggleChecked(event: boolean) {
    console.log(event); //true or false
  }

  // Obtener Solicitudes Nuevas, recien Creadas
  obtenerSolicitud(idUser: number){
    this._solicitudService.obtenerSolicitud(idUser).subscribe(
      response => {
        this.solicitudesNuevas = response;
        console.log(response);

      },error => {
        console.log(<any>error);
      }
    )
  }

  // Obtener Productos de un Cliente

  getProductoCliente(): void {
    this._productoService.getProductosCliente(this.identity.id).subscribe(data => {
      this.productos = data;
      console.log('Productos',  this.productos);

        // Si esta vacio el array
        // isEmptyP = true
        if (this.productos.length == 0) {
          this.isEmptyP = true;
        } else {
          this.isEmptyP = false;
        }

    });
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
        this.isUser = true;
        console.log(this.user)
      }
  }

}
