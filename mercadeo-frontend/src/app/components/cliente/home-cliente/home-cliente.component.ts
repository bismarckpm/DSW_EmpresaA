import { Component, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { ProductoService } from 'src/app/services/producto.service';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import {MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogConsultaSolicitudComponent } from '../dialog-consulta-solicitud/dialog-consulta-solicitud.component';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { Router } from '@angular/router';
import { GetEstudio } from 'src/app/interfaces/estudio';
import { DialogconsultarestudioComponent } from '../../admin/admin_estudio/dialogconsultarestudio/dialogconsultarestudio.component';
import { AlertService } from 'src/app/services/alert.service';

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
  isEmptySN = false;
  isChecked = true;
  isChecked2 = true;

  // Pag
  page = 12;
  pageSize = 6;
  
  page1 = 12;
  pageSize1 = 6;

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

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    // Services
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _estudioService: EstudioclienteService,
    private _productoService: ProductoService,
    private _alertService: AlertService,
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

  // Ver datos del estudio
  openDialog(est: GetEstudio): void {
    console.log(est._id);

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: est._id,
      nombre: est._nombre,
      fechaInicio: est._fechaInicio,
      fechaFin: est._fechaFin,
      estatus: est._estatus,
      estado: est._estado
    };

    const dialogRef = this.dialog.open(DialogconsultarestudioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }


  // METODOS

  // Obtener Solicitudes de Estudios

  obtenerEstudios(idUsuario: number | undefined, finalizado: boolean, proceso: Boolean){
    this._estudioService.getEstudios(idUsuario).subscribe(
      response => {
        
        this.solicitudes = response.objeto;
        console.log(response.solicitudes);

        if (finalizado == true && proceso == false) {
          this.solicitudes = this.solicitudes.filter(item => item._estatus === 'Finalizado');
          this._alertService.success("Estudios finalizados cargados con Éxito!!", this.options);
        } else if (finalizado == false && proceso == true) {
          this.solicitudes = this.solicitudes.filter(item => item._estatus === 'En Proceso');
          this._alertService.success("Estudios en proceso cargados con Éxito!! ", this.options);
        } else if (finalizado == true && proceso == true) {
          this.solicitudes = response.objeto;
          this._alertService.success("Estudios cargados con Éxito!!", this.options);
        } else if (finalizado == false && proceso == false) {
          this.solicitudes = this.solicitudes.filter(item => item._estatus === 'Nada');
          this._alertService.info("Aún no tienes ningún estudio :( :(  ", this.options);
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
       

        //Response nuevo. Ojo pelao 
        this.solicitudesNuevas = response.objeto;
        console.log(response.objeto);


        this.solicitudesNuevas = this.solicitudesNuevas.filter(item => item._estado === 'A' || item._estado === 'Activo'  )

        // Si esta vacio el array
        // isEmpty = true
        if (this.solicitudesNuevas.length == 0) {
          this.isEmptySN = true;
        } else {
          this.isEmptySN = false;
        }

        console.log(response);

      },error => {
        console.log(<any>error);
      }
    )
  }
  

  obtenerEstudiosAsociados(idSolicitud: number) {
    this._estudioService.getEstudios(this.user.id).subscribe( (response) => {
      

      
      this.estudios = response.objeto; 
      console.log(response.objeto);

      console.log('before', this.estudios);
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
    console.log(solicitud);

    let Solicitud: Solicitud_Estudio
    if(solicitud._ocupacion == null){

      Solicitud = {
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
      ocupacionDto: 0
    };
    console.log(Solicitud);
  } else {
      Solicitud= {
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
  }

   

    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._solicitudService.deleteSolicitud(Solicitud).subscribe(
        response => {
          console.log(response.objeto);
          this._alertService.success(response.mensaje + '' + response.error);
          location.reload();
        },
        error => {
          console.log(<any> error);
          this._alertService.error(error.mensaje + '' + error.estado);
        }
      );
    }
   
  }

  // Obtener Productos de un Cliente

  getProductoCliente(): void {
    this._productoService.getProductosCliente(this.identity.id).subscribe(data => {
      
      

        
        this.productos = data.objeto;
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
