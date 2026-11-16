# Documentação da Arquitetura: Sistema de Help Desk

Este documento detalha a estrutura, os relacionamentos e os conceitos de Programação Orientada a Objetos (POO) aplicados no diagrama de classes do sistema de **Help Desk**.

---

## 1. Visão Geral da Arquitetura

O sistema foi projetado utilizando uma arquitetura orientada a objetos bem definida, separando responsabilidades entre **interface de usuário (console)**, **regras de negócio (núcleo)**, **persistência de dados** e **modelo de domínio**.

---

## 2. Detalhamento das Classes por Camada

### 🖥️ Camada de Apresentação / Entrada
* **`Main`**: É o ponto de entrada da aplicação (`public static void main`). Responsável pela interação direta com o usuário via terminal (`Scanner`), exibição de menus, leitura de dados e delegação das operações para o controlador do sistema.

### ⚙️ Camada de Controle e Regras de Negócio
* **`SistemaChamados`**: Atua como o coração (fachada/controlador) do sistema. Mantém em memória as listas de clientes, atendentes e chamados, centralizando operações como cadastros, buscas, atribuições, fechamentos e listagens filtradas (por prioridade ou atraso).

### 💾 Camada de Persistência
* **`ArquivoManager`**: Gerencia a leitura e gravação dos dados do sistema em arquivos no formato CSV (`arquivoClientes`, `arquivoAtendentes`, `arquivoChamados`), garantindo que o estado do sistema seja preservado entre execuções.

---

## 3. Modelo de Domínio (Hierarquias e Polimorfismo)

### 👥 Hierarquia de Pessoas
* **`Pessoa` (Abstrata)**: Define a estrutura base para qualquer usuário do sistema, controlando a geração automática de identificadores (`proximoId`) e atributos comuns (`id`, `nome`, `email`). Exige que subclasses implementem `getTipo()`.
* **`Cliente`**: Herda de `Pessoa` e adiciona o atributo específico `setor`. Implementa a interface `Notificavel`.
* **`Atendente`**: Herda de `Pessoa` e adiciona o atributo específico `especialidade`. Também implementa `Notificavel`.
* **`Notificavel` (Interface)**: Define o contrato `enviarNotificacao(mensagem : String)`, permitindo que tanto clientes quanto atendentes recebam avisos de forma polimórfica.

### 🎫 Hierarquia de Chamados
* **`Chamado` (Abstrata)**: Representa a base de um ticket de suporte. Contém informações como `idChamado`, `titulo`, `descricao`, `dataAbertura`, o `status` atual, o `cliente` solicitante e o `atendente` responsável (opcional no momento da abertura).
  * Implementa `Comparable<Chamado>` para permitir a ordenação natural (usada, por exemplo, na listagem por prioridade).
  * Define métodos abstratos como `calcularPrazoHoras()` e `getCategoria()`, delegando às subclasses as regras específicas de SLA (acordo de nível de serviço).
* **`ChamadoHardware`**: Especialização para problemas físicos, adicionando o atributo `equipamento`.
* **`ChamadoSoftware`**: Especialização para problemas em aplicações, adicionando `sistema` e `versao`.
* **`ChamadoRede`**: Especialização para conectividade, adicionando `enderecoIp`.
* **`StatusChamado` (Enum)**: Define os estados possíveis no ciclo de vida de um chamado: `ABERTO`, `EM_ATENDIMENTO` e `FECHADO`.

---

## 4. Tratamento de Exceções

O diagrama diferencia claramente exceções verificadas (*Checked*) de não verificadas (*Unchecked*):

* **`ChamadoNaoEncontradoException`**: Herda de `RuntimeException` (*Unchecked*). Lançada pela classe `SistemaChamados` quando uma busca por ID não retorna resultados.
* **`StatusInvalidoException`**: Herda de `Exception` (*Checked*). Lançada pela classe `Chamado` ao tentar realizar uma transição de estado proibida (por exemplo, fechar um chamado que já está fechado ou atribuir atendente de forma inválida), obrigando o tratamento explícito no fluxo.

---

## 5. Resumo dos Relacionamentos UML

| Relacionamento | Símbolo no Diagrama | Significado no Sistema |
| :--- | :---: | :--- |
| **Herança (Generalização)** | `<\|--` | `Cliente` e `Atendente` estendem `Pessoa`. As categorias de chamado estendem `Chamado`. |
| **Implementação (Realização)** | `<\|..` | `Cliente` e `Atendente` assinam o contrato `Notificavel`. `Chamado` implementa `Comparable`. |
| **Agregação** | `o--` | `SistemaChamados` possui coleções (`0..*`) de `Cliente`, `Atendente` e `Chamado`. |
| **Associação Direcionada** | `-->` | Um `Chamado` conhece exatamente `1` `StatusChamado`, `1` `Cliente` e `0..1` `Atendente`. |
| **Dependência** | `..>` | `Main` e `ArquivoManager` utilizam `SistemaChamados` para executar ações; classes lançam exceções. |