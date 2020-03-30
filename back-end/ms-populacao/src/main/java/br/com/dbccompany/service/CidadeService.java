package br.com.dbccompany.service;

import java.util.List;

import br.com.dbccompany.dto.CidadeDTO;
import br.com.dbccompany.entity.Cidade;

public interface CidadeService {

	List<CidadeDTO> listarCidadePorEstado(String uf);
	
	Cidade salvar(Cidade cidade);
	
	void excluir(Long id);

	Cidade salvar(CidadeDTO cidade);

	List<CidadeDTO> listarCidade();
}
