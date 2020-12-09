import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Producto } from 'src/app/interfaces/producto';
import { ProductoService } from 'src/app/services/producto.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { TipoService } from 'src/app/services/tipo.service';
import { MarcaService } from 'src/app/services/marca.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { Marca } from 'src/app/interfaces/marca';
import { Tipo } from 'src/app/interfaces/tipo';
import { Presentacion } from 'src/app/interfaces/presentacion';
import { ProductoTipo } from 'src/app/interfaces/producto_tipo';
import { ProductoPresentacion } from 'src/app/interfaces/producto_presentacion';


@Component({
  selector: 'app-create-producto',
  templateUrl: './create-producto.component.html',
  styleUrls: ['./create-producto.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }]
})
export class CreateProductoComponent implements OnInit {

  producto: Producto[] = [];
  subcategorias: Subcategoria[] = [];
  marcas: Marca[] = [];
  tipos: Tipo[] = [];
  presentaciones: Presentacion[] = [];
  tipoProducto: ProductoTipo[] = [];
  presentacionProducto: ProductoPresentacion[] = [];


  
  productoFormSM: any;
  productoFormData: any;
  productoFormTP: any;
  isWait = false;
  isLinear = true;

  constructor(
    private _location: Location,
    private fb: FormBuilder,
    private _productoService: ProductoService,
    private _subcategoriaService: SubcategoriaService,
    private _marcaService: MarcaService,
    private _tipoService: TipoService,
    private _presentacionService: PresentacionService,
  ) { }

  ngOnInit(): void {
    this.getSubcategorias();
    this.getMarcas();
    this.getTipos();
    this.getPresentaciones();
    this.buildForm();
    
  }

  /** FORMS para guardar
   *   id: number;
   *   nombre: string;
   *   descripcion: string;
   *   idSubcategoria: Subcategoria;
   *   idMarca: Marca;
   */

  buildForm(): void {
    this.productoFormSM = this.fb.group({
     idSubcategoria: ["",
     Validators.compose([
       Validators.required, 
     ]),],
     idMarca: ["",
     Validators.compose([
       Validators.required]),
     ]
   });

   this.productoFormData = this.fb.group({
    nombre: ["",
    Validators.compose([
      Validators.required,
    ]),],
    descripcion: ["",
    Validators.compose([
      Validators.required]),
    ]
  });

  this.productoFormTP = this.fb.group({
    idPresentacion: ["",
    Validators.compose([
      Validators.required,
    ]),],
    idTipo: ["",
    Validators.compose([
      Validators.required]),
    ]

  });

 }

 // Get Subcategoria y Marca

 getSubcategorias(): void {
   this._subcategoriaService.getSubcategorias().subscribe(data => {this.subcategorias = data});
 }

 getMarcas(): void {
   this._marcaService.getMarcas().subscribe(data => {this.marcas = data});
 }

 getTipos(): void {
   this._tipoService.getTipos().subscribe(data => {this.tipos = data});
 }

 getPresentaciones(): void {
  this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data});
}

 //ADD



 productoid: any;
 add(): void {
  this.isWait = true;

  const newProducto: Producto = {
    nombre: this.productoFormData.get("nombre").value,
    descripcion: this.productoFormData.get("descripcion").value,
    idMarca: this.productoFormSM.get("idMarca").value,
    idSubcategoria: this.productoFormSM.get("idSubcategoria").value,
  };

  this._productoService.createProducto(newProducto).subscribe(data => {   
    this.isWait = false;
    this.productoid = data;
    console.log('component',this.productoid);
  });



 }

  goBack(): void {
    this._location.back();
  }
}