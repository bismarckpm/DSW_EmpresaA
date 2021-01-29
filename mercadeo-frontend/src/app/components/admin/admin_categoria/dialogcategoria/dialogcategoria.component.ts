import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { CategoriaService } from 'src/app/services/categoria.service';
import { CategoriaComponent } from '../categoria/categoria.component';

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


  // categoria: GetCategoria = {
  //   _id: 0,
  //   _nombre: '',
  //   _estado: ''
  // };

  categoria: any;

  ngOnInit(): void {
    this.get();
  }


  save(categoria: any): void {
    console.log('Saving new Categoria' ,categoria)
    const newCa: any = {
      id: categoria.categoria.id,
      nombre: categoria.categoria.nombre,
      estado: categoria.categoria.estado
    };


    this._categoriaService.editCategoria(newCa)
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
