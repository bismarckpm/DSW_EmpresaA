import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Producto, ProductoTipoPresentacion } from 'src/app/interfaces/producto';
import { ProductoService } from 'src/app/services/producto.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { TipoService } from 'src/app/services/tipo.service';
import { MarcaService } from 'src/app/services/marca.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { GetSubcategoria, Subcategoria } from 'src/app/interfaces/subcategoria';
import { GetMarca, Marca } from 'src/app/interfaces/marca';
import { GetTipo, Tipo } from 'src/app/interfaces/tipo';
import { GetPresentacion, Presentacion } from 'src/app/interfaces/presentacion';



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
  subcategorias: GetSubcategoria[] = [];
  marcas: GetMarca[] = [];
  
  tipos: GetTipo[] = [];
  presentaciones: GetPresentacion[] = [];
  tipoProducto: ProductoTipoPresentacion[] = [];
  presentacionProducto: ProductoTipoPresentacion[] = [];


  productoid: any;
  productoForm: any;
  productoForm2: any;
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
   *   subcategoriaDto: Subcategoria;
   *   marcaDto: Marca;
   */

  buildForm(): void {
    this.productoForm = this.fb.group({
      subcategoriaDto: ["",
      Validators.compose([
        Validators.required, 
      ]),],
      marcaDto: ["",
      Validators.compose([
        Validators.required]),
      ]
    });
    this.productoForm2 = this.fb.group({
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
    tp: this.fb.array([])
  });
 }

 //Devuelve los valores en un array
 get tp() : FormArray {
  return this.productoFormTP.get("tp") as FormArray
}
//Genera nuevo form
new(): FormGroup {
  return this.fb.group({
    productoDto: 1,
    estado: 'A',
    tipoDto: '',
    presentacionDto: '',
  })
}
//Agrega nuevo
addTP() {
  this.tp.push(this.new());
}
//Remueve
remove(i:number) {
  this.tp.removeAt(i);
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


 add(): void {
  this.isWait = true;

  const newProducto: Producto = {
    nombre: this.productoForm2.get("nombre").value,
    descripcion: this.productoForm2.get("descripcion").value,
    estado: 'A',
    marcaDto: this.productoForm.get("marcaDto").value,
    subcategoriaDto: this.productoForm.get("subcategoriaDto").value,
  };

  const t: ProductoTipoPresentacion = {
    productoDto: 1,
    estado: 'A',
    tipoDto: 1,
    presentacionDto: 1
  };


  console.log( this.productoFormTP.value)
  

  this._productoService.createProducto(newProducto, t).subscribe(data => {   
    this.isWait = false;
    this.productoid = data;
    console.log('component',this.productoid);
  });

 }

  goBack(): void {
    this._location.back();
  }
}