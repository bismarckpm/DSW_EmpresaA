import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GetTipo, Tipo } from 'src/app/interfaces/tipo';
import { TipoService } from 'src/app/services/tipo.service';
import { TipoComponent } from '../../tipo/tipo.component';

@Component({
  selector: 'app-dialogtipo',
  templateUrl: './dialogtipo.component.html',
  styleUrls: ['./dialogtipo.component.css']
})
export class DialogtipoComponent implements OnInit {


  tipo: GetTipo ={
    _id: 0,
    _nombre: '',
    _estado: '',
    _descripcion: '',
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
    estado: "A",
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
