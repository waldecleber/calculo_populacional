package br.com.dbccompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.dbccompany.entity.Estado;

@RepositoryRestResource(collectionResourceRel = "estado", path = "estado")
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("SELECT e FROM Estado e " +
			" LEFT JOIN FETCH e.cidades cidade ")
	List<Estado> listarEstados();

	@Query("SELECT e FROM Estado e " +
			" LEFT JOIN FETCH e.cidades cidade " +
			"WHERE e.uf = :uf")
	Estado buscarEstadoPorUf(String uf);

}
