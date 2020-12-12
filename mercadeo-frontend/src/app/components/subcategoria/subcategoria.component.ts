import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { GetSubcategoria, Subcategoria } from 'src/app/interfaces/subcategoria';
import { CategoriaService } from 'src/app/services/categoria.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';

import { DialogsubcategoriaComponent } from '../dialog/dialogsubcategoria/dialogsubcategoria.component';


@Component({
  selector: 'app-subcategoria',
  templateUrl: './subcategoria.component.html',
  styleUrls: ['./subcategoria.component.css']
})
export class SubcategoriaComponent implements OnInit {

  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }
  
  subcategorias: GetSubcategoria[] = [];

  constructor(
    private _subcategoriaService: SubcategoriaService, 
    public dialog: MatDialog,
    ) { }

  ngOnInit(): void {
    this.get();
  }
  //Dialogo
    //Dialogo para editar marca
    openDialog(id: number): void {
      console.log(id);
      const dialogRef = this.dialog.open(DialogsubcategoriaComponent, {
        width: '30rem',
        data: {id: id}
      });
  
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.get();
      });
      
    } 

  // CRUD 
  get(): void {
    this._subcategoriaService.getSubcategorias().subscribe(data => {this.subcategorias = data;} )
  }  


  delete(subcategoria: GetSubcategoria): void {

    const newSubcategoria: Subcategoria = {
      id: subcategoria._id,
      nombre: subcategoria._nombre,
      estado: "I",
      descripcion: subcategoria._descripcion,
      categoriaDto: subcategoria._categoria._id
    
    };

  
    if(confirm("Estas seguro de eliminar "+subcategoria._nombre)) {
    this._subcategoriaService.editSubcategoria(newSubcategoria).subscribe(() => this.get()) ;
    }


  }



  // TESTING


}
