package br.com.dbccompany.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.dbccompany.dto.CidadeDTO.CidadeDTOBuilder;
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
public class CotacaoDollarDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal high;

}
