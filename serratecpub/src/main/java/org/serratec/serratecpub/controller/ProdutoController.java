package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ProdutoDto;
import org.serratec.serratecpub.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public List<ProdutoDto> obterTodosProdutos() {
		return produtoService.obterTodosProdutos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> obterProdutoPorId(@PathVariable Long id) {
		Optional<ProdutoDto> produtoDto = produtoService.obterProdutosPorId(id);
		if (!produtoDto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoDto.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
		return produtoService.salvarProduto(produtoDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarProduto(@PathVariable Long id, ProdutoDto produto) {
		if (!produtoService.apagarProduto(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		return ResponseEntity.ok("Produto " + produto.nome() + "excluído com sucesso!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
		Optional<ProdutoDto> produtoAlterado = produtoService.alterarProduto(id, produtoDto);
		if (!produtoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoAlterado.get());
	}
}
