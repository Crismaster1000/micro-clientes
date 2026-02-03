package com.stephano.microclientes.controller;

import com.stephano.microclientes.entity.Cliente;
import com.stephano.microclientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public Cliente create(@Valid @RequestBody Cliente cliente) {
        return service.create(cliente);
    }

    @GetMapping
    public List<Cliente> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-cliente-id/{clienteId}")
    public Cliente getByClienteId(@PathVariable String clienteId) {
        return service.getByClienteId(clienteId);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return service.update(id, cliente);
    }

    @PatchMapping("/{id}")
    public Cliente patch(@PathVariable Long id, @RequestBody Cliente partial) {
        return service.patch(id, partial);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
