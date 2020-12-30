import { Estudio } from '../../../../interfaces/estudio';
import { EstudioService } from '../../../../services/estudio.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../../../admin/admin_estudio/dialogconsultarestudio/dialogconsultarestudio.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';
import { trigger, state, style, transition, animate } from '@angular/animations';

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
  columnsToDisplay : string[] = ['_id', '_nombre', '_estado', '_usuario._nombreUsuario'];
  dataSource!: MatTableDataSource<any>;
  expandedElement: any | null;


  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;

  // Variable Estudio
  estudios: any[] = [];

  constructor(private estudio: EstudioService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.busquedaEstudios();
  }


  // Metodo para traer todos los estudios asignados al analista
  busquedaEstudios() {
    this.isWait = true;
    this.estudio.getEstudiosAnalista(4).subscribe(
      (estudios) => {
        this.estudios = estudios;
        this.dataSource = new MatTableDataSource<any>(this.estudios)
        console.log(this.dataSource)
        this.isWait = false;

        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    );
}


  // Filtro
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log('FILTER', filterValue)
  }


openDialog(est: Estudio): void {
  console.log(est.id);

  const dialogConfig = new MatDialogConfig();

  dialogConfig.data = {
      id: est.id,
      nombre: est.nombre,
      fechaInicio: est.fechaInicio,
      fechaFinal: est.fechaFin,
      estatus: est.estatus,
      estado: est.estado

    };
  const dialogRef = this.dialog.open(DialogconsultarestudioComponent, dialogConfig);

  dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed');
    });
  }
}
