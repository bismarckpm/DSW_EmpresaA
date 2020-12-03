import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {Route} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

//Imports de los componentes.

import { LoginComponent } from './components/login/login.component';

//Rutas de la app
const routes: Routes = [
  {path: 'login',component: LoginComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders<Route> = RouterModule.forRoot(routes);