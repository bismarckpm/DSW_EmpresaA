import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Producto } from 'src/interfaces/producto';
import { Tipo } from 'src/interfaces/tipo';
import { ProductoService } from 'src/services/producto.service';
import { TipoService } from 'src/services/tipo.service';
import { TipoComponent } from '../../tipo/tipo.component';

@Component({
  selector: 'app-dialogtipo',
  templateUrl: './dialogtipo.component.html',
  styleUrls: ['./dialogtipo.component.css']
})
export class DialogtipoComponent implements OnInit {


  tipo: Tipo ={
    id: 0,
    nombre: '',
    estado: '',
    descripcion: '',
    idProducto: {
      "id": 0,
      "nombre": "",
      "descripcion": "",
      "idMarca": {
        "id": 0,
        "nombre": "",
        "estado": ""
      },
      "idSubcategoria": {
        "id": 0,
        "nombre": "",
        "estado": "",
        "descripcion": "",
        "idCategoria": {
          "id": 0,
          "nombre": "",
          "estado": ""
        }
      }
    }
  }; 

  productos: Producto[] = [];
  tipoForm: any;

  constructor(
    public dialogRef: MatDialogRef<TipoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Tipo,
    public _tipoService: TipoService,
    public _productoService: ProductoService,
    private fb: FormBuilder
  ) { }
  

  ngOnInit(): void {
  }


  buildForm(): void {
    this.tipoForm = this.fb.group({
     nombre: ["",
     Validators.compose([
       Validators.required,
     ]),],
     estado: ["",
     Validators.compose([
       Validators.required]),
      ],
     descripcion: ["",
     Validators.compose([
       Validators.required]),
     ],
     idProducto:["",
     Validators.compose([
       Validators.required]),
     ]
   });
 }

 get(){
  const id = this.data.id;
  console.log(id)
  this._tipoService.getTipo(id).subscribe(data => {this.tipo = data;});
}

 save(): void {

  const newTipo: Tipo = {
    id: 0,
    nombre: this.tipoForm.get("nombre").value,
    estado: this.tipoForm.get("estado").value,
    descripcion: this.tipoForm.get("descripcion").value,
    idProducto: this.tipoForm.get("idProducto").value,
  };

  console.log(newTipo)
  this._tipoService.editTipo(newTipo)
    .subscribe();
    this.dialogRef.close();

 }


 getCategoria(): void {
  this._productoService.getProductos().subscribe(data => {this.productos = data;} )
}  

  onNoClick(): void {
    this.dialogRef.close();
  }
}
