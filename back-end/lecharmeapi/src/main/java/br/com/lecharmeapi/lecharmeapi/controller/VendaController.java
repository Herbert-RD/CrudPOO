package br.com.lecharmeapi.lecharmeapi.controller;

import br.com.lecharmeapi.lecharmeapi.dao.VendaDAO;
import br.com.lecharmeapi.lecharmeapi.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaDAO vendaDao;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public Venda createVenda(@RequestBody Venda venda) {
        if (venda.getDataVenda() == null) {
            venda.setDataVenda(new Date());
        }
        if (venda.getNomeCliente() == null || venda.getNomeCliente().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
        }
        if (venda.getTipoPagamento() == null || venda.getTipoPagamento().isEmpty()) {
            throw new IllegalArgumentException("Tipo de pagamento não pode ser nulo ou vazio");
        }
        return vendaDao.save(venda);
    }

    @PutMapping("/{id}")
    public Venda updateVenda(@PathVariable Long id, @RequestBody Venda vendaDetails) {
        Optional<Venda> optionalVenda = vendaDao.findById(id);
        if (optionalVenda.isPresent()) {
            Venda venda = optionalVenda.get();
            venda.setProdutoId(vendaDetails.getProdutoId());
            venda.setNomeCliente(vendaDetails.getNomeCliente());
            venda.setTipoPagamento(vendaDetails.getTipoPagamento());
            venda.setDataVenda(vendaDetails.getDataVenda());
            return vendaDao.save(venda);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteVenda(@PathVariable Long id) {
        vendaDao.deleteById(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public List<Venda> getAllVendas() {
        return vendaDao.findAll();
    }

    @GetMapping("/{id}")
    public Venda getVendaById(@PathVariable Long id) {
        return vendaDao.findById(id).orElse(null);
    }
}
