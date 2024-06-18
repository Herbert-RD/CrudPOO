package br.com.lecharmeapi.lecharmeapi.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int produtoId;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String tipoPagamento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda;

    public Venda() {
        this.dataVenda = new Date(); // Inicializa com a data atual
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
}
