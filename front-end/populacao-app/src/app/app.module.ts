import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NgxCurrencyModule } from "ngx-currency";

import { AppComponent } from './app.component';
import { EstadoService } from './services/estado.service';
import { CidadeService } from './services/cidade.service';
import { CidadeListComponent } from './view/cidade-list/cidade-list.component';
import { CidadeFormComponent } from './view/cidade-form/cidade-form.component';
import { EstadoListComponent } from './view/estado-list/estado-list.component';
import { TopBarComponent } from './view/top-bar/top-bar.component';



@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    CidadeListComponent,
    CidadeFormComponent,
    EstadoListComponent    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    NgxCurrencyModule
  ],
  providers: [CidadeService, EstadoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
