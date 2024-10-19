package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ItemPedidoDto;
import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  public List<ItemPedidoDto> obterTodosItensPedidos(){
   return itemPedidoRepository.findAll().stream().map(p -> ItemPedidoDto.toDto(p)).toList();
  }

  public Optional<ItemPedidoDto> obterItensPedidosPorId(Long id){
   if(!itemPedidoRepository.existsById(id)) {
    return Optional.empty();
    }
   return Optional.of(ItemPedidoDto.toDto(itemPedidoRepository.findById(id).get()));
  }

  public ItemPedidoDto salvarItemPedido(ItemPedidoDto dto) {
         ItemPedido itemPedidoEntity = dto.toEntity();
         itemPedidoEntity.setValorBruto(dto.calcularValorBruto());
         itemPedidoEntity.setValorDesconto(dto.calcularValorDesconto());
         itemPedidoEntity.setValorLiquido(dto.calcularValorLiquido());
         ItemPedido itemPedidoSalvo = itemPedidoRepository.save(itemPedidoEntity);
         return ItemPedidoDto.toDto(itemPedidoSalvo);
     }

  public boolean apagarItemPedido(Long id) {
   if(!itemPedidoRepository.existsById(id)) {
    return false;
   }
   itemPedidoRepository.deleteById(id);
   return true;
  }
  public Optional<ItemPedidoDto> alterarItemPedido(Long id, ItemPedidoDto itemPedidoDto){
   if(!itemPedidoRepository.existsById(id)) {
    return Optional.empty();
   }
   ItemPedido itemPedidoEntity = itemPedidoDto.toEntity();
   itemPedidoEntity.setId(id);
   itemPedidoRepository.save(itemPedidoEntity);
   return Optional.of(ItemPedidoDto.toDto(itemPedidoEntity));
  }
}