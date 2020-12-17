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
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DatoUsuarioComponent } from './components/dato-usuario/dato-usuario.component';


import { FormsModule } from '@angular/forms';
/* import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component'; */
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

/* import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component'; */


import { ErrorComponent } from './components/error/error.component';
import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component';
import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component';
import { ConsultarEstudiosComponent } from './components/consultar-estudios/consultar-estudios.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSelectModule } from '@angular/material/select';
import { ModificarEstudioComponent } from './components/modificar-estudio/modificar-estudio.component';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from './components/dialogconsultarestudio/dialogconsultarestudio.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
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
import { MatSelectFilterModule } from 'mat-select-filter';
import { DatePipe } from '@angular/common'

@NgModule({
  declarations: [
    AppComponent,
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
    FontAwesomeModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatDialogModule,
    MatIconModule,
    MatButtonModule,
    MatSelectFilterModule
  ],
  exports:[],
  providers: [EncuestadoServicioService, LugarServicioService,
              UsuarioServicioService, NivelAcademicoServicioService,
              OcupacionServicioService, NivelEconomicoServicioService,
              RolServicioService, EstudioService, TelefonoServicioService,
              HijoServicioService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
