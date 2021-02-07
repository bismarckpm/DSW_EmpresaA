import { Component, OnInit, ViewChild,  ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { GetEstudio } from 'src/app/interfaces/estudio';
import { User } from 'src/app/interfaces/user';
import { EstudioService } from 'src/app/services/estudio.service';
import { LoginService } from 'src/app/services/login.service';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { DialogEstatusComponent } from '../dialog-estatus/dialog-estatus.component';
import { DialogoGestionarPoblacionComponent } from '../dialogo-gestionar-poblacion/dialogo-gestionar-poblacion.component';

@Component({
  selector: 'app-analista-page',
  templateUrl: './analista-page.component.html',
  styleUrls: ['./analista-page.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AnalistaPageComponent implements OnInit {

  // Estados
  isWait = false;

  // Variable Estudio
  estudios: any[] = [];
  encuestados: any[] = [];
  encuestadosR: any[] = []

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
    

  // Tabla
  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;
  columnsToDisplay : string[] = ['Estudio', 'Estatus', 'Opciones'];
  dataSource!: MatTableDataSource<any>;

  constructor(
    private estudioService: EstudioService,
    private muestraService: MuestraAnalistaService,
    private solicitud: SolicitudestudioService,
    private _loginService: LoginService,
    public dialog: MatDialog,
    private _router: Router,
    ) { }


  ngOnInit(): void {
    this.getUser();
    this.busquedaEstudios();
    
  }

// Metodo para traer todos los estudios asignados al analista
busquedaEstudios() {
  this.isWait = true;
  this.estudioService.getEstudiosAnalista(this.user.id).subscribe(
    (estudios) => {
      this.estudios = estudios;
      this.estudios = this.estudios.sort((a, b) => a._estatus.localeCompare(b._estatus));  
      this.estudios = this.estudios.reverse();

      this.dataSource = new MatTableDataSource<any>(this.estudios)
      this.dataSource.paginator = this.paginator;
      this.isWait = false;
    }
  );
}


  // Para ir a la poblacion o muestra de un estudio
  verPoblacion(estudio: number, solicitud : number, estudioName : string){
    this._router.navigate(['/muestra'], { queryParams: {
      estudio: estudio, solicitud: solicitud, estudioName: estudioName
    }});
  }

  // Para ir a los resultados de un estudio finalizado
  verEstudio(estudio: number | undefined){
    console.log(estudio);
    this._router.navigate(['/resultadosEstudio'], { queryParams: {
      estudio: estudio
    }});
  }


  openDialog(est: GetEstudio): void {
    console.log('dialogo',est);

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
        id: est._id,
        nombre: est._nombre,
        fechaInicio: est._fechaInicio,
        fechaFinal: est._fechaFin,
        estatus: est._estatus,
        estado: est._estado,
        conclusion: '',
        solicitudEstudio: est._solicitudEstudio._id,
        usuario: est._usuario._id
      };
    const dialogRef = this.dialog.open(DialogEstatusComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
        console.log(result)
        this.busquedaEstudios();

      });
    }


    //Dialogo para editar 
    openDialog2(data: any): void {
      console.log( 'usuario',  data._solicitudEstudio._usuario)
      const dialogRef = this.dialog.open(DialogoGestionarPoblacionComponent, {
        width: '30rem',
        data: {id: data._solicitudEstudio._id, descripcion: data._solicitudEstudio._descripcionSolicitud, producto: data._solicitudEstudio._producto._id,disponibilidadEnLinea: data._solicitudEstudio._disponibilidadEnLinea, generoPoblacional: data._solicitudEstudio._generoPoblacional, nivelEconomico: data._solicitudEstudio._nivelEconomico, ocupacion: data._solicitudEstudio._ocupacion, usuario: data._solicitudEstudio._usuario._id} 
      });


      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
        console.log('The dialog was closed');
        this.busquedaEstudios();
      });


    }


// User
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
