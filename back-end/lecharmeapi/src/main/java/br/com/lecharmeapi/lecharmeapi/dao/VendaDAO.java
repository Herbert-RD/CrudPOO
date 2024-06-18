package br.com.lecharmeapi.lecharmeapi.dao;

import br.com.lecharmeapi.lecharmeapi.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaDAO extends JpaRepository<Venda, Long> {
}
