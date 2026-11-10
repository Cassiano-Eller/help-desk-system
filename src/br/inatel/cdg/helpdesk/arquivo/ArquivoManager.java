package br.inatel.cdg.helpdesk.arquivo;

import br.inatel.cdg.helpdesk.sistema.SistemaChamados;

import java.nio.file.Path;
import java.nio.file.Paths;

// Integrante 3 – salva e carrega os dados em dados/*.csv
public class ArquivoManager {

    private Path arquivoClientes;
    private Path arquivoAtendentes;
    private Path arquivoChamados;

    public ArquivoManager(String pastaDados) {
        this.arquivoClientes = Paths.get(pastaDados, "clientes.csv");
        this.arquivoAtendentes = Paths.get(pastaDados, "atendentes.csv");
        this.arquivoChamados = Paths.get(pastaDados, "chamados.csv");
    }

    // Grava os 3 arquivos usando paraLinhaCsv() de cada objeto (polimorfismo)
    public void salvar(SistemaChamados sistema) {
        // TODO (Integrante 3): Files.write com try/catch (IOException e)
    }

    // Lê clientes e atendentes ANTES dos chamados. Se o arquivo não existe, não faz nada
    public void carregar(SistemaChamados sistema) {
        // TODO (Integrante 3): Files.readAllLines, split(";"), switch pela categoria,
        // multi-catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) para linha inválida, finally
    }
}
