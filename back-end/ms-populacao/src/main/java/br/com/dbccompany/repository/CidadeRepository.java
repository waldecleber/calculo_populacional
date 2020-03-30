package br.com.dbccompany.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.dbccompany.entity.Cidade;
import br.com.dbccompany.entity.Estado;

@RepositoryRestResource(collectionResourceRel = "cidade", path = "cidade")
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	@Query("SELECT c FROM Cidade c " + 
			" JOIN FETCH c.estado e " +
			"WHERE c.estado.uf = :uf")
	List<Cidade> listarCidadePorEstado(String uf);
	
	@Query("SELECT c FROM Cidade c " + 
			" JOIN FETCH c.estado e " +
			"WHERE c.estado.uf = :uf " +
			"AND c.nome = :cidade")
	Optional<Cidade> buscarCidadePorNomeEPorEstado(String cidade, String uf);

	@Query("SELECT c FROM Cidade c " + 
			" JOIN FETCH c.estado e " )
	List<Cidade> listarTodasCidades();

}
