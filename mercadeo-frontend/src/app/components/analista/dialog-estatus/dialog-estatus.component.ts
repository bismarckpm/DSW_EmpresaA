import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SetEstudio } from 'src/app/interfaces/estudio';
import { AlertService } from 'src/app/services/alert.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { ConsultarEstudioAnalistaComponent } from '../analista_estudio_asignado/consultar-estudio-analista/consultar-estudio-analista.component';

@Component({
  selector: 'app-dialog-estatus',
  templateUrl: './dialog-estatus.component.html',
  styleUrls: ['./dialog-estatus.component.css']
})
export class DialogEstatusComponent implements OnInit {

  form: any;
  currentDate = new Date();
  isEmpty = false;

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    public dialogRef: MatDialogRef<ConsultarEstudioAnalistaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private estudio: EstudioService,
    private fb: FormBuilder,
    private alertService: AlertService,

  ) { }

  ngOnInit(): void {
    console.log('data', this.data)
    this.isEmptyForm();
    this.buildForm();
  }



  buildForm(): void {
    this.form = this.fb.group({
     estatus: [this.data.estatus,
     Validators.compose([
       Validators.required,
     ]),],
     conclusion: ['',
      Validators.compose([
        Validators.required,
      ]),]
   });
 }

//Aca solo agregue el atributo conclusion
 actualizarEstudio() {

  const isEstatus = this.form.get("estatus").value;
  const isConclusion = this.form.get("conclusion").value; 


  if (isEstatus == 'Finalizado') {
    const estudioE: SetEstudio = {
      nombre: this.data.nombre,
      fechaInicio: this.data.fechaInicio,
      fechaFin: this.currentDate,
      estatus: isEstatus,
      estado: this.data.estado,
      conclusion: isConclusion, /// aca
      solicitudEstudioDto: this.data.solicitudEstudio,
      usuarioDto: this.data.usuario
    };

    console.log('eh', estudioE)
    if(confirm("¿Estás seguro de realizar el cambio")){
    this.estudio.setEstudio2(this.data.id, estudioE).subscribe((data) => {
      console.log(data)
      this.alertService.success(data.mensaje + '     Estado: '+ data.estado, this.options);
      this.dialogRef.close();
    });
    }
  }else {
    const estudioE: SetEstudio = {
      nombre: this.data.nombre,
      fechaInicio: this.data.fechaInicio,
      fechaFin: this.data.fechaFin,
      estatus: isEstatus,
      estado: this.data.estado,
      conclusion: '', /// y aca
      solicitudEstudioDto: this.data.solicitudEstudio,
      usuarioDto: this.data.usuario
    };

    console.log('eh', estudioE)
    if(confirm("¿Estás seguro de realizar el cambio")){

    this.estudio.setEstudio2(this.data.id, estudioE).subscribe((data) => {
      console.log(data);
      this.alertService.success(data.mensaje + '     Estado: '+ data.estado, this.options);
      this.dialogRef.close();
    });
  }
  }

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isEmptyForm(): void {
    this.estudio.getValidarPoblacionEstudio(this.data.id).subscribe( (data) => {
      this.isEmpty = data.objeto;
      console.log( this.isEmpty)
    }
    );
  }
}
