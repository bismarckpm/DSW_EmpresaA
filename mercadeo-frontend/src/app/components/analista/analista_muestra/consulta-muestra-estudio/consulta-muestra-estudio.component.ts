import { trigger, state, style, transition, animate } from '@angular/animations';
import { AfterViewInit, Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { EstudioService } from 'src/app/services/estudio.service';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';
import { Location } from '@angular/common';
import { RegionEstudioService } from 'src/app/services/regionestudio.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { FormBuilder, Validators } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogoGestionarUserComponent } from '../../dialogo-gestionar-user/dialogo-gestionar-user.component';
import { TelefonoServicioService } from 'src/app/services/telefono-servicio.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { AlertService } from 'src/app/services/alert.service';
import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';

@Component({
  selector: 'app-consulta-muestra-estudio',
  templateUrl: './consulta-muestra-estudio.component.html',
  styleUrls: ['./consulta-muestra-estudio.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ConsultaMuestraEstudioComponent implements OnInit, AfterViewInit {


  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
};

  // Obtener ID Estudio
  idEstudio: any;

  // Estados
  isWait = false;
  isEmpty: any;

  // Varialbes
  encuestados: any[] = []
  public age: number = 0;
  telefono: any;
  // ID
  id = +this.route.snapshot.paramMap.get('id')!;

  // Tabla
  columnsToDisplay: string[] = ['ID', 'Encuestado', 'Correo', 'Rol', 'Opciones'];
  dataSource!: MatTableDataSource<any>;
  expandedElement: any | null;

  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;

  // Regiones
  regiones: any[] = [];
  region: any;

  // Solicitud
  estudio: any;

  constructor(
    // Route
    private route: ActivatedRoute,
    private location: Location,
    private _router: Router,
    // Forms
    private fb: FormBuilder,
    // Dialog
    public dialog: MatDialog,
    // Services
    private estudioService: EstudioService,
    private _regionEstudioService: RegionEstudioService,
    private _solicitudService: SolicitudestudioService,
    private _tlfnService: TelefonoServicioService,
    private _preguntaService: PreguntaEncuestaServiceService,
    private _userService: EncuestadoServicioService,
    ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      response =>
      {
        this.idEstudio = response;
        console.log(this.idEstudio);
      });

    this.getMuestra();
    this.getEstudio(this.idEstudio.estudio);

  }

  ngAfterViewInit() {

  }

  // Suscribe la data en la tabla 
  getMuestra(): void {
    this.isWait = true;
    this.estudioService.getPoblacion(this.idEstudio.estudio).subscribe(data => {
      this.encuestados =data.objeto;

      console.log( 'id',this.idEstudio.solicitud,'ENCUESTADOOS',  this.encuestados, 'estudio', this.idEstudio.estudio)
      this.dataSource = new MatTableDataSource<any>(this.encuestados);
      this.isWait = false;

 
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
 

    })
  }


  // Click para obtener dato usuario
  datoUsuario: any;
  getDatoUsuario(idUser: any) {
    this._userService.getDatoUsuarioPorIdUsuario(idUser).subscribe(response =>{
      this.datoUsuario = response.objeto;
      console.log('Mi User' ,this.datoUsuario)
    })
  }

  // Devuelve TRUE o FALSE si alguien respondio el estudio/encuesta
  // isEmptyForm(user:number): void {
  //   this.estudioService.getValidarParticipacion(user,this.idEstudio.estudio).subscribe( (data) => {
  //     this.isEmpty = data;
  //     console.log('Ya participo', this.isEmpty)
  //   }
  //   );
  // }

  isEmptyForm(user:number) {
    this._preguntaService.validarPreguntas(this.idEstudio.estudio, user).subscribe((data) =>{
      this.isEmpty = data.objeto;
      console.log('Ya participo', this.isEmpty)
    })
  }

  // Filtro
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log('FILTER', filterValue)
  }


  goBack(): void {
    this.location.back();
  }

  contestar(user : number){
    this._router.navigate(['/responderEncuesta', this.idEstudio.estudio,user ] );
  }

  
  // Para ir a los resultados de un estudio finalizado
  verResultados(idUser: number){
    console.log( 'ID ESTUDIO ES: ', this.idEstudio.estudio , ' del user ', idUser);
    // this._router.navigate(['/encuestarespondida'], { queryParams: {
    //   estudio:  this.idEstudio.estudio, user: idUser
    // }});

    this._router.navigate(['encuestarespondida', this.idEstudio.estudio, idUser]);

  }


  // Obtener Regiones
  // Paso Id Solicitud
  // Returns = Regiones dentro de una solicitud
  buscarRegionesSolicitud(idSolicitud: number){
    this._regionEstudioService.buscaRegionesSolicitud(idSolicitud).subscribe(
      response => {
        this.regiones = response.objeto;
        this.regiones = this.regiones.map(item => item = item._nombre)

        console.log('DialogBuscarRegionesSolicitud', this.regiones);

        if (this.regiones.length > 2) {
          this.region = this.regiones.join('')
        } else {
          this.region = this.regiones.join(', ')
        }

      }
    )
  }

  // Obtener Estudio
  // Paso Id Estudio
  // Returns = Obtengo esa Estudio para comparar el estado
  getEstudio(id: any) {
    this.estudioService.getEstudio(id).subscribe((data) => {
      this.estudio = data.objeto;
      console.log('estudio', this.estudio)
    });
}

  // Obtener Telefono
  // Paso Id Usuario
  // Returns = Obtengo Numero de Telefono
  getTelefono(id: any) {
    this._tlfnService.getTelefonos(id).subscribe((data) => {
      this.telefono = data.objeto;
      console.log(this.telefono)
    });
  }


// Dialogo Editar Datos Opcionales
// Recibe el ID del usuario, no del datoUsuario
openDialog(idUser: any): void {
  const dialogRef = this.dialog.open(DialogoGestionarUserComponent, {
    width: '100%',
    height : 'auto',
    data: {idUser: idUser}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');

  });
}


}
