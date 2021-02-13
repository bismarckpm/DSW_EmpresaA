import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { GetMarca, Marca } from 'src/app/interfaces/marca';
import { AlertService } from 'src/app/services/alert.service';
import { MarcaService } from 'src/app/services/marca.service';
import { MarcaComponent } from '../marca/marca.component';


@Component({
  selector: 'app-dialogmarca',
  templateUrl: './dialogmarca.component.html',
  styleUrls: ['./dialogmarca.component.css']
})
export class DialogmarcaComponent implements OnInit {
  
  marca: GetMarca = {
    _id: 0,
    _nombre: '',
    _estado: ''
  };

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };
  
  constructor(
    public dialogRef: MatDialogRef<MarcaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Marca,
    public _marcaService: MarcaService,
    private alertService: AlertService,

  ) { }
  
  // Get Single Categoria
  get(){
    const id = this.data.id;
    console.log(id)
    this._marcaService.getMarca(id).subscribe(data => {
      // this.marca = data.marca;
      this.marca = data;
    }, error => {

    }
  );}

  ngOnInit(): void {
    this.get();
  }

  save(marca: GetMarca): void {
    console.log(marca)
    const newCa: Marca = {
      id: marca._id,
      nombre: marca._nombre,
      estado: marca._estado,
    };

    this._marcaService.editMarca(newCa)
      .subscribe((data)=>{
        this.alertService.success('data', this.options)
      }, error => {
        this.alertService.error('data', this.options);
      });
      this.dialogRef.close();
  }


  
  onNoClick(): void {
    this.dialogRef.close();
  }

}
