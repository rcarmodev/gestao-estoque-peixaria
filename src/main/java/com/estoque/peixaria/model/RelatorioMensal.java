package com.estoque.peixaria.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "relatorio_mensal")
public class RelatorioMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identifica o mês e ano do fechamento, ex: "09/2026"
    private String mesAno; 

    private String produto;
    private Double totalEntradas;
    private Double totalSaidas;
    private Double estoqueFinalFechamento;
    private LocalDate dataFechamento;

    // Construtores
    public RelatorioMensal() {
    }

    public RelatorioMensal(String mesAno, String produto, Double totalEntradas, Double totalSaidas, Double estoqueFinalFechamento, LocalDate dataFechamento) {
        this.mesAno = mesAno;
        this.produto = produto;
        this.totalEntradas = totalEntradas;
        this.totalSaidas = totalSaidas;
        this.estoqueFinalFechamento = estoqueFinalFechamento;
        this.dataFechamento = dataFechamento;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMesAno() { return mesAno; }
    public void setMesAno(String mesAno) { this.mesAno = mesAno; }

    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public Double getTotalEntradas() { return totalEntradas; }
    public void setTotalEntradas(Double totalEntradas) { this.totalEntradas = totalEntradas; }

    public Double getTotalSaidas() { return totalSaidas; }
    public void setTotalSaidas(Double totalSaidas) { this.totalSaidas = totalSaidas; }

    public Double getEstoqueFinalFechamento() { return estoqueFinalFechamento; }
    public void setEstoqueFinalFechamento(Double estoqueFinalFechamento) { this.estoqueFinalFechamento = estoqueFinalFechamento; }

    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }
}