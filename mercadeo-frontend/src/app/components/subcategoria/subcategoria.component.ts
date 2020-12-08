import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Categoria } from 'src/app/interfaces/categoria';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
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
  
  idCategoria: Categoria = {
    id: 0,
    nombre: "",
    estado: ""
  }

  subcategorias: Subcategoria[] = [];
  categorias: Categoria[] = []


  constructor(
    private _subcategoriaService: SubcategoriaService, 
    private _categoriaService: CategoriaService,
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


  getCategoria(): void {
    this._categoriaService.getCategorias().subscribe(cate => {this.categorias = cate;} )
  }  

  delete(subcategoria: Subcategoria): void {
    if(confirm("Estas seguro de eliminar "+subcategoria.nombre)) {
    this._subcategoriaService.deleteSubcategoria(subcategoria).subscribe(() => this.get()) ;
    }
  }


}
