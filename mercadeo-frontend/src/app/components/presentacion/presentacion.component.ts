import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Presentacion } from 'src/app/interfaces/presentacion';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { DialogpresentacionComponent } from '../dialog/dialogpresentacion/dialogpresentacion.component';

@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css']
})
export class PresentacionComponent implements OnInit {

  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }

  presentaciones: Presentacion[] = [];

  constructor(
    private _presentacionService: PresentacionService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.get();
  }
  
    //Dialogo para editar marca
    openDialog(id: number): void {
      console.log(id);
      const dialogRef = this.dialog.open(DialogpresentacionComponent, {
        width: '20rem',
        data: {id: id}
      });
  
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.get();
      });
      
    } 

  //CRUD 
  get(): void {
    this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data});
  }

  delete(presentacion: Presentacion): void {
    const newP: Presentacion = {
      id: presentacion.id,
      titulo: presentacion.titulo,
      estado: "Inactivo",
      caracteristicas: presentacion.caracteristicas
    };

    if(confirm("Estas seguro de eliminar "+presentacion.titulo)) {
    this._presentacionService.editPresentacion(newP).subscribe(() => this.get()) ;
    }
  }

}
