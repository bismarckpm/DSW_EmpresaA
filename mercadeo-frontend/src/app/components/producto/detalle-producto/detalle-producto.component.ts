import { Component, OnInit } from '@angular/core';
import { Routes, RouterModule, ActivatedRoute } from '@angular/router';
import { GetProducto, Producto } from 'src/app/interfaces/producto';
import { ProductoService } from 'src/app/services/producto.service';
import { PresentacionService } from 'src/app/services/presentacion.service';
import { TipoService } from 'src/app/services/tipo.service';
import { MarcaService } from 'src/app/services/marca.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { GetSubcategoria, Subcategoria } from 'src/app/interfaces/subcategoria';
import { GetMarca, Marca } from 'src/app/interfaces/marca';
import { GetTipo, Tipo } from 'src/app/interfaces/tipo';
import { GetPresentacion, Presentacion } from 'src/app/interfaces/presentacion';
import { GetProductoTipoPresentacion, ProductoTipoPresentacion } from 'src/app/interfaces/producto';
import { FormBuilder, Validators } from '@angular/forms';
import { TipoPresentacionService } from 'src/app/services/tipo-presentacion.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { DialogProductoTipoPresentacionComponent } from '../../dialog/dialog-producto-tipo-presentacion/dialog-producto-tipo-presentacion.component';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.css']
})
export class DetalleProductoComponent implements OnInit {

  // ID
  id = +this.route.snapshot.paramMap.get('id')!;

  // Panel u otros
  panelOpenState = false;
  isWait = false;

  // Form
  productoForm: any;
  productoFormTP: any;

  // Variables Producto
  producto: GetProducto =
  {
    _id:0,
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
    }
  }
  subcategorias: GetSubcategoria[] = [];
  marcas: GetMarca[] = [];
  tipos: GetTipo[] = [];
  presentaciones: GetPresentacion[] = [];
  productoTipoPresentacion: GetProductoTipoPresentacion[] = [];


  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private _productoService: ProductoService,
    private _subcategoriaService: SubcategoriaService,
    private _marcaService: MarcaService,
    private _tipoService: TipoService,
    private _presentacionService: PresentacionService,
    private _tpService: TipoPresentacionService,
    private _snackBar: MatSnackBar,
    public dialog: MatDialog
  ) { }


  ngOnInit(): void {
    this.getProducto();
    this.getSubcategorias();
    this.getMarcas();
    this.getTipos();
    this.getPresentaciones();
    this.getTipoPresentacion();
    this.buildForm();
  }


  // Metodos Productos
  getProducto(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this._productoService.getProducto(id).subscribe(data => {this.producto = data;});
  }

  saveProducto(): void {
    this.isWait = true;
    const newProducto: Producto = {
      id: this.id,
      nombre: this.productoForm.get("nombre").value,
      estado: "A",
      descripcion: this.productoForm.get("descripcion").value,
      subcategoriaDto: this.productoForm.get("subcategoriaDto").value,
      marcaDto: this.productoForm.get("marcaDto").value
    };
  
    console.log(newProducto)
    this._productoService.editProducto(newProducto).subscribe(() => {  
    this.openSnackBar(); 
    this.isWait = false;
    this.getProducto();
    this.getTipoPresentacion(); });
   }


  // Metodos Tipo y Presesntacion  ADD

  addTipoPresentacion(): void {
  this.isWait = true;
  const newTP: ProductoTipoPresentacion = {
    id:0,
    estado: 'A',
    productoDto: this.id,
    tipoDto: this.productoFormTP.get("tipoDto").value,
    presentacionDto: this.productoFormTP.get("presentacionDto").value,
  }

  console.log(newTP);
  this._tpService.createProductoTipoPresentacion(newTP).subscribe(() => {   
    this.isWait = false;
    this.openSnackBar();
    this.getProducto();
    this.getTipoPresentacion();
  });

  this.getTipoPresentacion();
}

deleteTipoPresentacion(tp: GetProductoTipoPresentacion): void {
  this.isWait = true;
  const newCa: ProductoTipoPresentacion = {
    id: tp._id,
    estado: 'I',
    productoDto: this.id,
    tipoDto: tp._tipo._id,
    presentacionDto: tp._presentacion._id
  };

  if(confirm("Estas seguro de eliminar "+tp._producto._nombre)) {
    this._tpService.editProductoTipoPresentacion(newCa).subscribe(() =>  {
      this.isWait = false;
      this.getProducto();
      this.getTipoPresentacion();
    });
  }
} 


  // Dialogo

  openDialog(id: number, productoDto: number): void {
    console.log(id);
    const dialogRef = this.dialog.open(DialogProductoTipoPresentacionComponent, {
      width: '20rem',
      data: {id: id, productoDto: productoDto }
    });


    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getProducto();
      this.getTipoPresentacion();
    });
    
  } 


  
  // Form 
  buildForm(): void {


    // Para editar producto actual
    this.productoForm = this.fb.group({
      subcategoriaDto: ['',
      Validators.compose([
        Validators.required, 
      ]),],
      marcaDto: ['',
      Validators.compose([
        Validators.required]),
      ],
     nombre: ["",
     Validators.compose([
       Validators.required,
     ]),],
     descripcion: ["",
     Validators.compose([
       Validators.required])]
  });

  // Tipo y Presentaciones
  this.productoFormTP = this.fb.group({
    productoDto: this.id,
    estado: 'A',
    tipoDto: ["",
    Validators.compose([
      Validators.required, 
    ]),],
    presentacionDto: ["",
    Validators.compose([
      Validators.required, 
    ]),]
  });
 }

  // Get Tipo, Presentacion, Subcategoria, Marca

 getSubcategorias(): void {
  this._subcategoriaService.getSubcategorias().subscribe(data => {this.subcategorias = data});
}

getMarcas(): void {
  this._marcaService.getMarcas().subscribe(data => {this.marcas = data});
}

getTipos(): void {
  this._tipoService.getTipos().subscribe(data => {this.tipos = data});
}

getPresentaciones(): void {
 this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data});
}

getTipoPresentacion(): void {
  this._tpService.getProductoTipoPresentacion().subscribe(data => {this.productoTipoPresentacion = data});
 }
 

// Snackbar

openSnackBar() {
  this._snackBar.open('Guardado!', 'Ok', {
    duration: 2000,
  });
}



}
