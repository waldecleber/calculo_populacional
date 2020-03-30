package br.com.dbccompany;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.dbccompany.entity.Cidade;
import br.com.dbccompany.entity.Estado;
import br.com.dbccompany.repository.EstadoRepository;
import br.com.dbccompany.service.impl.EstadoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EstadoServiceTest {

	@InjectMocks
	private EstadoServiceImpl estadoService;

	@Mock
	private EstadoRepository estadoRepository;
	
	@Test
	public void salvaEstadoComSucesso() {
		Estado estado = Estado.builder()
				.id(1L)
				.nome("Rio Grande do Sul")
				.build();
		
		when(estadoRepository.save(any(Estado.class))).thenReturn(estado);
		estadoService.salvar(estado);
		verify(estadoRepository, times(1)).save(estado);
	
	}
	
//	@Test
//	public void listarEstados() {
//		when(estadoRepository.listarEstados())				
//			.thenReturn(List.of(Estado.builder().build()));
//		
//		when(estadoRepository.buscarEstadoPorUf("RS")).thenReturn(Estado.builder().build());
//		
//		estadoService.listarEstados();
//		verify(estadoRepository, times(1)).listarEstados();
//	}
	
	@Test
	public void calcularPopulacaoEstado() {
		Estado estado = Estado.builder()
				.id(1L)
				.nome("Rio Grande do Sul")
				.uf("RS")
				.build();
		
		Cidade cidade1 = Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.populacao(12000)
				.estado(estado)
				.build();
		
		Cidade cidade2 = Cidade.builder()
				.id(2L)
				.nome("Canoas")
				.populacao(6000)
				.estado(estado)
				.build();
		
		Cidade cidade3 = Cidade.builder()
				.id(3L)
				.nome("Novo Hamburso")
				.populacao(4000)
				.estado(estado)
				.build();
		
		List<Cidade> cidades = new ArrayList<>();
		
		cidades.add(cidade1);
		cidades.add(cidade2);
		cidades.add(cidade3);
		
		estado.setCidades(new HashSet<>(cidades));
		
		when(estadoRepository.buscarEstadoPorUf("RS")).thenReturn(estado);
		
		Integer totalPopulacao = estadoService.calcularPopulacaoEstado(estado.getUf());
		
		assertEquals(22000, totalPopulacao);
		
	}
 	
}
