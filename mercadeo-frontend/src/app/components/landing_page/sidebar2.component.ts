import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { GetSolicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudesServicioService } from 'src/app/services/solicitudes-servicio.service';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { GetEstudio } from 'src/app/interfaces/estudio';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { EstudioService } from 'src/app/services/estudio.service';
import { ProductoService } from 'src/app/services/producto.service';
import { GetProducto } from 'src/app/interfaces/producto';

@Component({
  selector: 'app-sidebar2',
  templateUrl: './sidebar2.component.html',
  styleUrls: ['./sidebar2.component.css'],
  providers: [NgbCarouselConfig]
})
export class Sidebar2Component implements OnInit {
  
  isWait = false;

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

  // Solicitudes
  solicitudes: GetSolicitud_Estudio[] = [];
  // Estudios
  estudios: GetEstudio[] = [];
  // Producto
  productos: GetProducto[] = [];

  constructor(
    private _loginService: LoginService,
    private _solicitudService: SolicitudesServicioService,
    public config: NgbCarouselConfig,
    private estudio: EstudioService,
    public dialog: MatDialog,
    private navegacion: Router,
    private _snackBar: MatSnackBar,
    private _productoService: ProductoService,
    ) { 
      config.interval = 2000;
      config.keyboard = true;
      config.pauseOnHover = true;
    }


  ngOnInit(): void {
    this.getUser();
    this.getSolicitudes();
    this.busquedaEstudios();
    this.get();
  }

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


  // Solicitud de Estudio
  getSolicitudes(): void {
    this._solicitudService.getSols().subscribe(
      (data: GetSolicitud_Estudio[]) => {
        this.solicitudes = data;
        console.log(this.solicitudes);
        this.isWait = false;
      }
    );
  }


  // Get Estudios
  busquedaEstudios() {
    this.isWait=true;
    this.estudio.getEstudios(0).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
        this.isWait=false;
        console.log(this.estudios[0]._id);
        console.log(this.estudios[0]._fechaInicio);
        console.log(this.estudios[0]._fechaFin);
        console.log(this.estudios[0]._estatus);
        console.log(this.estudios[0]._nombre);

      }
    );
  }

  // Productos

  get(): void {
    this._productoService.getProductos().subscribe(data => {this.productos = data;
      console.log('Producto',  this.productos);
    });
  }



}
