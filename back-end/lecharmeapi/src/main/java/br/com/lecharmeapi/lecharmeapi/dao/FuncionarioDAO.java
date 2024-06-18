package br.com.lecharmeapi.lecharmeapi.dao;

import br.com.lecharmeapi.lecharmeapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario, Long> {
}
