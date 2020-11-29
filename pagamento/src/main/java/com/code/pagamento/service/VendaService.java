package com.code.pagamento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.code.pagamento.entity.ProdutoVenda;
import com.code.pagamento.entity.Venda;
import com.code.pagamento.exception.ResourceNotFoundException;
import com.code.pagamento.repository.ProdutoVendaRepository;
import com.code.pagamento.repository.VendaRepository;
import com.code.pagamento.vo.VendaVO;

@Service
public class VendaService {

	private VendaRepository vendaRepository;
	private ProdutoVendaRepository produtoVendaRepository;

	public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}
	
	public VendaVO save(VendaVO vendaVO) {
		
		Venda venda = vendaRepository.save(Venda.create(vendaVO));		
		List<ProdutoVenda> produtosSalvo = new ArrayList<ProdutoVenda>();
		
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvo.add(produtoVendaRepository.save(pv));
		});
		
		venda.setProdutos(produtosSalvo);
		return VendaVO.create(venda);
	}

	public Page<VendaVO> findAll(Pageable pageable) {
		return vendaRepository.findAll(pageable).map(this::convertToVendaVO);
	}
	
	public VendaVO findById(Long id) {
		return VendaVO.create(vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Venda não encontrado com id: " + id)));
	}
	
	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}

}
