import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './vistas/inicio/inicio.component';
import { DashboardComponent } from './vistas/dashboard/dashboard.component';
import { EditarComponent } from './vistas/editar/editar.component';
import { LoginComponent } from './vistas/login/login.component';
import { NuevoComponent } from './vistas/nuevo/nuevo.component';
//import {LoginComponent} from './vistas/login/login.component';

const routes: Routes = [
  { path: '', redirectTo:'inicio', pathMatch:'full'},
  { path: 'inicio', component:InicioComponent},
  { path: 'dashboard', component:DashboardComponent},
  { path: 'editar', component:EditarComponent},
  { path: 'nuevo', component:NuevoComponent},
  { path: 'login', component:LoginComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponets = [InicioComponent,DashboardComponent,EditarComponent,NuevoComponent,LoginComponent]
