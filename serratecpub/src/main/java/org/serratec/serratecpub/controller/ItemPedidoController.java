package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ItemPedidoDto;
import org.serratec.serratecpub.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController {
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	public List<ItemPedidoDto> obterTodosItensPedidos() {
		return itemPedidoService.obterTodosItensPedidos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoDto> obterItensPedidosPorId(@PathVariable Long id) {
		Optional<ItemPedidoDto> itemPedidoDto = itemPedidoService.obterItensPedidosPorId(id);
		if (!itemPedidoDto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedidoDto.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemPedidoDto cadastrarItemPedido(@RequestBody ItemPedidoDto dto) {
		return itemPedidoService.salvarItemPedido(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarItemPedido(@PathVariable Long id) {
		if (!itemPedidoService.apagarItemPedido(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item pedido não encontrado!");
		}
		return ResponseEntity.ok("Item pedido " + id  + " excluído com sucesso!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedidoDto> alterarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDto itemPedidoDto) {
		Optional<ItemPedidoDto> itemPedidoAlterado = itemPedidoService.alterarItemPedido(id, itemPedidoDto);
		if (!itemPedidoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedidoAlterado.get());
	}
}
