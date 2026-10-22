package com.estoque.peixaria.service;

import com.estoque.peixaria.model.MovimentacaoDiaria;
import com.estoque.peixaria.model.RelatorioMensal;
import com.estoque.peixaria.repository.MovimentacaoDiariaRepository;
import com.estoque.peixaria.repository.RelatorioMensalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MovimentacaoDiariaService {

    private final MovimentacaoDiariaRepository repository;
    private final RelatorioMensalRepository relatorioMensalRepository;

    public MovimentacaoDiariaService(MovimentacaoDiariaRepository repository, RelatorioMensalRepository relatorioMensalRepository) {
        this.repository = repository;
        this.relatorioMensalRepository = relatorioMensalRepository;
    }

    public List<MovimentacaoDiaria> listarTodas() {
        return repository.findAll();
    }

    public MovimentacaoDiaria salvar(MovimentacaoDiaria novaMov) {
        // Se o usuário selecionou uma data no input, mantemos ela. Se vier vazia, coloca hoje.
        if (novaMov.getData() == null) {
            novaMov.setData(LocalDate.now());
        }

        // Mantém a dataHora com o momento exato em que o registro foi gerado
        if (novaMov.getDataHora() == null) {
            novaMov.setDataHora(LocalDateTime.now());
        }

        // Busca direto no banco o último registro daquele produto
        MovimentacaoDiaria ultimo = repository.findUltimaMovimentacaoPorProduto(novaMov.getProduto());
        
        double estoqueAnterior = 0.0;
        if (ultimo != null && ultimo.getEstoqueFimDoDia() != null) {
            estoqueAnterior = ultimo.getEstoqueFimDoDia();

            // VERIFICAÇÃO DE VIRADA DE MÊS:
            // Se o mês/ano do novo lançamento for diferente do mês/ano do último registro gravado...
            LocalDate novaData = novaMov.getData();
            LocalDate ultimaData = ultimo.getData() != null ? ultimo.getData() : novaData;

            if (novaData.getYear() > ultimaData.getYear() || 
                (novaData.getYear() == ultimaData.getYear() && novaData.getMonthValue() > ultimaData.getMonthValue())) {
                
                // O mês virou! Salvamos o fechamento do mês anterior na tabela de relatórios mensais
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
                String mesAnoAnterior = ultimaData.format(formatter);

                RelatorioMensal fechamentoMensal = new RelatorioMensal(
                    mesAnoAnterior,
                    ultimo.getProduto(),
                    0.0,
                    0.0,
                    ultimo.getEstoqueFimDoDia(),
                    ultimaData
                );
                relatorioMensalRepository.save(fechamentoMensal);
            }
        }

        novaMov.setEstoqueInicial(estoqueAnterior);

        double entrada = novaMov.getEstoqueEntrada() != null ? novaMov.getEstoqueEntrada() : 0.0;
        double filialCentro = novaMov.getSaidaFilialCentro() != null ? novaMov.getSaidaFilialCentro() : 0.0;
        double filialNorte = novaMov.getSaidaFilialNorte() != null ? novaMov.getSaidaFilialNorte() : 0.0;
        double filialSul = novaMov.getSaidaFilialSul() != null ? novaMov.getSaidaFilialSul() : 0.0;

        double estoqueFim = estoqueAnterior + entrada - (filialCentro + filialNorte + filialSul);
        novaMov.setEstoqueFimDoDia(estoqueFim);

        return repository.save(novaMov);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
