import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Presentacion } from 'src/interfaces/presentacion';
import { PresentacionService } from 'src/services/presentacion.service';


@Component({
  selector: 'app-create-presentacion',
  templateUrl: './create-presentacion.component.html',
  styleUrls: ['./create-presentacion.component.css']
})
export class CreatePresentacionComponent implements OnInit {

  presentacion: Presentacion[] = [];
  presentacionForm: any;
  isWait = false;

  constructor(
    private _location: Location,
    private fb: FormBuilder,
    private _presentacionService: PresentacionService,
  ) { }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm(): void {
    this.presentacionForm = this.fb.group({
     titulo: ["",
     Validators.compose([
       Validators.required,
     ]),],
     caracteristicas: ["",
     Validators.compose([
       Validators.required]),
     ]
   });
 }

 
 add(): void {
  this.isWait = true;

  const newP: Presentacion = {
    id: 0,
    titulo: this.presentacionForm.get("titulo").value,
    estado: 'Activo',
    caracteristicas: this.presentacionForm.get("caracteristicas").value
  };

  this._presentacionService.createPresentacion(newP).subscribe(() => {   
    this.isWait = false;
    this.goBack() ;
  });
 }

 goBack(): void {
  this._location.back();
}

}
