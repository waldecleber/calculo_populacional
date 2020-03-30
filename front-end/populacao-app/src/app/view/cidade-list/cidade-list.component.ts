import { Component, OnInit } from '@angular/core';
import { CidadeService } from 'src/app/services/cidade.service';

@Component({
  selector: 'app-cidade-list',
  templateUrl: './cidade-list.component.html',
  styleUrls: ['./cidade-list.component.css']
})
export class CidadeListComponent implements OnInit {

  cidades: Array<any>;

  constructor(private cidadeService: CidadeService) { }

  ngOnInit(): void {

    this.listarCidades();
  }

  listarCidades() {
    this.cidadeService.listarCidades()
    .subscribe(dados => {
      this.cidades = dados;
    });
  }



}
