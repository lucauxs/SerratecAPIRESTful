package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.Produto;

public record ProdutoDto(
		Long id,
		String nome,
		String descricao,
		LocalDate dataCadastro,
		int qntEstoque,
		Double valorUnitario,
		String imagem
		) {
		
		public Produto toEntity() {
			Produto produto = new Produto();
			produto.setId(this.id);
			produto.setNome(this.nome);
			produto.setDescricao(this.descricao);
			produto.setDataCadastro(this.dataCadastro);
			produto.setQtdEstoque(this.qntEstoque);
			produto.setValorUnitario(this.valorUnitario);
			produto.setImagem(this.imagem);
			return produto;
		}
			
		public static ProdutoDto toDto(Produto produto) {
			return new ProdutoDto(produto.getId(), produto.getNome(), produto.getDescricao(),produto.getDataCadastro(), produto.getQtdEstoque(),
					produto.getValorUnitario(), produto.getImagem());
		}
}
