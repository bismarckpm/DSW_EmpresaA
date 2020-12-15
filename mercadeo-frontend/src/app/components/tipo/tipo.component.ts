import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GetTipo, Tipo } from 'src/app/interfaces/tipo';
import { TipoService } from 'src/app/services/tipo.service';
import { DialogtipoComponent } from '../dialog/dialogtipo/dialogtipo.component';

@Component({
  selector: 'app-tipo',
  templateUrl: './tipo.component.html',
  styleUrls: ['./tipo.component.css']
})
export class TipoComponent implements OnInit {

  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }

  tipos: GetTipo[] = [];

  constructor(
    private _tipoService: TipoService,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.get();
  }

    //Dialogo
    //Dialogo para editar tipo
    openDialog(id: number): void {
      console.log(id);
      const dialogRef = this.dialog.open(DialogtipoComponent, {
        width: '20rem',
        data: {id: id}
      });
  
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.get();
      });
      
    } 

  get(): void {
    this._tipoService.getTipos().subscribe(data => {this.tipos = data});
  }


  delete(tipo: GetTipo): void {
    const newTipo: Tipo = {
      id: tipo._id,
      nombre: tipo._nombre,
      estado: "I",
      descripcion: tipo._descripcion
    };

    if(confirm("Estas seguro de eliminar "+tipo._nombre)) {
    this._tipoService.editTipo(newTipo).subscribe(() => this.get()) ;
    }
  }


}
