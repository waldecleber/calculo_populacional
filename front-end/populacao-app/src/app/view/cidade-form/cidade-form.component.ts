import { Component, OnInit } from '@angular/core';
import { Cidade } from 'src/app/model/cidade';
import { EstadoService } from 'src/app/services/estado.service';
import { CidadeService } from 'src/app/services/cidade.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Estado } from 'src/app/model/estado';

@Component({
  selector: 'app-cidade-form',
  templateUrl: './cidade-form.component.html',
  styleUrls: ['./cidade-form.component.css']
})
export class CidadeFormComponent implements OnInit {

  cidade: Cidade;

  estados: Array<Estado>;

  estado: Estado;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cidadeService: CidadeService,
    private estadoService: EstadoService) { }

  ngOnInit(): void {
    this.cidade = new Cidade();
    
    this.listarEstados();
  }

  salvarCidade() {
    
    this.cidade.estado = this.estado;
    console.log("this.estado", this.cidade);
    this.cidadeService.salvarCidade(this.cidade).subscribe(resp => {
      this.router.navigate(['/']);
    });
  }

  
  listarEstados() {
    this.estadoService.listarEstados()
    .subscribe(dados => {
      this.estados = dados;
    });
    
  }

}
