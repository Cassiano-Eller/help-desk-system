# Qualquer uso de IA deve estar aqui

## Integrante 3 – Adriano Benedito Ferreira Filho (Infraestrutura)

**Ferramenta:** Claude (Anthropic), pelo Claude Code.

**Onde usei:**
- Organização do projeto: ajuda para montar a divisão das classes entre o grupo e a estrutura de pacotes com o esqueleto das classes (assinaturas e `TODO` de cada integrante).
- `SistemaChamados`: implementação das listas de clientes, atendentes e chamados e dos métodos de busca, abertura, atribuição, fechamento, ordenação por prioridade e chamados atrasados.
- `ArquivoManager`: implementação da leitura e gravação dos arquivos `.csv` com `java.nio` (`Files.readAllLines` e `Files.writeString`).
- `ChamadoNaoEncontradoException` e os dados de exemplo da pasta `dados/`.
- Integração: merge das branches na `dev`, teste do sistema completo e pequenos ajustes de limpeza (comentários `TODO` que sobraram, indentação, dados de teste nos `.csv` e o arquivo `Statuschamado.java` duplicado).

**Como usei:** pedi o código seguindo o estilo das aulas de C06, revisei cada parte comparando com os slides (Aulas 12 a 14) e conferi compilando e rodando o sistema. Os commits feitos com ajuda da IA têm a linha `Co-Authored-By: Claude` no histórico do Git.
