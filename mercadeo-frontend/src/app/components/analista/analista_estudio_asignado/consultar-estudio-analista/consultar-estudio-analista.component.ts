import { Estudio } from '../../../../interfaces/estudio';
import { EstudioService } from '../../../../services/estudio.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../../../admin/admin_estudio/dialogconsultarestudio/dialogconsultarestudio.component';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-consultar-estudio-analista',
  templateUrl: './consultar-estudio-analista.component.html',
  styleUrls: ['./consultar-estudio-analista.component.css']
})
export class ConsultarEstudioAnalistaComponent implements OnInit {

  // Estados
  isWait = false;

  //  Tabla
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];

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
        this.isWait = false;
      }
    );

}

openDialog(est: Estudio): void {
  console.log(est.id);

  const dialogConfig = new MatDialogConfig();

  dialogConfig.data = {
      id: est.id,
      tipoInstrumento: est.tipoDeInstrumento,
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
