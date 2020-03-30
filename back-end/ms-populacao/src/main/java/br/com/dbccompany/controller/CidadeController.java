package br.com.dbccompany.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbccompany.dto.CidadeDTO;
import br.com.dbccompany.entity.Cidade;
import br.com.dbccompany.service.CidadeService;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author waldecleber
 *
 */
@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CidadeController {
	
	private final CidadeService cidadeService;
	
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CidadeDTO>> listarCidades() {		
		return ResponseEntity.ok().body(cidadeService.listarCidade());
	}
	
	@CrossOrigin
	@GetMapping(path = "/estado/{uf}")
	public ResponseEntity<List<CidadeDTO>> listarCidadePorEstado(@PathVariable(name = "uf") String uf) {		
		return ResponseEntity.ok().body(cidadeService.listarCidadePorEstado(uf));
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody CidadeDTO cidade) {    	
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidade));			
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public Map<String, Boolean> excluir(@PathVariable Long id) {
		cidadeService.excluir(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
