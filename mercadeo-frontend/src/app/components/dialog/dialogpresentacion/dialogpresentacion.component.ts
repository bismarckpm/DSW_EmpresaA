import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Presentacion } from 'src/app/interfaces/presentacion';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { PresentacionComponent } from '../../presentacion/presentacion.component';

@Component({
  selector: 'app-dialogpresentacion',
  templateUrl: './dialogpresentacion.component.html',
  styleUrls: ['./dialogpresentacion.component.css']
})
export class DialogpresentacionComponent implements OnInit {

  presentacion: Presentacion ={
    id: 0,
    titulo: '',
    estado: '',
    caracteristicas: '',
  }; 

  presentacionForm: any;

  constructor(
    public dialogRef: MatDialogRef<PresentacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Presentacion,
    public _presentacionService: PresentacionService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.get();
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

 get(){
  const id = this.data.id;
  console.log(id)
  this._presentacionService.getPresentacion(id).subscribe(data => {this.presentacion = data;});
}


save(): void {

  const NewP: Presentacion = {
    id: this.data.id,
    titulo: this.presentacionForm.get("titulo").value,
    estado: "Activo",
    caracteristicas: this.presentacionForm.get("caracteristicas").value
  };

  console.log(NewP)
  this._presentacionService.editPresentacion(NewP)
    .subscribe();
    this.dialogRef.close();

 }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
