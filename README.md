# Divisão de Tarefas

## Integrante 1: Modelagem e Usuários

**Responsável por:** `Pessoa`, `Cliente`, `Atendente` e a interface `Notificavel`.

**O que implementar:**
- `Pessoa` com os atributos id, nome, email e telefone.
- `Cliente` e `Atendente`, que estendem `Pessoa` e implementam `Notificavel`.
- `Notificavel` com o método `enviarNotificacao()`.

**Requisitos:** classes, herança, interface, boas práticas e parte do UML.

**Precisa saber explicar:** a herança `Pessoa` > `Cliente` / `Atendente` e a interface `Notificavel`.

---

## Integrante 2: Módulo de Chamados

**Responsável por:** `Chamado`, `ChamadoHardware`, `ChamadoSoftware`, `ChamadoRede` e `StatusChamado`.

**O que implementar:**
- `Chamado` como classe abstrata, com idChamado, titulo, descricao, status e os métodos `abrirChamado()`, `fecharChamado()` e `alterarStatus()`.
- O enum `StatusChamado`, com os valores ABERTO, EM_ATENDIMENTO e FECHADO.
- Os três tipos de chamado (Hardware, Software e Rede), que estendem `Chamado`.

**Requisitos:** classes, classe abstrata, herança, polimorfismo e parte do UML.

**Precisa saber explicar:** classe abstrata, polimorfismo, os tipos de chamado e os status.

---

## Integrante 3: Infraestrutura

**Responsável por:** `SistemaChamados`, `ArquivoManager`, `MonitorChamados` e `ChamadoNaoEncontradoException`.

**O que implementar:**
- O gerenciamento das listas e dos métodos principais (`SistemaChamados`).
- A persistência em .csv/.txt (`ArquivoManager`).
- O monitoramento automático com Thread (`MonitorChamados`).
- A exceção personalizada (`ChamadoNaoEncontradoException`).

**Requisitos:** tratamento de erros, arquivos, threads, classes, GitHub e parte do UML.

**Precisa saber explicar:** exceções, arquivos, threads, implementação no GitHub e integração do sistema.