import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Tipo } from 'src/interfaces/tipo';
import { ProductoService } from 'src/services/producto.service';
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
    private _productoService: ProductoService,
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
        width: '30rem',
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

}
