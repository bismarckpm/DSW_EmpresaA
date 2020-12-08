import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { routing, appRoutingProviders } from './app-routing.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import {MatIconModule} from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistraPreguntaComponent } from './components/pregunta/registra-pregunta/registra-pregunta.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { ConsultaPreguntaComponent } from './components/pregunta/consulta-pregunta/consulta-pregunta.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistraPreguntaComponent,
    NavigationComponent,
    ConsultaPreguntaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    routing,
    NoopAnimationsModule,
    MatSliderModule,
    MatIconModule,
    CommonModule,
    MatButtonModule
  ],
  providers: [
    appRoutingProviders,

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
