package br.com.dbccompany.service;

import java.util.List;

import br.com.dbccompany.dto.EstadoDTO;
import br.com.dbccompany.entity.Estado;

public interface EstadoService {
	
	Estado salvar(EstadoDTO estado);
	
	List<EstadoDTO> listarEstados();
	
	List<EstadoDTO> buscarEstadoPorId(Long id);

}
