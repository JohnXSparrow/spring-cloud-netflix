package com.code.pagamento.vo;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.code.pagamento.entity.Venda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaVO extends RepresentationModel<VendaVO> {

	private Long id;
	private Date data;
	private List<ProdutoVendaVO> produtos;
	private Double valorTotal;

	public static VendaVO create(Venda venda) {
		return new ModelMapper().map(venda, VendaVO.class);
	}

}
