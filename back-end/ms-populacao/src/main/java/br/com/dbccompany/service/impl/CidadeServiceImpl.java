package br.com.dbccompany.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbccompany.dto.CidadeDTO;
import br.com.dbccompany.entity.Cidade;
import br.com.dbccompany.exception.CidadeNaoEncontradaException;
import br.com.dbccompany.exception.CidadeNaoPodeSerExcluidaException;
import br.com.dbccompany.exception.CidadeNomeDuplicadoException;
import br.com.dbccompany.repository.CidadeRepository;
import br.com.dbccompany.service.CidadeService;
import br.com.dbccompany.util.MapperConverter;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<CidadeDTO> listarCidadePorEstado(String uf) {
		
		return MapperConverter.mapList(cidadeRepository.listarCidadePorEstado(uf), CidadeDTO.class); 
		
	}

	public Cidade salvar(Cidade cidade) {
		Optional<Cidade> optional = cidadeRepository.buscarCidadePorNomeEPorEstado(cidade.getNome(), cidade.getEstado().getUf());
		if (optional.isPresent()) {
			throw new CidadeNomeDuplicadoException("Já existe uma cidade com este mesmo Estado.");
		}
		return cidadeRepository.save(cidade);
		
	}

	public void excluir(Long id) {
		Cidade cidade = cidadeRepository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaException("Não foi encontrada cidade com este id."));
		Optional<Cidade> optional = cidadeRepository.buscarCidadePorNomeEPorEstado(cidade.getNome(), cidade.getEstado().getUf());
		if (optional.isPresent() && cidade.getEstado().getUf().equals("RS")) {
			throw new CidadeNaoPodeSerExcluidaException("Esta cidade não pode ser removida do Estado Rio Grande do Sul.");
		}
		cidadeRepository.delete(cidade);
	}

	@Override
	public Cidade salvar(CidadeDTO cidadeDTO) {
		Cidade cidade = Cidade.builder().build();
		BeanUtils.copyProperties(cidadeDTO, cidade);
		return salvar(cidade);
	}

	@Override
	public List<CidadeDTO> listarCidade() {
		return MapperConverter.mapList(cidadeRepository.listarTodasCidades(), CidadeDTO.class); 
	}

}
