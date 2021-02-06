import { Estudio, GetEstudio } from '../../../../interfaces/estudio';
import { EstudioService } from '../../../../services/estudio.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../../../admin/admin_estudio/dialogconsultarestudio/dialogconsultarestudio.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { DialogoGestionarPoblacionComponent } from '../../dialogo-gestionar-poblacion/dialogo-gestionar-poblacion.component';
import { DialogEstatusComponent } from '../../dialog-estatus/dialog-estatus.component';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { RegionEstudioService } from 'src/app/services/regionestudio.service';

@Component({
  selector: 'app-consultar-estudio-analista',
  templateUrl: './consultar-estudio-analista.component.html',
  styleUrls: ['./consultar-estudio-analista.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ConsultarEstudioAnalistaComponent implements OnInit {

  // Estados
  isWait = false;

  //  Tabla
  columnsToDisplay : string[] = ['Estudio', 'Estatus', 'Fecha', 'Opciones'];
  dataSource!: MatTableDataSource<any>;
  expandedElement: any | null;


  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;

  // Variable Estudio
  estudios: any[] = [];
  GetEstudio: GetEstudio[] = [];

  solicitudes: any;

  // Regiones
  regiones: any[] = [];
  region: any;

  // Usuarios
  public identity: any;
  public user: any;

  constructor(private estudio: EstudioService,
    private solicitud: SolicitudestudioService,
    private _loginService: LoginService,
    public dialog: MatDialog,
    private _router: Router,
    private _regionEstudioService: RegionEstudioService,

              ) { }

  ngOnInit(): void {
    this.getUser();
    this.busquedaEstudios();
  }


  // Metodo para traer todos los estudios asignados al analista
  busquedaEstudios() {
    this.isWait = true;
    this.estudio.getEstudiosAnalista(this.user.id).subscribe(
      (estudios) => {
        this.estudios = estudios;
        console.log( this.estudios)

        this.dataSource = new MatTableDataSource<any>(this.estudios)
        console.log(this.dataSource)
        this.isWait = false;

        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    );
}

  getSolicitudEstudio(id: any) {
      this.solicitud.getSolicitud(id).subscribe((data) => {this.solicitudes = data; console.log('solicitud', this.solicitudes)});
      this.buscarRegionesSolicitud(id);
  }


  // Filtro
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log('FILTER', filterValue)
  }


  // User
  getUser() {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
    console.log(this.user);
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


    //Dialogo para editar marca
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


  // Obtener Regiones
  // Paso Id Solicitud
  // Returns = Regiones dentro de una solicitud
  buscarRegionesSolicitud(idSolicitud: number){
    this._regionEstudioService.buscaRegionesSolicitud(idSolicitud).subscribe(
      response => {
        this.regiones = response;
        this.regiones = this.regiones.map(item => item = item._nombre)

        console.log('DialogBuscarRegionesSolicitud', this.regiones);

        if (this.regiones.length > 1) {
          this.region = this.regiones.join(', ')
        } else {
          this.region = this.regiones.join('')
        }

      }
    )
  }

}
