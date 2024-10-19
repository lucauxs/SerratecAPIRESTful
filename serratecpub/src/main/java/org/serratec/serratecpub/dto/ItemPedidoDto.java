package org.serratec.serratecpub.dto;

import java.util.List;

import org.serratec.serratecpub.model.ItemPedido;

public record ItemPedidoDto(
    Long id,
    int quantidade,
    Double precoVenda,
    int percentualDesconto,
    Double valorBruto,
    Double valorLiquido,
    Double valorDesconto,
    PedidoDto pedido,
    List<ProdutoDto> produto
) {

    public ItemPedido toEntity() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(this.id);
        itemPedido.setQuantidade(this.quantidade);
        itemPedido.setPrecoVenda(this.precoVenda);
        itemPedido.setPercentualDesconto(this.percentualDesconto);
        itemPedido.setPedido(this.pedido.toEntity());
        itemPedido.setProduto(this.produto.stream().map(ProdutoDto::toEntity).toList());
        itemPedido.calcularValores();
        return itemPedido;
    }

    public static ItemPedidoDto toDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(
            itemPedido.getId(),
            itemPedido.getQuantidade(),
            itemPedido.getPrecoVenda(),
            itemPedido.getPercentualDesconto(),
            itemPedido.getValorBruto(),
            itemPedido.getValorLiquido(),
            itemPedido.getValorDesconto(),
            PedidoDto.toDto(itemPedido.getPedido()),
            itemPedido.getProduto().stream().map(ProdutoDto::toDto).toList()
        );
    }

    public Double calcularValorBruto() {
        return this.precoVenda * this.quantidade;
    }

    public Double calcularValorDesconto() {
        return calcularValorBruto() * (this.percentualDesconto / 100.0);
    }

    public Double calcularValorLiquido() {
        return calcularValorBruto() - calcularValorDesconto();
    }
}