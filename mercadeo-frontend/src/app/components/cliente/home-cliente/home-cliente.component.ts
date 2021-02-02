import { Component, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { ProductoService } from 'src/app/services/producto.service';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogConsultaSolicitudComponent } from '../dialog-consulta-solicitud/dialog-consulta-solicitud.component';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { Router } from '@angular/router';

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

  // Estudios
  estudios: any[] = [];

  // Producto
  productos: GetProducto[] = [];

  constructor(
    // Services
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _estudioService: EstudioclienteService,
    private _productoService: ProductoService,
    // Others
    public dialog: MatDialog,
    private _router: Router,
  ) { }

  ngOnInit(): void {
    this.getUser();
    this.obtenerEstudios(this.user.id, this.isChecked, this.isChecked2);
    this.obtenerSolicitud(this.user.id)
    this.getProductoCliente();
  }

  // Dialogo
  openConsultarSolicitud(solicitud: any): void {
    const dialogRef = this.dialog.open(DialogConsultaSolicitudComponent, {
      width: '34rem',
      height : '50rem',
      data: {solicitud: solicitud}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');

    });
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
  

  obtenerEstudiosAsociados(idSolicitud: number) {
    this._estudioService.getEstudios(this.user.id).subscribe( (response) => {
      this.estudios = response;
      console.log('before', this.estudios);
      this.estudios = this.estudios.filter(item => item._idSolicitudEstudio._id == idSolicitud);
      this.estudios = this.estudios.filter(item => item._idSolicitudEstudio._id == idSolicitud);
      console.log('after', this.estudios);
    })
  }

  // Editar y Eliminar Solicitud
  editarSolicitud(solicitud: any){
    const idSolicitud = solicitud;
    this._router.navigate(['/editaSolicitud'], { queryParams: {
      idSolicitud: idSolicitud
    }});
  }

  eliminarSolicitud(solicitud: any){

    let Solicitud:  Solicitud_Estudio = {
      id: solicitud._id,
      descripcionSolicitud: solicitud._descripcionSolicitud,
      generoPoblacional: solicitud._generoPoblacional,
      fechaPeticion: solicitud._fechaPeticion,
      edadMinimaPoblacion: solicitud._edadMinimaPoblacion,
      edadMaximaPoblacion: solicitud._edadMaximaPoblacion,
      estatus: solicitud._estatus,
      estado:'I',
      conCuantasPersonasVive: solicitud._conCuantasPersonasVive,
      disponibilidadEnLinea: solicitud._disponibilidadEnLinea,
      nivelEconomicoDto: solicitud._nivelEconomico._id,
      productoDto: solicitud._producto._id,
      usuarioDto: solicitud._usuario._id,
      ocupacionDto: solicitud._ocupacion._id
    };

    console.log(Solicitud);

    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._solicitudService.deleteSolicitud(Solicitud).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(<any> error);
        }
      );
    }
    location.reload();
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
