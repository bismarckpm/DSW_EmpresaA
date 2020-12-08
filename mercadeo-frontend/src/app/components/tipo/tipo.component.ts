import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Tipo } from 'src/interfaces/tipo';
import { TipoService } from 'src/services/tipo.service';
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

  tipos: Tipo[] = [];

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


  delete(tipo: Tipo): void {
    const newTipo: Tipo = {
      id: tipo.id,
      nombre: tipo.nombre,
      estado: "Inactivo",
      descripcion: tipo.descripcion
    };

    if(confirm("Estas seguro de eliminar "+tipo.nombre)) {
    this._tipoService.editTipo(newTipo).subscribe(() => this.get()) ;
    }
  }


}
