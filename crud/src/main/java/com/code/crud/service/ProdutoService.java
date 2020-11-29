package com.code.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.code.crud.entity.Produto;
import com.code.crud.exception.ResourceNotFoundException;
import com.code.crud.repository.ProdutoRepository;
import com.code.crud.vo.ProdutoVO;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public ProdutoVO save(ProdutoVO produtoVO) {
		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
	}

	public Page<ProdutoVO> findAll(Pageable pageable) {
		return produtoRepository.findAll(pageable).map(this::convertToProdutoVO);
	}

	public ProdutoVO findById(Long id) {
		return ProdutoVO.create(produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id)));
	}

	public ProdutoVO update(ProdutoVO produtoVO) {
		if (produtoVO.getId() == null) throw new ResourceNotFoundException("Id não foi informado.");
		produtoRepository.findById(produtoVO.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Produto não encontrado com id: " + produtoVO.getId()));

		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
	}

	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
		produtoRepository.delete(entity);
	}

	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}

}
