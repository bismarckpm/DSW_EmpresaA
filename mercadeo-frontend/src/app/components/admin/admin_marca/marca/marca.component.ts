import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GetMarca, Marca } from 'src/app/interfaces/marca';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { MarcaService } from 'src/app/services/marca.service';
import { DialogmarcaComponent } from '../dialogmarca/dialogmarca.component';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {


  marcas : GetMarca[] = [];

  
  // Usuarios
  public identity: any;
  public user: User;
  
  constructor(
    private _marcaService: MarcaService,
    public dialog: MatDialog,
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

  // Cambia tipo de vista Tabla / Lista
  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }

  //Dialogo para editar marca
  openDialog(id: number): void {
    console.log(id);
    const dialogRef = this.dialog.open(DialogmarcaComponent, {
      width: '20rem',
      data: {id: id}
    });


    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.get();
    });
    
  } 


  // CRUD 
  get(): void {
    this._marcaService.getMarcas().subscribe(data => {
      this.marcas = data;
      this.marcas = this.marcas.sort((a, b) => a._estado.localeCompare(b._estado));  
    });
  }



  delete(marca: GetMarca): void {
    const newMarca: Marca = {
      id: marca._id,
      nombre: marca._nombre,
      estado: "I",
    };

    if(confirm("Estas seguro de eliminar "+marca._nombre)) {
      this._marcaService.editMarca(newMarca).subscribe(() =>  {this.get()});
    }
  }



}
