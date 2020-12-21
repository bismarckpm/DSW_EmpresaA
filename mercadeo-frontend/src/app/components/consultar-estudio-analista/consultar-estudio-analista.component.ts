import { Estudio } from '../../interfaces/estudio';
import { EstudioService } from './../../services/estudio.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../dialogconsultarestudio/dialogconsultarestudio.component';

@Component({
  selector: 'app-consultar-estudio-analista',
  templateUrl: './consultar-estudio-analista.component.html',
  styleUrls: ['./consultar-estudio-analista.component.css']
})
export class ConsultarEstudioAnalistaComponent implements OnInit {
  id: number = 0;
  idR: number = 0;
  estudios: Estudio[] = [];
  constructor(private estudio: EstudioService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.id = 10;
    this.idR = 2;
  }


  busquedaEstudios() {
    if (this.idR === 2) {
    this.estudio.getEstudiosAnalista(this.id).subscribe(
      (estudios: Estudio[]) => {
        this.estudios = estudios;
      }
    );
  }


}

openDialog(est: Estudio): void {
  console.log(est.id);

  const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: est.id,
      tipoInstrumento: est.tipoInstrumento,
      nombre: est.nombre,
      fechaInicio: est.fechaInicio,
      fechaFinal: est.fechaFinal,
      status: est.status,
      estado: est.estado

    }
  const dialogRef = this.dialog.open(DialogconsultarestudioComponent, dialogConfig);

  dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed');
    });
  }
}
