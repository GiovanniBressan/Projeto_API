package com.projeto_api.demo.service;
import com.projeto_api.demo.model.Tarefa;
import com.projeto_api.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//NOME: Giovanni Samuel
//RU: 3608113

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    // 1. CREATE
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // 2. READ ALL
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    // 3. READ ONE
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    // 4. UPDATE
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaDetalhes) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

        if (tarefaOptional.isPresent()) {
            Tarefa tarefaExistente = tarefaOptional.get();
            
            // Atualiza apenas os campos que o usuário enviou
            tarefaExistente.setNome(tarefaDetalhes.getNome());
            tarefaExistente.setDataEntrega(tarefaDetalhes.getDataEntrega());
            tarefaExistente.setResponsavel(tarefaDetalhes.getResponsavel());
            
            return tarefaRepository.save(tarefaExistente);
        } else {
            return null; // Retorna null se não encontrar a tarefa
        }
    }

    // 5. DELETE
    public boolean deletarTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}