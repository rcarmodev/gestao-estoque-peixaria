package com.estoque.peixaria.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_diaria")
public class MovimentacaoDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo de data escolhida no formulário
    private LocalDate data;

    private LocalDateTime dataHora;

    private String produto;
    private Double estoqueInicial;
    private Double estoqueEntrada;
    private Double saidaFilialCentro;
    private Double saidaFilialNorte;
    private Double saidaFilialSul;
    private Double estoqueFimDoDia;
    private String responsavel;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public Double getEstoqueInicial() { return estoqueInicial; }
    public void setEstoqueInicial(Double estoqueInicial) { this.estoqueInicial = estoqueInicial; }

    public Double getEstoqueEntrada() { return estoqueEntrada; }
    public void setEstoqueEntrada(Double estoqueEntrada) { this.estoqueEntrada = estoqueEntrada; }

    public Double getSaidaFilialCentro() { return saidaFilialCentro; }
    public void setSaidaFilialCentro(Double saidaFilialCentro) { this.saidaFilialCentro = saidaFilialCentro; }

    public Double getSaidaFilialNorte() { return saidaFilialNorte; }
    public void setSaidaFilialNorte(Double saidaFilialNorte) { this.saidaFilialNorte = saidaFilialNorte; }

    public Double getSaidaFilialSul() { return saidaFilialSul; }
    public void setSaidaFilialSul(Double saidaFilialSul) { this.saidaFilialSul = saidaFilialSul; }

    public Double getEstoqueFimDoDia() { return estoqueFimDoDia; }
    public void setEstoqueFimDoDia(Double estoqueFimDoDia) { this.estoqueFimDoDia = estoqueFimDoDia; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}
