package com.projeto_api.demo.controller;

import com.projeto_api.demo.model.Tarefa;
import com.projeto_api.demo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//NOME: Giovanni Samuel
//RU: 3608113

@RestController
@RequestMapping("/api/tarefas") 
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    // 1. Criar Tarefa (POST)
    // URL: POST http://localhost:8080/api/tarefas
    @PostMapping("/")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        // Retorna 201 Created
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    // 2. Consultar todas as Tarefas (GET)
    // URL: GET http://localhost:8080/api/tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTodas() {
        List<Tarefa> tarefas = tarefaService.listarTodas();
        // Retorna 200 OK
        return ResponseEntity.ok(tarefas);
    }

    // 3. Consultar uma Tarefa pelo ID (GET)
    // URL: GET http://localhost:8080/api/tarefas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                     .map(ResponseEntity::ok) 
                     .orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetalhes) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefaDetalhes);
        
        if (tarefaAtualizada != null) {
            return ResponseEntity.ok(tarefaAtualizada); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        boolean deletado = tarefaService.deletarTarefa(id);
        
        if (deletado) {
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
