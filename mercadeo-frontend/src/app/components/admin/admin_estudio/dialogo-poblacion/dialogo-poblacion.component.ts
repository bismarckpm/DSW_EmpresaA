import { Component, Inject, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DialogoGestionarPoblacionComponent } from 'src/app/components/analista/dialogo-gestionar-poblacion/dialogo-gestionar-poblacion.component';
import { PoblacionService } from 'src/app/services/poblacion.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';

@Component({
  selector: 'app-dialogo-poblacion',
  templateUrl: './dialogo-poblacion.component.html',
  styleUrls: ['./dialogo-poblacion.component.css']
})
export class DialogoPoblacionComponent implements OnInit {

  isWait = false;
  // Tabla
  displayedColumns: string[] = ['_id','_nombreUsuario','_correo','_datoUsuario._fechaNacimiento','_datoUsuario._sexo','_datoUsuario._disponibilidadEnLinea','opcion'];
  dataSource!: MatTableDataSource<any>;

  displayedColumns2: string[] = ['id','usuario','correo','edad','genero','disponibilidad','opcion'];
  dataSource2!: MatTableDataSource<any>;

  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;

  estudia: any;

  constructor(
    private _snackBar: MatSnackBar,
    // Dialogs
    public dialogRef: MatDialogRef<DialogoPoblacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog,
    // Services
    private poblacionService: PoblacionService,
    private _pe: PreguntaEncuestaServiceService,
  ) { }

  ngOnInit(): void {
    console.log(this.data);
    this.estudia = this.data.estudia;
    this.getPoblacion();
    this.getPoblacionNoRelacionada();
  }


  // ngAfterViewInit(): void {
  //   this.dataSource2.sort = this.sort;
  // }

  // Obtener poblacion actual
  poblaciones: any[] = [];
  getPoblacion() {
    this.poblacionService.getPoblacion(this.data.idEstudio).subscribe((response)=>{
      this.poblaciones = response.objeto;
      
      this.dataSource = new MatTableDataSource(this.poblaciones);

      console.log('Relacion', this.poblaciones)
    })
  }

  // Obtener poblacion no asignada
  poblacionesNA: any[] = [];
  getPoblacionNoRelacionada() {
    this.poblacionService.getPoblacionNoRelacionada(this.data.idEstudio).subscribe((response)=>{
      this.poblacionesNA = response.objeto;

      this.dataSource2 = new MatTableDataSource(this.poblacionesNA);
      // this.dataSource2.sort = this.sort;


      console.log('No Relacion', this.poblacionesNA)
    })
  }


  addPerson(poblacion: any) {
    this.isWait = true;
    const newPersona: any = {
      id: 0,
      estado: 'A',
      usuario: poblacion._id,
      estudio: this.data.estudia._id,
    }
    console.log(newPersona)

    this.poblacionService.addPoblacionNueva(newPersona).subscribe((response)=>{
      this.getPoblacion();
      this.getPoblacionNoRelacionada();
      this.openSnackBar('Agregado usuario ' + poblacion._nombreUsuario+' al estudio')
      this.isWait = false;
    }, (error) => {
      console.log(error);
      this.openSnackBar('Error Agregando ,'+ error)
      this.isWait = false;
    }
   
  )}
  

  removePerson(poblacion: any) {
    this.isWait = true;
    const Persona: any = {
      id: poblacion._id,
      estado: 'I',
      usuario: poblacion._usuario._id,
      estudio: this.data.estudia._id,
    }
    console.log(Persona)

    this._pe.validarPreguntas(this.data.estudia._id ,poblacion._usuario._id).subscribe( (data) => {
      let isValid = data
      console.log('Participo?' ,isValid)

      if (isValid.objeto == 'En Espera') {
        this.poblacionService.editPoblacion(Persona).subscribe((response)=>{
          this.getPoblacion();
          this.getPoblacionNoRelacionada();
          this.openSnackBar('Removido usuario ' + poblacion._usuario._nombreUsuario+' al estudio')
          this.isWait = false;
        })
      } else {
        this.openSnackBar('Error: El usuario ya empezo la encuesta')
        this.isWait = false;
      }

    })




  }
  
  


    //Dialogo para editar poblacion
    openDialog(): void {

      console.log( 'usuario',  this.data.estudia)

      const dialogRef = this.dialog.open(DialogoGestionarPoblacionComponent, {
        width: '30rem',
        data: {data: this.data.estudia} 
      });


      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
        console.log('The dialog was closed');
      });
    }

  // Snackbar
  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'Ok', {
      duration: 2000,
    });
}
}