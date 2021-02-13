import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Categoria, GetCategoria } from 'src/app/interfaces/categoria';
import { AlertService } from 'src/app/services/alert.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { CategoriaComponent } from '../categoria/categoria.component';

@Component({
  selector: 'app-dialogcategoria',
  templateUrl: './dialogcategoria.component.html',
  styleUrls: ['./dialogcategoria.component.css']
})
export class DialogcategoriaComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<CategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Categoria,
    public _categoriaService: CategoriaService,

    private alertService: AlertService,

  ) { }


  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  categoria: any;

  ngOnInit(): void {
    this.get();
  }


  save(categoria: any): void {
    console.log('Saving new Categoria' ,categoria)
    const newCa: any = {
      id: categoria.objeto._id,
      nombre: categoria.objeto._nombre,
      estado: categoria.objeto._estado
    };


    this._categoriaService.editCategoria(newCa)
      .subscribe((data)=> {
        const msg = data.mensaje + ' Estatus:' + data.estado
        this.alertService.success(msg, this.options)
      }, error => {
        this.alertService.error(error, this.options)
    });


      this.dialogRef.close();
  }

  // Get Single Categoria
  get(){
    const id = this.data.id;
    this._categoriaService.getCategoria(id).subscribe(data => {
      this.categoria = data;
      console.log(this.categoria)
    });
  }
  

  onNoClick(): void {
    this.dialogRef.close();
  }

}
