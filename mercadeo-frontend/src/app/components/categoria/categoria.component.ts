import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogcategoriaComponent } from '../dialog/dialogcategoria/dialogcategoria.component';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/interfaces/user';


@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  
  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }

  categorias: GetCategoria[] = [];
  categoria: Categoria[] = [];
  
  
  // Usuarios
  public identity: any;
  public user: User;


  constructor(
    public _categoriaService: CategoriaService,
    private location: Location,
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

    
   

  openDialog(id: number): void {
    console.log(id);
    const dialogRef = this.dialog.open(DialogcategoriaComponent, {
      width: '20rem',
      data: {id: id}
    });


    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.get();
    });
    this.get();

  } 


  ngOnInit() {
    this.get();
  }


  goBack(): void {
    this.location.back();
  }

  get(){
    this._categoriaService.getCategorias().subscribe(data => {this.categorias = data})
  }


  
  delete(categoria: GetCategoria): void {
    const newCa: Categoria = {
      id: categoria._id,
      nombre: categoria._nombre,
      estado: "I",
    };

    if(confirm("Estas seguro de eliminar "+categoria._nombre)) {
      this._categoriaService.editCategoria(newCa).subscribe(() =>  {this.get()});
    }
  } 


  /* Old Delete
  delete(categoria: Categoria): void {
    if(confirm("Estas seguro de eliminar "+categoria.nombre)) {
      this._categoriaService.deleteCategoria(categoria).subscribe();
    }

    this.get();
  } */


  /* testing

 
  categorias$: Observable<Categoria[]>;
  private searchTerms = new Subject<string>();


  search(term: string): void {
    this.searchTerms.next(term);
  }

  test(): void {
    this.categorias$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this._categoriaService.searchCategorias(term)),
    );
  }
 */
}


