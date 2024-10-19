package org.serratec.serratecpub.model;

import java.time.LocalDate;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 3, max = 50, message = TratamentoDeErro.SizeMessage)
	private String nome;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Enumerated(EnumType.STRING)
	private CategoriaNome categoria;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(max = 100, message = TratamentoDeErro.SizeMessage)
	private String descricao;
	@Positive
	private int qtdEstoque;
	private LocalDate dataCadastro = LocalDate.now();//data de cadastro vai automaticamente
	@Positive
	private Double valorUnitario;
	@Size(min = 1, max = 500, message = TratamentoDeErro.SizeMessage)
	private String imagem;
	
	
	@ManyToOne
	private ItemPedido ItemPedido;
	
	public ItemPedido getItemPedido() {
		return ItemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		ItemPedido = itemPedido;
	}
	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public String getImagem() {
		return imagem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQtdEstoque(int qntEstoque) {
		this.qtdEstoque = qntEstoque;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
