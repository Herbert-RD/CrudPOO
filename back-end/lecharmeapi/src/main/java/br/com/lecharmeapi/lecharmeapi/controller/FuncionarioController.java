package br.com.lecharmeapi.lecharmeapi.controller;

import br.com.lecharmeapi.lecharmeapi.dao.FuncionarioDAO;
import br.com.lecharmeapi.lecharmeapi.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioDAO dao;

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        if (funcionario.getDataContratacao() == null) {
            funcionario.setDataContratacao(new Date());
        }
        return dao.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) {
        Optional<Funcionario> optionalFuncionario = dao.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionario.setNome(funcionarioDetails.getNome());
            funcionario.setDataContratacao(funcionarioDetails.getDataContratacao());
            return dao.save(funcionario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Funcionario getFuncionarioById(@PathVariable Long id) {
        return dao.findById(id).orElse(null);
    }
}
