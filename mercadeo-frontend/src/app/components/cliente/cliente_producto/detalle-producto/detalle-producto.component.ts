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
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { DialogProductoTipoPresentacionComponent } from 'src/app/components/admin/admin_producto/dialog-producto-tipo-presentacion/dialog-producto-tipo-presentacion.component';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.css']
})
export class DetalleProductoComponent implements OnInit {

  // ID
  id = +this.route.snapshot.paramMap.get('id')!;
  // ID Usuarios
  public identity: any;
  public user: User = {
    id:0,
    nombreUsuario:'',
    correo:'',
    estado:'',
    idRol:0
  };

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
    },
    _usuario: {
      id:0,
      nombreUsuario:'',
      correo:'',
      estado:'',
      idRol: 0
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
    public dialog: MatDialog,
    private _loginService: LoginService
  ) { 

  }


  ngOnInit(): void {
    this.getProducto();
    this.getSubcategorias();
    this.getMarcas();
    this.getTipos();
    this.getPresentaciones();
    this.getTipoPresentacion();
    this.buildForm();
    this.userLogged();
  }


  // User Data

  userLogged(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
  }

  // Metodos Productos
  getProducto(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this._productoService.getProducto(id).subscribe(data => {this.producto = data.objeto;});
  }

  saveProducto(): void {
    this.isWait = true;
    const newProducto: Producto = {
      id: this.id,
      nombre: this.productoForm.get("nombre").value,
      estado: "A",
      descripcion: this.productoForm.get("descripcion").value,
      subcategoriaDto: this.productoForm.get("subcategoriaDto").value,
      marcaDto: this.productoForm.get("marcaDto").value,
      usuarioDto: this.identity.id
    };

    
  
    console.log(newProducto)
    this._productoService.editProducto(newProducto).subscribe((response) => {  
      this.openSnackBar(response.mensaje + '  Estado: '+ response.estado);
      this.isWait = false;
    this.getProducto();
    this.getTipoPresentacion();
  });

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

  let existe: boolean = false; 

  this.productoTipoPresentacion.forEach(element => {

    // Comprueba cada tipo y presentacion asociado al id del producto
    // con lo que se va a insertar, en caso de que se cumplan las condiciones
    // devuelve un true, y esto evita que se cree uno repetido.
    const producto: any = element._producto._id
    const productoNew: any =  this.id
    const tipo: any = element._tipo._id
    const tipoNew: any =  newTP.tipoDto
    const presentacion: any = element._presentacion._id
    const presentacionNew: any =  newTP.presentacionDto
    const estatus: any =  element._estado
 
    const exist = (producto === productoNew) &&
    (tipo === tipoNew) && (presentacion === presentacionNew) && estatus === 'A';

    if (exist == true) {
      existe = true
    }
    
  });
  
  console.log(newTP);

  if (existe) { 
    this.isWait = false;
    this.openSnackBar('Ya existe!');

  }else {
  this._tpService.createProductoTipoPresentacion(newTP).subscribe((response) => {   
    this.isWait = false;
    this.openSnackBar(response.mensaje + '  Estado: '+ response.estado);
    this.getProducto();
    this.getTipoPresentacion();
  });
}

}

deleteTipoPresentacion(tp: GetProductoTipoPresentacion): void {
  const newCa: ProductoTipoPresentacion = {
    id: tp._id,
    estado: 'I',
    productoDto: this.id,
    tipoDto: tp._tipo._id,
    presentacionDto: tp._presentacion._id
  };

  if(confirm("Estas seguro de eliminar "+tp._producto._nombre)) {
    this.isWait = true;
    this._tpService.editProductoTipoPresentacion(newCa).subscribe((response) =>  {
      this.isWait = false;
      this.openSnackBar('Eliminado!  Estado: '+ response.estado);
      this.getProducto();
      this.getTipoPresentacion();
    });
  }
} 


  // Dialogo

  openDialog(id: number, productoDto: number,  tipoDto: number,  presentacionDto: number): void {
    console.log(id);
    const dialogRef = this.dialog.open(DialogProductoTipoPresentacionComponent, {
      width: '20rem',
      data: {id: id, productoDto: productoDto, tipoDto:tipoDto,  presentacionDto:presentacionDto}
    });


    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getTipoPresentacion();
      this.getProducto();
    });

  } 


  
  // Form 
  buildForm(): void {


    // Para editar producto actual
    this.productoForm = this.fb.group({
      subcategoriaDto: ["",
      Validators.compose([
        Validators.required, 
      ]),],
      marcaDto: ["",
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
  this._subcategoriaService.getSubcategorias().subscribe(data => {this.subcategorias = data.objeto ;  this.subcategorias = this.subcategorias.filter(item => item._estado === 'A');} 
  );
}

getMarcas(): void {
  this._marcaService.getMarcas().subscribe(data => {this.marcas = data.objeto ; this.marcas = this.marcas.filter(item => item._estado === 'A');
});
}

getTipos(): void {
  this._tipoService.getTipos().subscribe(data => {this.tipos = data.objeto ; this.tipos = this.tipos.filter(item => item._estado === 'A')});
}

getPresentaciones(): void {
 this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data.objeto ; this.presentaciones = this.presentaciones.filter(item => item._estado === 'A')});
}

getTipoPresentacion(): void {
  this._tpService.getProductoTipoPresentacion().subscribe(data => {
    this.productoTipoPresentacion = data.objeto ;
    this.productoTipoPresentacion = this.productoTipoPresentacion.sort((a, b) => a._estado.localeCompare(b._estado));  
  });
 }
 

// Snackbar

openSnackBar(msg: string) {
  this._snackBar.open(msg, 'Ok', {
    duration: 2000,
  });
}



}
