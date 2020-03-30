import { Component, OnInit } from '@angular/core';
import { EstadoService } from 'src/app/services/estado.service';
import { Estado } from 'src/app/model/estado';
import { CidadeService } from 'src/app/services/cidade.service';
import { Cidade } from 'src/app/model/cidade';

@Component({
  selector: 'app-estado-list',
  templateUrl: './estado-list.component.html',
  styleUrls: ['./estado-list.component.css']
})
export class EstadoListComponent implements OnInit {

  estados: Array<any>;
  estadosComboBox: Array<any>;
  estado: any = {
    id: 2
  };

  cidades: Array<any>;

  constructor(
    private estadoService: EstadoService,
    private cidadeService: CidadeService
  ) { }

  ngOnInit(): void {
    this.estadoService.listarEstados()
      .subscribe(dados => {
        this.estadosComboBox = dados;
      });

      this.listarEstados();
      this.listarCidadePorUF("RS");
  }

  listarEstados() {    
    if(this.estado.id > 0) {
      this.estadoService.buscarEstado(+this.estado.id)
        .subscribe(data => { 
          this.estados = data;
      },
      error => {
         console.log(error);
      });
    } else {      
      this.estadoService.listarEstados()
      .subscribe(dados => {
        this.estados = dados;
      });
    }

    
    
  }

  listarCidadePorUF(uf: string) {
    this.cidadeService.buscarCidadesPorUf(uf)
        .subscribe(data => { 
          this.cidades = data;
      },
      error => {
         console.log(error);
      });
  }

  

}
