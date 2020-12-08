import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Location } from '@angular/common';
import { FindValueSubscriber } from 'rxjs/internal/operators/find';
import { Marca } from 'src/app/interfaces/marca';
import { MarcaService } from 'src/app/services/marca.service';


@Component({
  selector: 'app-create-marca',
  templateUrl: './create-marca.component.html',
  styleUrls: ['./create-marca.component.css']
})
export class CreateMarcaComponent implements OnInit {

  marca: Marca = {
    id: 0,
    nombre: '',
    estado: ''
  };

  marcaFormControl: any;
  isWait = false;

  constructor(
    private _marcaService: MarcaService,
    private _location: Location,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm(): void {
     this.marcaFormControl = this.fb.group({
      nombre: ["",
      Validators.compose([
        Validators.required,
        Validators.maxLength(10),
      ]),],
    });
  }

  add(): void {
    this.isWait = true;
    const newMarca: Marca = {
      id: 0,
      nombre: this.marcaFormControl.get("nombre").value,
      estado: 'Activo'
    };
    this._marcaService.createMarca(newMarca).subscribe(() => {   
      this.isWait = false;
      this.goBack() ;
    });
  }


  goBack(): void {
    this._location.back();
  }


}
