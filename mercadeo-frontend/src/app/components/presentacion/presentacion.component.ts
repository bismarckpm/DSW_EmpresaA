import { Component, OnInit } from '@angular/core';
import { Presentacion } from 'src/interfaces/presentacion';
import { PresentacionService } from 'src/services/presentacion.service';
import { ProductoService } from 'src/services/producto.service';

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

  presentaciones: Presentacion[] = [];

  constructor(
    private _presentacionService: PresentacionService,
    private _productoService: ProductoService,
  ) { }

  ngOnInit(): void {
    this.get();
  }
  
  get(): void {
    this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data});
  }


}
