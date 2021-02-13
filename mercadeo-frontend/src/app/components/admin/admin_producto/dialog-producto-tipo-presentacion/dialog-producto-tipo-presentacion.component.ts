import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GetPresentacion } from 'src/app/interfaces/presentacion';
import { GetProductoTipoPresentacion, ProductoTipoPresentacion } from 'src/app/interfaces/producto';
import { GetTipo } from 'src/app/interfaces/tipo';
import { AlertService } from 'src/app/services/alert.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { TipoPresentacionService } from 'src/app/services/tipo-presentacion.service';
import { TipoService } from 'src/app/services/tipo.service';
import { CreatePresentacionComponent } from '../../admin_presentacion/create-presentacion/create-presentacion.component';

@Component({
  selector: 'app-dialog-producto-tipo-presentacion',
  templateUrl: './dialog-producto-tipo-presentacion.component.html',
  styleUrls: ['./dialog-producto-tipo-presentacion.component.css']
})
export class DialogProductoTipoPresentacionComponent implements OnInit {


  // Forms
  productoFormTP: any;

  // Variables
  tipos: GetTipo[] = [];
  presentaciones: GetPresentacion[] = [];

  productoTipoPresentacion: GetProductoTipoPresentacion ={
    _id:0,
    _estado:'A',
    _tipo: {
      _id:0,
      _nombre:'',
      _estado:'',
      _descripcion:''
    },
    _presentacion: {
      _id:0,
      _titulo:'',
      _estado:'',
      _caracteristicas:''
    },
    _producto: {
      _id: 0,
      _nombre:'',
      _estado:'',
      _descripcion:'',
      _marca:{
        _id:0,
        _nombre:'',
        _estado:'',
      },
      _subcategoria:{
        _id:0,
        _nombre:'',
        _estado:'',
        _descripcion:'',
        _categoria:{
          _id:0,
          _nombre:'',
          _estado:'',
        }
      },
      _usuario: {
        id:0,
        nombreUsuario:'',
        correo:'',
        estado:'',
        idRol: 0
      }
    }

  }

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };


  constructor(
    public dialogRef: MatDialogRef<CreatePresentacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ProductoTipoPresentacion,
    public _tipoService: TipoService,
    public _presentacionService: PresentacionService,
    private _tpService: TipoPresentacionService,
    private _alertService: AlertService,

    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.get();
    this.getPresentaciones();
    this.getTipos();
    this.buildForm();
  }


  //  Form

  buildForm(): void {
    this.productoFormTP = this.fb.group({
      productoDto: this.data.id,
      estado: 'A',
      tipoDto: [this.data.tipoDto,
      Validators.compose([
        Validators.required, 
      ]),],
      presentacionDto: [this.data.presentacionDto,
      Validators.compose([
        Validators.required, 
      ]),]
    });
 }


  // Get
  get(): void {
    this._tpService.getPTP(this.data.id).subscribe(data => {

      this.productoTipoPresentacion = data.objeto;
    });
  }

  getTipos(): void {
    this._tipoService.getTipos().subscribe(data => {

      this.tipos = data.objeto;
      this.tipos = this.tipos.filter(item => item._estado === 'A');});
  }
  
  getPresentaciones(): void {
   this._presentacionService.getPresentaciones().subscribe(data => {

     this.presentaciones = data.objeto; 
     this.presentaciones = this.presentaciones.filter(item => item._estado === 'A');});
  }

  // Save

  
  save(): void {

    const newCa: ProductoTipoPresentacion = {
      id: this.data.id,
      estado: 'A',
      productoDto: this.data.productoDto,
      tipoDto: this.productoFormTP.get("tipoDto").value, 
      presentacionDto: this.productoFormTP.get("presentacionDto").value
    };

    console.log(newCa)
    this._tpService.editProductoTipoPresentacion(newCa)
      .subscribe( response => {
        this._alertService.success(response.mensaje+ '    Estado: '+ response.estado, this.options)
      } );
      this.dialogRef.close();
  }



  onNoClick(): void {
    this.dialogRef.close();
  }
}
