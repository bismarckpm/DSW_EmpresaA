import { HijoServicioService } from './services/hijo-servicio.service';
import { TelefonoServicioService } from './services/telefono-servicio.service';
import { EstudioService } from './services/estudio.service';
import { RolServicioService } from './services/rol-servicio.service';


import { NivelEconomicoServicioService } from './services/nivel-economico-servicio.service';

import { OcupacionServicioService } from './services/ocupacion-servicio.service';

import { NivelAcademicoServicioService } from './services/nivel-academico-servicio.service';
import { Nivel_Academico } from './models/nivel_academico';
import { UsuarioServicioService } from './services/usuario-servicio.service';

import { LugarServicioService } from './services/lugar-servicio.service';

import { SidebarComponent } from './components/sidebar/sidebar.component';

import { EncuestadoServicioService } from './services/encuestado-servicio.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing, appRoutingProviders } from './app-routing.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule, DatePipe } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

// Components
import { AppComponent } from './app.component';
import { DashboardproductoComponent } from './components/dashboardproducto/dashboardproducto.component';
import { CategoriaComponent } from './components/categoria/categoria.component';
import { SubcategoriaComponent } from './components/subcategoria/subcategoria.component';
import { DialogsubcategoriaComponent } from './components/dialog/dialogsubcategoria/dialogsubcategoria.component';
import { DialogcategoriaComponent } from './components/dialog/dialogcategoria/dialogcategoria.component';
import { MarcaComponent } from './components/marca/marca.component';
import { DialogmarcaComponent } from './components/dialog/dialogmarca/dialogmarca.component';
import { TipoComponent } from './components/tipo/tipo.component';
import { PresentacionComponent } from './components/presentacion/presentacion.component';
import { DialogtipoComponent } from './components/dialog/dialogtipo/dialogtipo.component';
import { CreatePresentacionComponent } from './components/create_components/create-presentacion/create-presentacion.component';
import { AddcategoriaComponent } from './components/create_components/addcategoria/addcategoria.component';
import { CreateSubcategoriaComponent } from './components/create_components/create-subcategoria/create-subcategoria.component';
import { CreateMarcaComponent } from './components/create_components/create-marca/create-marca.component';
import { CreateTipoComponent } from './components/create_components/create-tipo/create-tipo.component';
/*import { LoginComponent } from './components/login/login.component';
import { RegistraPreguntaComponent } from './components/pregunta/registra-pregunta/registra-pregunta.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { ConsultaPreguntaComponent } from './components/pregunta/consulta-pregunta/consulta-pregunta.component';*/
import { DialogpresentacionComponent } from './components/dialog/dialogpresentacion/dialogpresentacion.component';
import { CreateProductoComponent } from './components/create_components/create-producto/create-producto.component';
import { CreatetipopresentacionComponent } from './components/create_components/createtipopresentacion/createtipopresentacion.component';
import { DetalleProductoComponent } from './components/producto/detalle-producto/detalle-producto.component';

// Routing
import { AppRoutingModule } from './app-routing.module';
import { DatoUsuarioComponent } from './components/dato-usuario/dato-usuario.component';


import { FormsModule } from '@angular/forms';
/* import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component'; */
//import { FontAwesomeModule } from '@fontawesome/angular-fontawesome';

/* import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component'; */


import { ErrorComponent } from './components/error/error.component';
import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component';
import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component';
import { ConsultarEstudiosComponent } from './components/consultar-estudios/consultar-estudios.component';
import { ModificarEstudioComponent } from './components/modificar-estudio/modificar-estudio.component';
import { DialogconsultarestudioComponent } from './components/dialogconsultarestudio/dialogconsultarestudio.component';
import { CrearUsuarioComponent } from './components/crear-usuario/crear-usuario.component';
import { DatosAdicionalesComponent } from './components/datos-adicionales/datos-adicionales.component';
import { CrearEstudioComponent } from './components/crear-estudio/crear-estudio.component';
import { ConsultarEstudioAnalistaComponent } from './components/consultar-estudio-analista/consultar-estudio-analista.component';
import { ConsultarEstudioEncuestadoComponent } from './components/consultar-estudio-encuestado/consultar-estudio-encuestado.component';
import { DesarrollarEntrevistaComponent } from './desarrollar-entrevista/desarrollar-entrevista.component';
import { ContestarEncuestaComponent } from './components/contestar-encuesta/contestar-encuesta.component';
import { DialogMostrarUsuarioComponent } from './components/dialog-mostrar-usuario/dialog-mostrar-usuario.component';
import { ConsultarEncuestadosEstudioComponent } from './components/consultar-encuestados-estudio/consultar-encuestados-estudio.component';
import { AsignarPreguntasEstudioComponent } from './components/asignar-preguntas-estudio/asignar-preguntas-estudio.component';
//import { MatSelectFilterModule } from 'mat-select-filter';
// Material imports
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatStepperModule} from '@angular/material/stepper';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatBadgeModule} from '@angular/material/badge';
import {MatRippleModule} from '@angular/material/core';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatSliderModule } from '@angular/material/slider';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { DialogProductoTipoPresentacionComponent } from './components/dialog/dialog-producto-tipo-presentacion/dialog-producto-tipo-presentacion.component';
import {MatTooltipModule} from '@angular/material/tooltip';


@NgModule({
  declarations: [
    AppComponent,
    /*LoginComponent,
    RegistraPreguntaComponent,
    NavigationComponent,
    ConsultaPreguntaComponent,*/
    CategoriaComponent,
    DashboardproductoComponent,
    SubcategoriaComponent,
    AddcategoriaComponent,
    DialogcategoriaComponent,
    CreateSubcategoriaComponent,
    MarcaComponent,
    CreateMarcaComponent,
    DialogmarcaComponent,
    DialogsubcategoriaComponent,
    TipoComponent,
    PresentacionComponent,
    CreateTipoComponent,
    DialogtipoComponent,
    CreatePresentacionComponent,
    DialogpresentacionComponent,
    CreateProductoComponent,
    CreatetipopresentacionComponent,
    DetalleProductoComponent,
    DialogProductoTipoPresentacionComponent,
    DatoUsuarioComponent,
    ConsultarUsuarioComponent,
    ModificarUsuarioComponent,
    ErrorComponent,
    SidebarComponent,
    ConsultarEstudiosComponent,
    ModificarEstudioComponent,
    DialogconsultarestudioComponent,
    CrearUsuarioComponent,
    DatosAdicionalesComponent,
    CrearEstudioComponent,
    ConsultarEstudioAnalistaComponent,
    ConsultarEstudioEncuestadoComponent,
    DesarrollarEntrevistaComponent,
    ContestarEncuestaComponent,
    DialogMostrarUsuarioComponent,
    ConsultarEncuestadosEstudioComponent,
    AsignarPreguntasEstudioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    //FontAwesomeModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatDialogModule,
    MatIconModule,
    MatButtonModule,
    //MatSelectFilterModule,
    MatExpansionModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatTooltipModule
  ],
  exports:[],
  providers: [appRoutingProviders, DatePipe, EncuestadoServicioService, LugarServicioService,
              UsuarioServicioService, NivelAcademicoServicioService,
              OcupacionServicioService, NivelEconomicoServicioService,
              RolServicioService, EstudioService, TelefonoServicioService,
              HijoServicioService],

  bootstrap: [AppComponent]
})
export class AppModule { }
