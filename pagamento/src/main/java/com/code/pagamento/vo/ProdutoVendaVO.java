package com.code.pagamento.vo;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.code.pagamento.entity.ProdutoVenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> {

	private Long id;
	private Long idProduto;
	private Integer quantidade;

	public static ProdutoVendaVO create(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
	}

}
