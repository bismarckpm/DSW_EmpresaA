import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { TipoService } from 'src/app/services/tipo.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { Tipo } from 'src/app/interfaces/tipo';
import { Presentacion } from 'src/app/interfaces/presentacion';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { ProductoPresentacion } from 'src/app/interfaces/producto_presentacion';
import { ProductoTipo } from 'src/app/interfaces/producto_tipo';
import { TipoPresentacionService } from 'src/app/services/tipo-presentacion.service';

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
  tipos: Tipo[] = [];
  presentaciones: Presentacion[] = [];

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

 add(newTipo: number, newPresentacion: number): void {
  console.log(this.producto.id, newTipo, newPresentacion)

  const PTipo: ProductoTipo = {
    idProducto: this.producto.id,
    idTipo: newTipo
  };
  console.log(PTipo)
  const PPresentacion : ProductoPresentacion = {
    idProducto: this.producto.id,
    idPresentacion: newPresentacion
  }
  console.log(PPresentacion)
  this._tpService.createProductoTipo(PTipo).subscribe();
  this._tpService.createProductoPresentacion(PPresentacion).subscribe();
}

}
