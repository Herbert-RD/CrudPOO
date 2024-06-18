package br.com.lecharmeapi.lecharmeapi.dao;

import br.com.lecharmeapi.lecharmeapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}

