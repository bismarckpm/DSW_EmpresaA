import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Categoria } from 'src/interfaces/categoria';
import { Subcategoria } from 'src/interfaces/subcategoria';
import { CategoriaService } from 'src/services/categoria.service';
import { SubcategoriaService } from 'src/services/subcategoria.service';
import { SubcategoriaComponent } from '../../subcategoria/subcategoria.component';

@Component({
  selector: 'app-dialogsubcategoria',
  templateUrl: './dialogsubcategoria.component.html',
  styleUrls: ['./dialogsubcategoria.component.css']
})
export class DialogsubcategoriaComponent implements OnInit {

  subcategoria: Subcategoria ={
    id: 0,
    nombre: '',
    estado: '',
    descripcion: '',
    idCategoria: {
      id: 0,
      nombre: "",
      estado: ""
    },
  };
  categorias: Categoria[] = [];
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
       Validators.maxLength(10),
     ]),],
     estado: ["",
     Validators.compose([
       Validators.required]),
      ],
     descripcion: ["",
     Validators.compose([
       Validators.required]),
     ],
     idCategoria:["",
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
    const newSubcategoria: Subcategoria = {
      id: this.data.id,
      nombre: this.subcategoriaForm.get("nombre").value,
      estado: this.subcategoriaForm.get("estado").value,
      descripcion: this.subcategoriaForm.get("descripcion").value,
      idCategoria: this.subcategoriaForm.get("idCategoria").value,
    };

    console.log(newSubcategoria)
    this._subcategoriaService.editSubcategoria(newSubcategoria)
      .subscribe();
      this.dialogRef.close();
  }

getCategoria(): void {
    this._categoriaService.getCategorias().subscribe(cate => {this.categorias = cate;} )
}  


  onNoClick(): void {
    this.dialogRef.close();
  }

}
