import { Component, Inject, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DialogoGestionarPoblacionComponent } from 'src/app/components/analista/dialogo-gestionar-poblacion/dialogo-gestionar-poblacion.component';
import { PoblacionService } from 'src/app/services/poblacion.service';

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


  constructor(
    private _snackBar: MatSnackBar,
    // Dialogs
    public dialogRef: MatDialogRef<DialogoPoblacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog,
    // Services
    private poblacionService: PoblacionService,
  ) { }

  ngOnInit(): void {
    console.log(this.data);
    this.getPoblacion();
    this.getPoblacionNoRelacionada();
  }



  // Obtener poblacion actual
  poblaciones: any[] = [];
  getPoblacion() {
    this.poblacionService.getPoblacion(this.data.idEstudio).subscribe((response)=>{
      this.poblaciones = response;
      this.dataSource = new MatTableDataSource(this.poblaciones);

      console.log('Relacion', this.poblaciones)
    })
  }

  // Obtener poblacion no asignada
  poblacionesNA: any[] = [];
  getPoblacionNoRelacionada() {
    this.poblacionService.getPoblacionNoRelacionada(this.data.idEstudio).subscribe((response)=>{
      this.poblacionesNA = response;
      this.dataSource2 = new MatTableDataSource(this.poblacionesNA);


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
      this.openSnackBar('Agregado ,' +response)
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
      id: 1,
      estado: 'I',
      usuario: poblacion._id,
      estudio: this.data.estudia._id,
    }
    console.log(Persona)

    this.poblacionService.editPoblacion(Persona).subscribe((response)=>{
      this.getPoblacion();
      this.getPoblacionNoRelacionada();
      this.openSnackBar('Removido ,'+ response)
      this.isWait = false;

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