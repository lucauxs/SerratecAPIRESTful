package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ClienteDto;
import org.serratec.serratecpub.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired

    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDto> listarClientes(){
        return clienteService.obterTodosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> listarClientePorId(@PathVariable Long id){
        Optional<ClienteDto> dto = clienteService.obterClientePorId(id);
        if(dto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto cadastrarCliente(@RequestBody ClienteDto clienteDto){
        return clienteService.salvarCliente(clienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable Long id, ClienteDto cliente){
        if(!clienteService.excluirCliente(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.ok("Cliente "+ cliente.nome() +"com id" + id + " excluído com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id,
    @RequestBody ClienteDto clienteDto){
        Optional<ClienteDto> clienteAlterado = clienteService.alterarCliente(id, clienteDto);
        if (!clienteAlterado.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAlterado.get());

    }

    
}
