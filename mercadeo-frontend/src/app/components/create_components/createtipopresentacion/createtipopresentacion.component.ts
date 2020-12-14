import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { TipoService } from 'src/app/services/tipo.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { GetTipo, Tipo } from 'src/app/interfaces/tipo';
import { GetPresentacion, Presentacion } from 'src/app/interfaces/presentacion';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { TipoPresentacionService } from 'src/app/services/tipo-presentacion.service';
import { ProductoTipo, ProductoPresentacion } from 'src/app/interfaces/producto';

@Component({
  selector: 'app-createtipopresentacion',
  templateUrl: './createtipopresentacion.component.html',
  styleUrls: ['./createtipopresentacion.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }]
})
export class CreatetipopresentacionComponent implements OnInit {

  @Input() producto;
  productoFormTP: any;
  tipos: GetTipo[] = [];
  presentaciones: GetPresentacion[] = [];

  ptipo: ProductoTipo[] = [];
  ppresentacion: ProductoPresentacion[] = [];

  
  constructor(
    private _location: Location,
    private fb: FormBuilder,
    private _tipoService: TipoService,
    private _presentacionService: PresentacionService,
    private _tpService: TipoPresentacionService,

  ) { }

  ngOnInit(): void {
    console.log('estooy aqui',this.producto);
    this.getTipos();
    this.getPresentaciones();
    this.buildForm();
  }

  notify(): void {
    console.log('estooy aqui',this.producto);
  }

  getTipos(): void {
    this._tipoService.getTipos().subscribe(data => {this.tipos = data});
  }
 
  getPresentaciones(): void {
   this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data});
 }
  
  buildForm(): void {
  this.productoFormTP = this.fb.group({
    presentacion: ["",
    Validators.compose([
      Validators.required,
    ]),],
    tipo: ["",
    Validators.compose([
      Validators.required]),
    ]
  });
 }


 agregar(): void {

 }

 add(newTipo: number, newPresentacion: number): void {
  console.log(this.producto.id, newTipo, newPresentacion)

  const PTipo: ProductoTipo = {
    estado: 'A',
    productoDto: this.producto.id,
    tipoDto: newTipo
  };
  console.log(PTipo)
  const PPresentacion : ProductoPresentacion = {
    estado: 'A',
    productoDto: this.producto.id,
    presentacionDto: newPresentacion
  }
  console.log(PPresentacion)
  this._tpService.createProductoTipo(PTipo).subscribe();
  this._tpService.createProductoPresentacion(PPresentacion).subscribe();
}

}
