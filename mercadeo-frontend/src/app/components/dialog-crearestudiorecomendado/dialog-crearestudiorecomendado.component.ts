import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio } from 'src/app/interfaces/estudio';
import { Usuario } from 'src/app/interfaces/usuario';
import { EstudioService } from 'src/app/services/estudio.service';
import { UsuarioServicioService } from 'src/app/services/usuario-servicio.service';
import { DialogPreviewestudioComponent } from '../dialog-previewestudio/dialog-previewestudio.component';

@Component({
  selector: 'app-dialog-crearestudiorecomendado',
  templateUrl: './dialog-crearestudiorecomendado.component.html',
  styleUrls: ['./dialog-crearestudiorecomendado.component.css']
})
export class DialogCrearestudiorecomendadoComponent implements OnInit {


  //Form crear estudio recomendado
  estudioRecomendadoForm: any; 


  analistas:any;
  fechaActual: any;

  constructor(
    public dialogRef: MatDialogRef<DialogPreviewestudioComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private fb: FormBuilder,
    private _user: UsuarioServicioService,
    private estudiosR: EstudioService
  ) { }

  ngOnInit(): void {

    console.log(this.data);
    this.buildForm();

    this._user.getUsuariosAnalista(3).subscribe(
      (analista: Usuario[]) => {
        this.analistas = analista;
        console.log(this.analistas);
      }
    );
    this.fechaActual = new Date;
  }


  buildForm(): void {
    this.estudioRecomendadoForm = this.fb.group({
      nombreEstudio: ["",
      Validators.compose([
        Validators.required, 
      ]),],
      fechaInicio: ["",
      Validators.compose([
        Validators.required]),
      ],
      analistaAsignado: ["",
        Validators.compose([
          Validators.required]),
        ]
    });
 }

  crearEstudioRecomendado(){

    const estudio: Estudio = {
      id: this.data.idEstudio,
      nombre: this.estudioRecomendadoForm.get("nombreEstudio").value,
      fechaInicio: this.estudioRecomendadoForm.get("fechaInicio").value,
      estatus: 'En Espera',
      estado: 'A',
      solicitudEstudioDto: Number(this.data.idSolicitud),
      usuarioDto: this.estudioRecomendadoForm.get("analistaAsignado").value,
    };

     this.estudiosR.createEstudioRecomendacion(this.data.idSolicitud, estudio);
    
    console.log(estudio);
  }

  closeDialog(){
    this.dialogRef.close();
  }


  //this.navegacion.navigate(['asignarpreguntasaestudio', this.idEstudio]);
  


}
