import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  
  url = environment.API + "/estados";
  
  constructor(private http: HttpClient) { }
  
  listarEstados() {
    return this.http.get<any[]>(`${this.url}`);
  }
  
  buscarEstado(id: number) {
    return this.http.get<any[]>(`${this.url}/${id}`);
  }
}
