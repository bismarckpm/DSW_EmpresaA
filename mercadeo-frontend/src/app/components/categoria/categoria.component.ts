import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/interfaces/categoria';
import { CategoriaService } from 'src/services/categoria.service';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogcategoriaComponent } from '../dialog/dialogcategoria/dialogcategoria.component';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';


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

  categorias: Categoria[] = [];
  
  constructor(
    public _categoriaService: CategoriaService,
    private location: Location,
    public dialog: MatDialog,
    ) { }

    
   

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
    
  } 


  ngOnInit() {
    this.get();
  }


  goBack(): void {
    this.location.back();
  }

  get(){
    this._categoriaService.getCategorias().subscribe(data => {this.categorias = data;});
  }


  add(nombre: string, estado: string): void {
    nombre = nombre.trim();
    estado = estado.trim();
    if (!nombre) { return; }
    this._categoriaService.createCategoria({
      nombre,
      estado
    } as Categoria).subscribe(data => { this.categorias.push(data)} );
    this.get();
  }

  save(categoria: Categoria): void {
    this._categoriaService.editCategoria(categoria)
      .subscribe();
  }
  
  delete(categoria: Categoria): void {
    if(confirm("Estas seguro de eliminar "+categoria.nombre)) {
      this._categoriaService.deleteCategoria(categoria).subscribe();
    }

    this.get();
  }


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


