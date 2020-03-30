package br.com.dbccompany.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.dbccompany.dto.CotacaoDollarDTO;
import br.com.dbccompany.dto.EstadoDTO;
import br.com.dbccompany.entity.Estado;
import br.com.dbccompany.exception.EstadoNaoEncontradoException;
import br.com.dbccompany.repository.EstadoRepository;
import br.com.dbccompany.service.EstadoService;
import br.com.dbccompany.util.MapperConverter;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	private static final BigDecimal CUSTO_CIDADAO = new BigDecimal("123.45");
	private static final BigDecimal VALOR_CORTE = new BigDecimal(50000);
	private static final BigDecimal DESCONTO = new BigDecimal("12.3");
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private static final String URL_COTACAO_USD_BRL = "https://economia.awesomeapi.com.br/USD-BRL";
	
	public Estado salvar(Estado estado) {		
		return estadoRepository.save(estado);		
	}

	public List<EstadoDTO> listarEstados() {
		List<Estado> estados = estadoRepository.listarEstados();
		List<EstadoDTO> listaDTO = MapperConverter.mapList(estados, EstadoDTO.class);
		listaDTO.stream().forEach(e -> { 
				e.setPopulacao(calcularPopulacaoEstado(e.getUf()));
				e.setCustoPopulacional(calcularCustoPopulacional(e.getUf()));
			});
		
		
		return listaDTO;
	}
	
	public Integer calcularPopulacaoEstado(String uf) {
		Estado estado = estadoRepository.buscarEstadoPorUf(uf);
		return estado.getCidades().stream()
					.map(p -> p.getPopulacao())
					.mapToInt(Integer::intValue)
					.sum();		
	}
	
	public BigDecimal calcularCustoPopulacional(String uf) {
		Integer populacao = this.calcularPopulacaoEstado(uf);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CotacaoDollarDTO[]> entity = restTemplate.getForEntity(URL_COTACAO_USD_BRL, CotacaoDollarDTO[].class);

		BigDecimal cotacaoDolar = entity.getBody()[0].getHigh();
		
		BigDecimal consultaCusto = new BigDecimal(populacao).multiply(CUSTO_CIDADAO).multiply(cotacaoDolar);
		if (consultaCusto.compareTo(VALOR_CORTE) > 0) {
			BigDecimal desconto = consultaCusto.multiply(DESCONTO).divide(ONE_HUNDRED);
			return consultaCusto.subtract(desconto);
		}
		
		return consultaCusto;
	}

	@Override
	public Estado salvar(EstadoDTO estadoDTO) {
		Estado estado = Estado.builder().build();
		BeanUtils.copyProperties(estadoDTO, estado);
		return salvar(estado);
	}

	@Override
	public List<EstadoDTO> buscarEstadoPorId(Long id) {
		Estado estado = estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException("Não foi encontrado nenhum estado com este id."));
		
		List<EstadoDTO> listaDTO = MapperConverter.mapList(Arrays.asList(estado), EstadoDTO.class);
		listaDTO.stream().forEach(e -> { 
			e.setPopulacao(calcularPopulacaoEstado(e.getUf()));
			e.setCustoPopulacional(calcularCustoPopulacional(e.getUf()));
		});
		
		return listaDTO;
	}

}
