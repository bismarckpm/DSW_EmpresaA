import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio } from 'src/app/interfaces/estudio';
import { EstudioService } from 'src/app/services/estudio.service';


@Component({
  selector: 'app-dialogconsultarestudio',
  templateUrl: './dialogconsultarestudio.component.html',
  styleUrls: ['./dialogconsultarestudio.component.css']
})
export class DialogconsultarestudioComponent implements OnInit {

  id: number = 0;
  nombreE: string = '';
  fechaI: string = '';
  fechaF: string = '';
  status: string = '';
  estado: string = '';
  fechaFn = new Date();
  fechaIn = new Date();
  form: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: Estudio,
  private estudio: EstudioService, private fb: FormBuilder, 
  public datepipe: DatePipe ) { }

  ngOnInit(): void {

  console.log(this.data)

   console.log("Data"+ this.data.fechaInicio + ' ' + this.data.fechaFin )
   this.id = this.data.id!;
   this.nombreE = this.data.nombre;
   this.fechaIn = this.data.fechaInicio;
   this.fechaFn = this.data.fechaFin!;
   this.status = this.data.estatus;
   this.estado = this.data.estado;
   /* this.fechaI = this.datepipe.transform(this.fechaIn, 'yyyy-MM-dd')!;
   this.fechaF = this.datepipe.transform(this.fechaFn, 'yyyy-MM-dd')!; */

   console.log(this.fechaIn);
   console.log(this.fechaFn);
   console.log(this.fechaI);
   console.log(this.fechaF);
   console.log(this.status);
   console.log(this.id);
   this.buildForm();
  }


  buildForm(): void {
    this.form = this.fb.group({
      estatus: ["",
      Validators.compose([
        Validators.required])
      ]
    })
}

  actualizarEstudio(estatus : any) {

    let estudioE: Estudio = {
      nombre:  this.data.nombre,
      fechaInicio: this.data.fechaInicio,
      fechaFin: this.data.fechaFin,
      estatus:  'FINALIZADO',
      estado: this.data.estado,
      solicitudEstudioDto: this.data.solicitudEstudioDto,
      usuarioDto: this.data.usuarioDto
    };

    this.estudio.setEstudio(this.id, estudioE);
  }


}
