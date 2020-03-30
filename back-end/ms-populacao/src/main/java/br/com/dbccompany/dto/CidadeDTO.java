package br.com.dbccompany.dto;

import br.com.dbccompany.entity.Estado;
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
public class CidadeDTO {

	private Long id;

	private String nome;

	private EstadoDTO estado;

	private Integer populacao;

}
