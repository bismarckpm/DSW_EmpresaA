import { Estudio } from './../../models/estudio';
import { EstudioService } from './../../services/estudio.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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
    this.id = 5;
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
  const dialogRef = this.dialog.open(DialogconsultarestudioComponent, {
    width: '30rem',
    data: {id: est.id,
            tipoInstrumento: est.tipoInstrumento,
            nombre: est.nombre,
            fechaInicio: est.fechaInicio,
            fechaFin: est.fechaFin,
            estatus: est.estatus,
            estado: est.estado
          }
    });

  dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed');
    });
  }
}
