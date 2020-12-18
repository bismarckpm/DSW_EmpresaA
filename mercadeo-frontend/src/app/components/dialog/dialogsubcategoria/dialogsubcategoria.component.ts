import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { GetSubcategoria, Subcategoria } from 'src/app/interfaces/subcategoria';
import { CategoriaService } from 'src/app/services/categoria.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';

import { SubcategoriaComponent } from '../../subcategoria/subcategoria.component';

@Component({
  selector: 'app-dialogsubcategoria',
  templateUrl: './dialogsubcategoria.component.html',
  styleUrls: ['./dialogsubcategoria.component.css']
})
export class DialogsubcategoriaComponent implements OnInit {


  subcategoria: GetSubcategoria ={
    _id: 0,
    _nombre: '',
    _estado: '',
    _descripcion: '',
    _categoria: {
      _id: 0,
      _nombre: '',
      _estado: ''
    }
  };


  categorias: GetCategoria[] = [];
  subcategoriaForm: any;

  constructor(
    public dialogRef: MatDialogRef<SubcategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Subcategoria,
    public _subcategoriaService: SubcategoriaService,
    public _categoriaService: CategoriaService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.get();
    this.getCategoria();
    this.buildForm();
  }




  buildForm(): void {
    this.subcategoriaForm = this.fb.group({
     nombre: ["",
     Validators.compose([
       Validators.required,
       Validators.minLength(2),
     ]),],
     descripcion: ["",
     Validators.compose([
       Validators.required]),
     ],
     categoriaDto:[this.data.categoriaDto,
     Validators.compose([
       Validators.required]),
     ]
   });
 }

  //CRUD

  get(){
    const id = this.data.id;
    console.log(id)
    this._subcategoriaService.getSubcategoria(id).subscribe(data => {this.subcategoria = data;});
  }

  save(): void {
    console.log("newSubcategoria aaaqui")

    const newSubcategoria: Subcategoria = {
      id: this.data.id,
      nombre: this.subcategoriaForm.get("nombre").value,
      estado: this.data.estado,
      descripcion: this.subcategoriaForm.get("descripcion").value,
      categoriaDto: this.subcategoriaForm.get("categoriaDto").value
    };

    console.log(newSubcategoria)
    this._subcategoriaService.editSubcategoria(newSubcategoria)
      .subscribe();
      this.dialogRef.close();
  }

getCategoria(): void {
    this._categoriaService.getCategorias().subscribe(cate => {this.categorias = cate;  this.categorias = this.categorias.filter(item => item._estado === 'A');} )
}  


  onNoClick(): void {
    this.dialogRef.close();
  }

}
