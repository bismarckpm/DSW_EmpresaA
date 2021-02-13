import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { GetSubcategoria, Subcategoria } from 'src/app/interfaces/subcategoria';
import { User } from 'src/app/interfaces/user';
import { AlertService } from 'src/app/services/alert.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { LoginService } from 'src/app/services/login.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';

import { DialogsubcategoriaComponent } from '../dialogsubcategoria/dialogsubcategoria.component';


@Component({
  selector: 'app-subcategoria',
  templateUrl: './subcategoria.component.html',
  styleUrls: ['./subcategoria.component.css']
})
export class SubcategoriaComponent implements OnInit {

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };
  
  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }
  
  subcategorias: GetSubcategoria[] = [];

  // Usuarios
  public identity: any;
  public user: User;

  constructor(
    private _subcategoriaService: SubcategoriaService, 
    public dialog: MatDialog,
    private _loginService: LoginService,
    // Alertas
    private alertService: AlertService,
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
 
    //Dialogo
    //Dialogo para editar marca
    openDialog(subcategoria: GetSubcategoria): void {
      console.log(subcategoria._id);
      const dialogRef = this.dialog.open(DialogsubcategoriaComponent, {
        width: '30rem',
        data: {id: subcategoria._id, estado: subcategoria._estado, categoriaDto: subcategoria._categoria._id} 
      });
  
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.get();
      });
      this.get();
    } 

  // CRUD 
  get(): void {
    this._subcategoriaService.getSubcategorias().subscribe(data => {
      this.subcategorias = data.objeto;
      this.subcategorias = this.subcategorias.sort((a, b) => a._estado.localeCompare(b._estado));  

    }, error => {
      this.alertService.error(error, this.options)
    }

  )}  


  delete(subcategoria: GetSubcategoria): void {

    const newSubcategoria: Subcategoria = {
      id: subcategoria._id,
      nombre: subcategoria._nombre,
      estado: "I",
      descripcion: subcategoria._descripcion,
      categoriaDto: subcategoria._categoria._id
    
    };

  
    if(confirm("Estas seguro de eliminar "+subcategoria._nombre)) {
    this._subcategoriaService.editSubcategoria(newSubcategoria).subscribe((response) => {
    this.get()
    this.alertService.error(response.mensaje+' Estatus:'+response.estado, this.options)

    });
    }


  }



  // TESTING


}
