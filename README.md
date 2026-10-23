# 🐟 Sistema de Gestão de Estoque & Balanço Comercial Multi-Lojas

> **API REST e Dashboard Operacional desenvolvidos em Java 17 e Spring Boot para controle diário de insumos perecíveis de alto valor, rastreabilidade de distribuição entre filiais e fechamento automatizado de inventário.**

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-003B57?style=for-the-badge&logo=h2&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

---

## 🎯 1. O Problema de Negócio & Oportunidade Real

Em operações de gastronomia de alto giro (como restaurantes japoneses, peixarias e dark kitchens), proteínas nobres como salmão, atum e frutos do mar representam a maior fatia do **Custo de Mercadoria Vendida (CMV)**. 

Os principais gargalos operacionais identificados em campo foram:
* **Falta de conciliação de peso na entrada:** Divergências entre o peso faturado na nota fiscal e a pesagem real recebida na balança de recebimento.
* **Quebras por degelo e manipulação:** Perda de peso invisível no descongelamento e limpeza de pescados que corrói a margem sem registro de inventário.
* **Fricção de distribuição multi-lojas:** Descentralização no controle de remessas enviadas para diferentes filiais e pontos de delivery.
* **Erros de digitação por equipes de chão de loja:** Softwares tradicionais com formulários longos e complexos acabam sendo abandonados por cozinheiros e estoquistas que trabalham em ambientes dinâmicos e com luvas.

Este sistema foi concebido para entregar **controle rigoroso de dados com interface de baixíssima fricção operacional**.

---

## 🏗️ 2. Arquitetura & Tecnologias

A aplicação foi estruturada seguindo o padrão em camadas do ecossistema Spring:

```
src/main/java/com/estoque/peixaria/
├── controller/          # Endpoints REST para movimentações e relatórios
├── model/               # Entidades JPA mapeadas para o banco de dados
├── repository/          # Interfaces Spring Data JPA para persistência
├── service/             # Regras de negócio, cálculos e lógica de virada de mês
└── PeixariaApplication  # Classe de inicialização Spring Boot
```

* **Linguagem:** Java 17 (LTS).
* **Framework:** Spring Boot 3.x (WebMVC, Data JPA).
* **Persistência:** H2 Database em modo arquivo local (`AUTO_SERVER=TRUE`), garantindo retenção de dados entre reinicializações sem dependências externas complexas para prototipagem rápida.
* **Frontend Integrado:** Dashboard Single Page Application (SPA) em Vanilla HTML5, CSS3 com variáveis dinâmicas e JavaScript nativo, incluindo visualização tabular e extrato diário consolidado.

### 📡 Endpoints da API REST

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/movimentacoes` | Lista o histórico consolidado de movimentações e saldo atual |
| `POST` | `/movimentacoes` | Registra entrada/saídas e recalcula o estoque final automaticamente |
| `DELETE` | `/movimentacoes/{id}` | Exclui um registro específico de movimentação por ID |
| `GET` | `/api/relatorios-mensais` | Retorna os snapshots consolidados de fechamento mensal |

---

## ⚙️ 3. Regras de Negócio Implementadas

### A) Cálculo Dinâmico de Balanço Diário
A cada novo lançamento, o serviço consulta o último registro gravado para aquele insumo e aplica a fórmula de integridade de estoque:

$$\text{Estoque Final} = \text{Estoque Inicial} + \text{Entradas} - \left( \sum \text{Saídas das Filiais} \right)$$

O cálculo considera saídas segmentadas para 3 unidades operacionais distintas (*Filial Centro, Filial Norte e Filial Sul*).

### B) Snapshot Automatizado de Fechamento Mensal
No momento do lançamento diário, a camada de serviço (`MovimentacaoDiariaService`) verifica se a data informada ultrapassou o mês/ano da última movimentação gravada. Em caso de virada de competência:
1. O sistema consolida automaticamente o saldo do mês anterior.
2. Salva um snapshot histórico na entidade `RelatorioMensal`.
3. Inicia o novo ciclo mensal com o saldo inicial rigorosamente sincronizado.

---

## 💡 4. Decisões de Design de Produto (Chão de Operação)

* **Autonomia e Agilidade:** Menus e formulários desenhados para preenchimento em menos de 10 segundos no celular ou tablet de frente de estoque.
* **Roadmap de Auditoria Visual por Foto:** Planejamento arquitetural para substituir conferências manuais de notas fiscais por upload de foto com data/hora da caixa sobre a balança de recebimento, criando comprovação imediata contra divergências de fornecedores sem onerar a rotina da cozinha.

---

## 🗺️ 5. Roadmap de Evolução Técnica

- [ ] **Conteinerização com Docker:** Criação de `docker-compose.yml` para orquestração da aplicação e migração do H2 para **PostgreSQL**.
- [ ] **Camada de DTOs & Bean Validation:** Refatoração de endpoints para utilização de DTOs (`MovimentacaoRequestDTO`) com anotações de validação (`@Valid`, `@Positive`, `@NotNull`).
- [ ] **Dinamização de Lojas:** Modelagem de relacionamento relacional `@ManyToOne` com entidade `Loja` para permitir escalabilidade infinita de filiais.
- [ ] **Testes Automatizados:** Implementação de suíte de testes unitários e de integração com **JUnit 5** e **Mockito**.
- [ ] **Pipeline de CI/CD:** Automação de builds via GitHub Actions.

---

## 🚀 6. Como Executar Localmente

### Pré-requisitos
* **Java Development Kit (JDK) 17** instalado e configurado no `PATH`.
* Git instalado.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/rcarmodev/gestao-estoque-peixaria.git
   cd gestao-estoque-peixaria
   ```

2. **Execute a aplicação via Maven Wrapper:**
   * **Windows:**
     ```bash
     mvnw.cmd spring-boot:run
     ```
   * **Linux / Mac:**
     ```bash
     ./mvnw spring-boot:run
     ```

3. **Acesse no navegador:**
   * **Dashboard Operacional:** [http://localhost:8081](http://localhost:8081)
   * **Console H2 Database:** [http://localhost:8081/h2-console](http://localhost:8081/h2-console)  
     *(JDBC URL: `jdbc:h2:file:./data/peixaria_db` | User: `sa` | Password: em branco)*

---

## 📄 Licença & Propriedade Intelectual

Projeto desenvolvido para fins educacionais, avaliação técnica e demonstração de portfólio. Todos os direitos reservados a **Rafael Carmo (`rcarmodev`)**.
