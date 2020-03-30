package br.com.dbccompany;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.dbccompany.entity.Cidade;
import br.com.dbccompany.entity.Estado;
import br.com.dbccompany.exception.CidadeNaoPodeSerExcluidaException;
import br.com.dbccompany.exception.CidadeNomeDuplicadoException;
import br.com.dbccompany.repository.CidadeRepository;
import br.com.dbccompany.service.impl.CidadeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CidadeServiceTest {
	

	@InjectMocks
	private CidadeServiceImpl cidadeService;

	@Mock
	private CidadeRepository cidadeRepository;

	@Test
	public void listarCidadesPorEstado() {
		when(cidadeRepository.listarCidadePorEstado(anyString()))				
			.thenReturn(List.of(Cidade.builder().build()));
		cidadeService.listarCidadePorEstado("RS");
		verify(cidadeRepository, times(1)).listarCidadePorEstado(anyString());
	}
	
	@Test
	public void salvarCidadeComSucess() {
		Estado estadoRS = buildEstadoRS();		
		Cidade cidade = Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.estado(estadoRS)
				.build();
	
		when(cidadeRepository.save(any(Cidade.class)))				
			.thenReturn(cidade);
		cidadeService.salvar(cidade);
		verify(cidadeRepository, times(1)).save(cidade);
		
	}
	
	@Test(expected = CidadeNomeDuplicadoException.class)
	public void salvarCidadeExceptionComMesmoNomePorEstado() {
		Estado estadoRS = buildEstadoRS();		
		Cidade cidade1 = Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.estado(estadoRS)
				.build();
		
		when(cidadeRepository.save(any(Cidade.class)))				
			.thenReturn(cidade1);
		cidadeService.salvar(cidade1);
		
		Cidade cidade2 = Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.estado(estadoRS)
				.build();
		
		doReturn(Optional.of(Cidade.builder().build()))
			.when(cidadeRepository).buscarCidadePorNomeEPorEstado(cidade2.getNome(), estadoRS.getUf());
		cidadeService.salvar(cidade2);		
		
		verify(cidadeRepository, times(0)).save(cidade2);		
	}
	
	@Test
	public void excluirCidadeComSucesso() {
		Estado estadoSC = Estado.builder()
				.id(1L)
				.nome("Santa Catarina")
				.uf("SC")
				.build();		
		Cidade cidade = Cidade.builder()
				.id(1L)
				.nome("Blumenal")
				.estado(estadoSC)
				.build();
	
		when(cidadeRepository.save(any(Cidade.class)))				
			.thenReturn(cidade);
		cidadeService.salvar(cidade);
		verify(cidadeRepository, times(1)).save(cidade);
		
		doReturn(Optional.of(cidade)).when(cidadeRepository).findById(1L);
		doReturn(Optional.of(Cidade.builder().build()))
			.when(cidadeRepository).buscarCidadePorNomeEPorEstado(cidade.getNome(), estadoSC.getUf());
	
		
		cidadeService.excluir(cidade.getId());
		verify(cidadeRepository, times(1)).delete(cidade);
		
	}
	
	@Test(expected = CidadeNaoPodeSerExcluidaException.class)
	public void tentarExcluirCidadeDoRioGrandeDoSul() {
		Estado estadoRS = buildEstadoRS();		
		Cidade cidade = Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.estado(estadoRS)
				.build();
	
		when(cidadeRepository.save(any(Cidade.class)))				
			.thenReturn(cidade);
		cidadeService.salvar(cidade);
		verify(cidadeRepository, times(1)).save(cidade);
		
		doReturn(Optional.of(Cidade.builder().build()))
			.when(cidadeRepository).buscarCidadePorNomeEPorEstado(cidade.getNome(), estadoRS.getUf());
		
		doReturn(Optional.of(cidade))
			.when(cidadeRepository).findById(1L);
		
		cidadeService.excluir(cidade.getId());
		verify(cidadeRepository, times(0)).delete(cidade);		
	}

	private Estado buildEstadoRS() {
		return Estado.builder()
			.id(1L)
			.nome("Rio Grande do Sul")
			.uf("RS")
			.build();
	}

}
