import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/interfaces/categoria';
import { CategoriaService } from 'src/services/categoria.service';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Location } from '@angular/common';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-addcategoria',
  templateUrl: './addcategoria.component.html',
  styleUrls: ['./addcategoria.component.css']
})
export class AddcategoriaComponent implements OnInit {

  categorias: Categoria[] = [];
  categoriaFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('[a-zA-Z ]*'),
  ]);

  matcher = new MyErrorStateMatcher();
  isWait = false;

  constructor(
    private _categoriaService: CategoriaService,
    private location: Location,
    ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  add(nombre: string, estado: string): void {
    nombre = nombre.trim();
    estado = estado.trim();
    if (!nombre) { return; }
    this.isWait = true;
    this._categoriaService.createCategoria({
      nombre,
      estado
    } as Categoria).subscribe(() => {this.goBack(); this.isWait = false; })  ;
  }

}
