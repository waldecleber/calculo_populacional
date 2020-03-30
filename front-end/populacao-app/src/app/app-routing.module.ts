import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EstadoListComponent } from './view/estado-list/estado-list.component';
import { CidadeService } from './services/cidade.service';
import { CidadeFormComponent } from './view/cidade-form/cidade-form.component';
import { CidadeListComponent } from './view/cidade-list/cidade-list.component';


const routes: Routes = [
  {
    path: '', component: EstadoListComponent
  },
  { 
    path: 'cidade-form', component: CidadeFormComponent 
  },
  { 
    path: 'cidade-list', component: CidadeListComponent 
  },
  // { 
  //   path: 'cliente-form/:id', component: ClientesFormComponent 
  // },
  // { 
  //   path: 'emprestimo-form/:id', component: EmprestimoFormComponent
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [CidadeService]
})
export class AppRoutingModule { }
