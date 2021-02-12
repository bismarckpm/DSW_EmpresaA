import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { GetPresentacion, Presentacion } from 'src/app/interfaces/presentacion';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { DialogpresentacionComponent } from '../dialogpresentacion/dialogpresentacion.component';

@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css']
})
export class PresentacionComponent implements OnInit {

  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }

  presentaciones: GetPresentacion[] = [];

  
  // Usuarios
  public identity: any;
  public user: User;
  
  constructor(
    private _presentacionService: PresentacionService,
    private dialog: MatDialog,
    private _loginService: LoginService
  ) {
    this.identity = JSON.parse(_loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
   }

  ngOnInit(): void {
    this.get();
  }
  
    //Dialogo para editar marca
    openDialog(id: number): void {
      console.log(id);
      const dialogRef = this.dialog.open(DialogpresentacionComponent, {
        width: '20rem',
        data: {id: id}
      });
  
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.get();
      });
      
    } 

  //CRUD 
  get(): void {
    this._presentacionService.getPresentaciones().subscribe(data => {
      this.presentaciones = data;
      this.presentaciones = this.presentaciones.sort((a, b) => a._estado.localeCompare(b._estado));  
    });
  }

  delete(presentacion: GetPresentacion): void {
    const newP: Presentacion = {
      id: presentacion._id,
      titulo: presentacion._titulo,
      estado: "I",
      caracteristicas: presentacion._caracteristicas
    };

    if(confirm("Estas seguro de eliminar "+presentacion._titulo)) {
    this._presentacionService.editPresentacion(newP).subscribe(() => this.get()) ;
    }
  }

}
