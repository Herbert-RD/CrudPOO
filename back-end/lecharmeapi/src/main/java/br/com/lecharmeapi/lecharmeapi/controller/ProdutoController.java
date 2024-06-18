package br.com.lecharmeapi.lecharmeapi.controller;

import br.com.lecharmeapi.lecharmeapi.dao.ProdutoDAO;
import br.com.lecharmeapi.lecharmeapi.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoDAO dao;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        if (produto.getDataCriacao() == null) {
            produto.setDataCriacao(new Date());
        }
        return dao.save(produto);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        Optional<Produto> optionalProduto = dao.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(produtoDetails.getNome());
            produto.setCor(produtoDetails.getCor());
            produto.setTamanho(produtoDetails.getTamanho());
            produto.setPreco(produtoDetails.getPreco());
            produto.setDataCriacao(produtoDetails.getDataCriacao());
            return dao.save(produto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        dao.deleteById(id);
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public List<Produto> getAllProdutos() {
        return dao.findAll();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) {
        return dao.findById(id).orElse(null);
    }
}

