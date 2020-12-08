import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Tipo } from 'src/interfaces/tipo';
import { TipoService } from 'src/services/tipo.service';
import { TipoComponent } from '../../tipo/tipo.component';

@Component({
  selector: 'app-dialogtipo',
  templateUrl: './dialogtipo.component.html',
  styleUrls: ['./dialogtipo.component.css']
})
export class DialogtipoComponent implements OnInit {


  tipo: Tipo ={
    id: 0,
    nombre: '',
    estado: '',
    descripcion: '',
  }; 

  tipoForm: any;

  constructor(
    public dialogRef: MatDialogRef<TipoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Tipo,
    public _tipoService: TipoService,
    private fb: FormBuilder
  ) { }
  

  ngOnInit(): void {
    this.get();
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

 get(){
  const id = this.data.id;
  console.log(id)
  this._tipoService.getTipo(id).subscribe(data => {this.tipo = data;});
}

 save(): void {

  const newTipo: Tipo = {
    id: this.data.id,
    nombre: this.tipoForm.get("nombre").value,
    estado: "Activo",
    descripcion: this.tipoForm.get("descripcion").value
  };

  console.log(newTipo)
  this._tipoService.editTipo(newTipo)
    .subscribe();
    this.dialogRef.close();

 }



  onNoClick(): void {
    this.dialogRef.close();
  }
}
