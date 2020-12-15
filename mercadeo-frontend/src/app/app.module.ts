import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing, appRoutingProviders } from './app-routing.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

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
import { LoginComponent } from './components/login/login.component';
import { RegistraPreguntaComponent } from './components/pregunta/registra-pregunta/registra-pregunta.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { ConsultaPreguntaComponent } from './components/pregunta/consulta-pregunta/consulta-pregunta.component';
import { DialogpresentacionComponent } from './components/dialog/dialogpresentacion/dialogpresentacion.component';
import { CreateProductoComponent } from './components/create_components/create-producto/create-producto.component';
import { VistaestudiosComponent } from './components/estudio-cliente/vistaestudios/vistaestudios.component';
import { DialogestudiocliComponent } from './components/dialog/dialogestudiocli/dialogestudiocli.component';
import { ResultadoestudioComponent } from './components/estudio-cliente/resultadoestudio/resultadoestudio.component';
import { RespuestapreguntaComponent } from './components/respuestapregunta/respuestapregunta.component';
import { ConsultarespuestaComponent } from './components/consultarespuesta/consultarespuesta.component';
import { DialogopcionComponent } from './components/dialog/dialogopcion/dialogopcion.component';
import { RecuperarpasswordComponent } from './components/recuperarpassword/recuperarpassword.component';
import { RegistrarsolicitudComponent } from './components/solicitud_estudio/registrarsolicitud/registrarsolicitud.component';

// Routing
import { AppRoutingModule } from './app-routing.module';

// Import ngModel
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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
import { CreatetipopresentacionComponent } from './components/create_components/createtipopresentacion/createtipopresentacion.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistraPreguntaComponent,
    NavigationComponent,
    ConsultaPreguntaComponent,
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
    VistaestudiosComponent,
    DialogestudiocliComponent,
    ResultadoestudioComponent,
    RespuestapreguntaComponent,
    ConsultarespuestaComponent,
    DialogopcionComponent,
    RecuperarpasswordComponent,
    RegistrarsolicitudComponent,
    CreateProductoComponent,
    CreatetipopresentacionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    routing,
    NoopAnimationsModule,
    CommonModule,
    NgbModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    // Material imports
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatDialogModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatStepperModule,
    MatListModule,
    MatDividerModule,
    MatBadgeModule,
    MatRippleModule,
    MatSliderModule,
    MatButtonModule,
    MatExpansionModule
  ],
  providers: [
    appRoutingProviders,

  ],
  bootstrap: [AppComponent]

})
export class AppModule { }
