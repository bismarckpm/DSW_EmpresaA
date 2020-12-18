import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Location } from '@angular/common';
import { Categoria } from 'src/app/interfaces/categoria';
import { CategoriaService } from 'src/app/services/categoria.service';
import { User } from 'src/app/modelos/user';
import { LoginService } from 'src/app/services/login.service';

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

  
  // Usuarios
  public identity: any;
  public user: User;
  
  isWait = false;

  constructor(
    private _categoriaService: CategoriaService,
    private location: Location,
    private _loginService: LoginService

    ) { 
      
    this.identity = JSON.parse(_loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
    }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  add(nombre: string, estado: string): void {
    const id = 1;
    nombre = nombre.trim();
    estado = estado.trim();
    if (!nombre) { return; }
    this.isWait = true;
    this._categoriaService.createCategoria({
      id,
      nombre,
      estado
    } as Categoria).subscribe(() => {this.goBack(); this.isWait = false; })  ;
  }

}
