import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Tipo } from 'src/app/interfaces/tipo';
import { TipoService } from 'src/app/services/tipo.service';

@Component({
  selector: 'app-create-tipo',
  templateUrl: './create-tipo.component.html',
  styleUrls: ['./create-tipo.component.css']
})
export class CreateTipoComponent implements OnInit {

  tipoForm: any;
  tipos: Tipo[] = [];
  isWait = false;

  constructor(
    private _tipoService: TipoService,
    private _location: Location,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.buildForm();
  }


  buildForm(): void {
    this.tipoForm = this.fb.group({
     nombre: ["",
     Validators.compose([
       Validators.required,
     ]),],
     descripcion: ["",
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
    estado: 'Activo',
    descripcion: this.tipoForm.get("descripcion").value
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
