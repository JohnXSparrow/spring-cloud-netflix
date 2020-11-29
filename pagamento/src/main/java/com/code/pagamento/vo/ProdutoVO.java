package com.code.pagamento.vo;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.code.pagamento.entity.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVO extends RepresentationModel<ProdutoVO> {

	private Long id;
	private Integer estoque;
	
	public static ProdutoVO create(Produto produto) {
		return new ModelMapper().map(produto, ProdutoVO.class);
	}

}