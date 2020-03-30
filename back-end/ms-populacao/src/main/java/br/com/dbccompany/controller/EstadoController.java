package br.com.dbccompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbccompany.dto.EstadoDTO;
import br.com.dbccompany.entity.Estado;
import br.com.dbccompany.service.EstadoService;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author waldecleber
 *
 */
@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EstadoController {
	
	private final EstadoService estadoService;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> listarEstados() {		
		return ResponseEntity.ok().body(estadoService.listarEstados());
	}
	
	@CrossOrigin
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<EstadoDTO>> listarEstados(@PathVariable Long id) {		
		return ResponseEntity.ok().body(estadoService.buscarEstadoPorId(id));
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody EstadoDTO estado) {    	
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.salvar(estado));			
	}
	
}
