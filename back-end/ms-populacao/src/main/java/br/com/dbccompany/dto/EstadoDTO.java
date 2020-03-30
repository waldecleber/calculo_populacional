package br.com.dbccompany.dto;

import java.math.BigDecimal;
import java.util.Set;

import br.com.dbccompany.entity.Cidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {

	private Long id;

	private String nome;

	private String uf;
	
	private String bandeira;
	
	private Integer populacao;
	
	private BigDecimal custoPopulacional;

}
