import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Producto } from 'src/interfaces/producto';
import { Tipo } from 'src/interfaces/tipo';
import { ProductoService } from 'src/services/producto.service';
import { TipoService } from 'src/services/tipo.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-tipo',
  templateUrl: './create-tipo.component.html',
  styleUrls: ['./create-tipo.component.css']
})
export class CreateTipoComponent implements OnInit {

  tipoForm: any;
  tipos: Tipo[] = [];
  productos: Producto[] = [];
  isWait = false;

  constructor(
    private _tipoService: TipoService,
    private _productoService: ProductoService,
    private _location: Location,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this._productoService.getProductos().subscribe((data) => (this.productos = data));
    this.buildForm();
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

 
 add(): void {
  this.isWait = true;

  const newTipo: Tipo = {
    id: 0,
    nombre: this.tipoForm.get("nombre").value,
    estado: this.tipoForm.get("estado").value,
    descripcion: this.tipoForm.get("descripcion").value,
    idProducto: this.tipoForm.get("idProducto").value,
  };

  this._tipoService.createTipo(newTipo).subscribe(() => {   
    this.isWait = false;
    this.goBack() ;
  });
 }

 goBack(): void {
  this._location.back();
}

}
