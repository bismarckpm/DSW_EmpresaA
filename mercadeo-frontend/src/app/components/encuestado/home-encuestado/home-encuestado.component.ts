import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { delay, map, retryWhen, take } from 'rxjs/operators';
import { GetEstudio, GetEstudioEncuestado } from 'src/app/interfaces/estudio';
import { User } from 'src/app/interfaces/user';
import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';
import { LoginService } from 'src/app/services/login.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { ProductoService } from 'src/app/services/producto.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-home-encuestado',
  templateUrl: './home-encuestado.component.html',
  styleUrls: ['./home-encuestado.component.css'],
  providers: [NgbCarouselConfig]
})
export class HomeEncuestadoComponent implements OnInit {

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
};

  // VARIABLES
  menuPosition: any;

  @HostListener('window:scroll', ['$event'])
    handleScroll(){
        const windowScroll = window.pageYOffset;
        if(windowScroll >= this.menuPosition){
            this.sticky = true;
        } else {
            this.sticky = false;
      }
  }

  @ViewChild('stickyMenu') menuElement!: ElementRef;

  // images = [700, 533, 807, 124].map((n) => `https://picsum.photos/id/${n}/900/500`);
  // Es una pagina que posee imagenes, en esta genera aleatoriamente una imagen
  // Existen mejores formas de colocarlos, pero en este momento no se como aun
  images= [`https://picsum.photos/1200/500?random=1`,`https://picsum.photos/1200/500?random=2`,`https://picsum.photos/1200/500?random=3`,
  `https://picsum.photos/1200/500?random=4`,`https://picsum.photos/1200/500?random=5`,`https://picsum.photos/1200/500?random=6`,
  `https://picsum.photos/1200/500?random=7`,`https://picsum.photos/1200/500?random=8`,`https://picsum.photos/1200/500?random=9`,
  `https://picsum.photos/1200/500?random=10`,`https://picsum.photos/1200/500?random=11`]

  // Estados
  isWait: boolean = false;
  sticky: boolean = false;
  isEmpty = false;
  isEmpty2 = false;
  estado = '';

  // Usuarios
  public identity: any;
  isUser = false;
  public user: User = {
    id:0,
    nombreUsuario:'',
    correo:'',
    estado:'',
    idRol:0
  };

  // Encuestas
  disponibilidad: any;
  encuestaRespondida: any;
  cantidad = 0;      // cambie el tipo da dato aca a number
  estudios: GetEstudio[] = [];
  amount = 0;        // cambie el tipo da dato aca a number
  estudiosPr: GetEstudio[] = [];
  estudiosR: GetEstudio[] = [];
  // Snackbar
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';


  constructor(
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _estudioService: EstudioclienteService,
    private _productoService: ProductoService,
    private _datoService: EncuestadoServicioService,
    private _pe: PreguntaEncuestaServiceService,
    private estudio: EstudioService,
    private navegacion: Router,
    private _snackBar: MatSnackBar,
    config: NgbCarouselConfig,
    private _alertService: AlertService,
  ) {
    config.interval = 10000;
    config.keyboard = false;
    config.pauseOnHover = false;
    config.showNavigationIndicators = false;
  }

  ngOnInit(): void {
    this.getUser();
    /* this.estudiosRespondidos(); */
    this._snackBar.open('Por favor espere, cargando estudios', undefined, {
      duration: 3000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

    this.busquedaEstudios();
    this.buscarDisponibilidad();
  }

  ngAfterViewInit(){
    this.menuPosition = this.menuElement.nativeElement.offsetTop;
 }

  // METODOS
      //////ESTO LO COMENTE PORQUE YA NO USAREMOS EL METODO DE LOS ESTUDIOS RESPONDIDOS
 //estudiosRespondidos(){
  /* this.estudio.getEncuestaRespondida(this.user.id).subscribe(
    response => {
      this.encuestaRespondida = response;

      this.cantidad = this.encuestaRespondida.length;


        // Si esta vacio el array
        // isEmpty = true
      if (this.encuestaRespondida.length == 0) {
          this.isEmpty = true;
        } else {
          this.isEmpty = false;
        }

      console.log('respondidos',  this.encuestaRespondida);
    }, error => {
      console.log(<any>error);
    }
  ) */
//}


// Estudios/Encuesta por Responder
// Solo ando usando esto para obtener la cantidad de estudios que falten

  busquedaEstudios() {
    this.estudio.getEstudios(this.user.id).subscribe(
      (estudios) => {
        this.estudios = estudios.objeto;
        console.log("Ayudaa", this.estudios)
        console.log('Estudios por responder', this.estudios);
        // ACA VAMOS A VALIDAR EL ESTADO DE CADA UNO DE LOS ESTUDIOS QUE VIENEN


        const estudioPR = this.estudios.filter(item=> item._estatus == 'En Espera' || item._estatus == 'En Proceso' )
        const estudioR = this.estudios.filter(item=> item._estatus == 'Finalizado')

       // Si esta vacio el array
        // isEmpty2 = true
        if (estudioPR.length === 0) {
        this.isEmpty2 = true;
      } else {
        this.isEmpty2 = false;
      }

      // Si esta vacio el array
        // isEmpty = true
        if (estudioR.length === 0) {
        this.isEmpty = true;
      } else {
        this.isEmpty = false;
      }

        // for (let i = 0; i < this.estudios.length; i++){
        //   this.validarEncuesta(this.estudios[i]);
        // }


        this.estudios.forEach( item => {
          this.validarEncuesta(item);
        })

        console.log(this.estudios)

      },
      error => {
        console.log(<any>error)},

    );
  }

  Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }


  validar(estudio: any)  {
     this._pe.validarPreguntas(estudio._id!, this.user.id).subscribe((data)=>{
        let proceso = data.objeto
        this._alertService.info('Estado: ' + data.objeto, this.options)
      })
  }

  async validarEncuesta(estudio: GetEstudio) {
    console.log(estudio._id!);
    console.log(this.user.id);
    // ACA SE LLAMA METODO PARA SABER SI EL ESTUDIO ESTA EN ESPERA, EN PROCESO O FINALIZADO
    await this.Delay(4000);
    this._pe.validarPreguntas(estudio._id!, this.user.id).subscribe(
     response => {
       this.estado = response.objeto;
       console.log(this.estado);

       if (this.estado === 'En Espera') {
         estudio._icono = 'input';
         this.estudiosPr.push(estudio);
          //ICONO PARA ESTUDIOS EN ESPERA, SI ENCUENTRAN UNO MEJOR SE LO COLOCAN
         this.amount = this.amount + 1;

       }
       else if (this.estado === 'En Proceso'){
        estudio._icono = 'edit';
        this.estudiosPr.push(estudio);
        //ICONO PARA ESTUDIOS EN PROCESO, SI ENCUENTRAN UNO MEJOR SE LO COLOCAN
        this.amount = this.amount + 1;

       }
       else {
         // ESTUDIOS FINALIZADOS EL ICONO ES EL MISMO QUE YA TENIA
         this.estudiosR.push(estudio);
         this.cantidad = this.cantidad + 1;
       }

       // Si esta vacio el array
        // isEmpty2 = true
       if (this.estudiosPr.length === 0 || this.disponibilidad._disponibilidadEnLinea === 'No') {
        this.isEmpty2 = true;
      } else {
        this.isEmpty2 = false;
      }

      // Si esta vacio el array
        // isEmpty = true
       if (this.estudiosR.length === 0) {
        this.isEmpty = true;
      } else {
        this.isEmpty = false;
      }


     }

   );

 }


  // Para navegar a las respuestas
  encuestaContestada(id: number){
    this.navegacion.navigate(['encuestarespondida', id, this.user.id]);
  }

  // Para contestar una encuesta disponible
  encuesta(id: number) {
    this.validar(id);
    this.navegacion.navigate(['contestarencuesta', id, this.user.id]);
  }

  // Scrolling
  scroll(element: any) {
    element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"})
}


  // Obtener el User
  getUser(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol )

    if (this.user) {
        this.isUser = true;
        console.log(this.user)
    }
}

buscarDisponibilidad() {
  this._datoService.getDatoUsuarioPorIdUsuario(this.user.id).subscribe(
    (response) => {
      this.disponibilidad = response.objeto;
      console.log(this.disponibilidad);

    }
  );
}


}
