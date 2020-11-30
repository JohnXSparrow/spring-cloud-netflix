package com.code.crud.vo;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.code.crud.entity.Produto;

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
	private String nome;
	private Integer estoque;
	private Double preco;

	public static ProdutoVO create(Produto produto) {
		return new ModelMapper().map(produto, ProdutoVO.class);
	}

}
