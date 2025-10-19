package com.projeto_api.demo.repository;
import com.projeto_api.demo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//NOME: Giovanni Samuel
//RU: 3608113

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // O Spring Data JPA cria os métodos find, save, delete, etc. automaticamente.
}
