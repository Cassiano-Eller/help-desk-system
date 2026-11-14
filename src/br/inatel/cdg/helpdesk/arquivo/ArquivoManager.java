package br.inatel.cdg.helpdesk.arquivo;

import br.inatel.cdg.helpdesk.chamado.Chamado;
import br.inatel.cdg.helpdesk.chamado.ChamadoHardware;
import br.inatel.cdg.helpdesk.chamado.ChamadoRede;
import br.inatel.cdg.helpdesk.chamado.ChamadoSoftware;
import br.inatel.cdg.helpdesk.chamado.StatusChamado;
import br.inatel.cdg.helpdesk.sistema.SistemaChamados;
import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// Integrante 3 – salva e carrega os dados em arquivos .csv
public class ArquivoManager {

    private Path arquivoClientes;
    private Path arquivoAtendentes;
    private Path arquivoChamados;

    public ArquivoManager(String pastaDados) {
        this.arquivoClientes = Paths.get(pastaDados + "/clientes.csv");
        this.arquivoAtendentes = Paths.get(pastaDados + "/atendentes.csv");
        this.arquivoChamados = Paths.get(pastaDados + "/chamados.csv");
    }

    // Grava os 3 arquivos usando paraLinhaCsv() de cada objeto (polimorfismo)
    public void salvar(SistemaChamados sistema) {
        String textoClientes = "";
        for (Cliente cliente : sistema.getClientes()) {
            textoClientes += cliente.paraLinhaCsv() + "\n";
        }

        String textoAtendentes = "";
        for (Atendente atendente : sistema.getAtendentes()) {
            textoAtendentes += atendente.paraLinhaCsv() + "\n";
        }

        String textoChamados = "";
        for (Chamado chamado : sistema.getChamados()) {
            textoChamados += chamado.paraLinhaCsv() + "\n"; // cada tipo de chamado monta a sua linha
        }

        try {
            Files.writeString(arquivoClientes, textoClientes);
            Files.writeString(arquivoAtendentes, textoAtendentes);
            Files.writeString(arquivoChamados, textoChamados);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Lê clientes e atendentes ANTES dos chamados, porque o chamado precisa deles
    public void carregar(SistemaChamados sistema) {
        try {
            carregarClientes(sistema);
            carregarAtendentes(sistema);
            carregarChamados(sistema);
        } finally {
            System.out.println("Dados carregados: " + sistema.getClientes().size() + " cliente(s), "
                    + sistema.getAtendentes().size() + " atendente(s), "
                    + sistema.getChamados().size() + " chamado(s).");
        }
    }

    // Se o arquivo não existe (primeira execução), devolve uma lista vazia
    private List<String> lerLinhas(Path arquivo) {
        List<String> linhas = new ArrayList<>();
        if (!Files.exists(arquivo)) {
            return linhas;
        }
        try {
            linhas = Files.readAllLines(arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler " + arquivo.getFileName() + ": " + e.getMessage());
        }
        return linhas;
    }

    // Formato: id;nome;email;setor
    private void carregarClientes(SistemaChamados sistema) {
        for (String linha : lerLinhas(arquivoClientes)) {
            if (linha.trim().isEmpty()) {
                continue;
            }
            try {
                String[] campos = linha.split(";", -1);
                int id = Integer.parseInt(campos[0]);
                sistema.cadastrarCliente(new Cliente(id, campos[1], campos[2], campos[3]));
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Linha inválida em clientes.csv ignorada: " + linha);
            }
        }
    }

    // Formato: id;nome;email;especialidade
    private void carregarAtendentes(SistemaChamados sistema) {
        for (String linha : lerLinhas(arquivoAtendentes)) {
            if (linha.trim().isEmpty()) {
                continue;
            }
            try {
                String[] campos = linha.split(";", -1);
                int id = Integer.parseInt(campos[0]);
                sistema.cadastrarAtendente(new Atendente(id, campos[1], campos[2], campos[3]));
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Linha inválida em atendentes.csv ignorada: " + linha);
            }
        }
    }

    // Formato: id;categoria;titulo;descricao;status;dataAbertura;idCliente;idAtendente;campo1[;campo2]
    private void carregarChamados(SistemaChamados sistema) {
        for (String linha : lerLinhas(arquivoChamados)) {
            if (linha.trim().isEmpty()) {
                continue;
            }
            try {
                // o -1 mantém os campos vazios do fim da linha (ex.: versão em branco)
                String[] campos = linha.split(";", -1);
                int id = Integer.parseInt(campos[0]);
                String categoria = campos[1];
                String titulo = campos[2];
                String descricao = campos[3];
                StatusChamado status = StatusChamado.valueOf(campos[4]);
                LocalDateTime dataAbertura = LocalDateTime.parse(campos[5]);
                Cliente cliente = sistema.buscarCliente(Integer.parseInt(campos[6]));

                Atendente atendente = null; // campo vazio = chamado ainda sem atendente
                if (!campos[7].isEmpty()) {
                    atendente = sistema.buscarAtendente(Integer.parseInt(campos[7]));
                }

                Chamado chamado;
                switch (categoria) {
                    case "Hardware":
                        chamado = new ChamadoHardware(id, titulo, descricao, status, dataAbertura,
                                cliente, atendente, campos[8]);
                        break;
                    case "Software":
                        chamado = new ChamadoSoftware(id, titulo, descricao, status, dataAbertura,
                                cliente, atendente, campos[8], campos[9]);
                        break;
                    case "Rede":
                        chamado = new ChamadoRede(id, titulo, descricao, status, dataAbertura,
                                cliente, atendente, campos[8]);
                        break;
                    default:
                        throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
                }
                sistema.adicionarChamadoCarregado(chamado);
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Linha inválida em chamados.csv ignorada: " + linha);
            }
        }
    }
}
