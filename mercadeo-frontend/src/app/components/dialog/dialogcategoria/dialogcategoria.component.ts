import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Categoria } from 'src/app/interfaces/categoria';
import { CategoriaService } from 'src/app/services/categoria.service';

import { CategoriaComponent } from '../../categoria/categoria.component';

@Component({
  selector: 'app-dialogcategoria',
  templateUrl: './dialogcategoria.component.html',
  styleUrls: ['./dialogcategoria.component.css']
})
export class DialogcategoriaComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<CategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Categoria,
    public _categoriaService: CategoriaService,
  ) { }


  categoria: Categoria = {
    id: 0,
    nombre: '',
    estado: ''
  };

  ngOnInit(): void {
    this.get();
  }


  save(categoria: Categoria): void {
    console.log(categoria)
    this._categoriaService.editCategoria(categoria)
      .subscribe();
      this.dialogRef.close();
  }

  // Get Single Categoria
  get(){
    const id = this.data.id;
    this._categoriaService.getCategoria(id).subscribe(data => {this.categoria = data;});
  }
  

  onNoClick(): void {
    this.dialogRef.close();
  }

}
