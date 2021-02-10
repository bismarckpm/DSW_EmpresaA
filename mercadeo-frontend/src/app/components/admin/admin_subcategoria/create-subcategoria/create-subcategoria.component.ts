import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { CategoriaService } from 'src/app/services/categoria.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-create-subcategoria',
  templateUrl: './create-subcategoria.component.html',
  styleUrls: ['./create-subcategoria.component.css']
})
export class CreateSubcategoriaComponent implements OnInit {
  subcategoriaForm: any;
  subcategoria: Subcategoria[] = [];
  categorias: Categoria[] = [];
  isWait = false;

  
  // Usuarios
  public identity: any;
  public user: User;

  constructor(
    private _subcategoriaService: SubcategoriaService,
    private _categoriaService: CategoriaService,
    private _location: Location,
    private fb: FormBuilder,
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
     this._categoriaService.getCategorias().subscribe((data) =>{ 
       (this.categorias = data.categorias); 
       this.propChanged();
       } );
 
    

    this.buildForm();
  }


  propChanged():void {
    this.categorias = this.categorias.filter(item => item.estado === 'A');
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
     categoriaDto:["",
     Validators.compose([
       Validators.required]),
     ]
   });
 }

 add(): void {
  this.isWait = true;

  const newSubcategoria: Subcategoria = {
    id: 0,
    nombre: this.subcategoriaForm.get("nombre").value,
    estado: 'A',
    descripcion: this.subcategoriaForm.get("descripcion").value,
    categoriaDto: this.subcategoriaForm.get("categoriaDto").value,
  };

  this._subcategoriaService.createSubcategoria(newSubcategoria).subscribe(() => {   
    this.isWait = false;
    this.goBack() ;
  });
 }

 goBack(): void {
  this._location.back();
}

}
