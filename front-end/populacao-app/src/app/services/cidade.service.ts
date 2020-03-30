import { Injectable } from '@angular/core';

import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Estado } from '../model/estado';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cidade } from '../model/cidade';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {  
  url = environment.API + "/cidades";
  
  constructor(private http: HttpClient) { }
  
  listarCidades() {
    return this.http.get<any[]>(`${this.url}`);
  }

  buscarCidadesPorUf(uf: string) {
    return this.http.get<any[]>(`${this.url}/estado/${uf}`);
  }


    
  salvarCidade(cidade: Cidade) {    
    return this.http.post(this.url, cidade);
  }

 
  excluirCidade(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }

  


}
