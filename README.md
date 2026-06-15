# Ágape - Backend 🚀

O **Ágape-BackEnd** é uma API REST robusta desenvolvida em Java e Spring Boot, projetada para centralizar a inteligência de negócios e o gerenciamento analítico de frotas de veículos. O sistema atua como o motor de dados para o dashboard adaptável do ecossistema, fornecendo endpoints de alta performance e geração automatizada de relatórios executivos em PDF.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17 
* **Framework Principal:** Spring Boot 3.x
* **Segurança:** Spring Security (com suporte a CORS integrado)
* **Gerador de Relatórios:** JasperReports (Compilação dinâmica de layouts `.jrxml`)
* **Gerenciador de Dependências:** Maven
* **Padrão de Arquitetura:** RESTful com desacoplamento via DTOs (Data Transfer Objects)

---

## 📦 Estrutura e Funcionalidades do Painel Analítico

A API centraliza as regras de negócio para análise de frotas através do `PainelController` (`/api/painel`), entregando dados consolidados e performáticos estruturados de acordo com filtros temporais e específicos por veículo.

### Endpoints Disponíveis

| Verbo | Endpoint | Parâmetros de Filtro (Query) | Descrição |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/painel/veiculos` | Nenhum | Retorna uma lista simplificada de veículos para popular componentes de filtro no front-end. |
| **GET** | `/api/painel/indicadores` | `veiculoId`, `dataInicio`, `dataFim` | Retorna o resumo geral de KPIs da frota (Custo médio por KM, KM total e total de viagens). |
| **GET** | `/api/painel/consumo-combustivel`| `veiculoId`, `dataInicio`, `dataFim` | Retorna o histórico e evolução mensal do consumo de combustível. |
| **GET** | `/api/painel/quilometragem` | `veiculoId`, `dataInicio`, `dataFim` | Retorna os dados agregados de rodagem e quilometragem da frota. |
| **GET** | `/api/painel/status-veiculo` | `veiculoId`, `dataInicio`, `dataFim` | Retorna a divisão de status atual da frota (Operando, Manutenção, Parados). |
| **GET** | `/api/painel/postos-melhor-preco` | `veiculoId`, `dataInicio`, `dataFim` | Retorna o ranking analítico dos postos de combustível com melhores preços praticados. |
| **GET** | `/api/painel/dashboard/pdf` | `veiculoId`, `dataInicio`, `dataFim` | Compila os dados da tela em tempo real e retorna um arquivo PDF para download. |

---

## 📊 Integração com JasperReports (Geração de PDFs)

O sistema possui um motor integrado de relatórios nativos que elimina a necessidade de ferramentas terceiras de renderização no lado do cliente.

* **Localização do Layout:** Os arquivos de design visual estruturados no Jaspersoft Studio devem ser inseridos em:  
    `src/main/resources/reports/relatorio_principal.jrxml`
* **Mecanismo de Download:** A API compila o arquivo `.jrxml` em tempo real através de uma `ClassPathResource` do Spring, injeta os dados reais processados pelo banco via parâmetros e faz o stream dos bytes com os headers `APPLICATION_PDF` e `Content-Disposition: attachment`.

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
* Java 17 ou superior instalado.
* Maven 3.x configurado.

### Passos para Inicialização

1. Clone o repositório em sua máquina:
   ```bash
   git clone [https://github.com/seu-usuario/agape-backend.git](https://github.com/seu-usuario/agape-backend.git)
