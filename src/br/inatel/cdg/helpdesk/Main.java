package br.inatel.cdg.helpdesk;

import br.inatel.cdg.helpdesk.arquivo.ArquivoManager;
import br.inatel.cdg.helpdesk.chamado.Chamado;
import br.inatel.cdg.helpdesk.chamado.ChamadoHardware;
import br.inatel.cdg.helpdesk.chamado.ChamadoRede;
import br.inatel.cdg.helpdesk.chamado.ChamadoSoftware;
import br.inatel.cdg.helpdesk.excecao.ChamadoNaoEncontradoException;
import br.inatel.cdg.helpdesk.excecao.StatusInvalidoException;
import br.inatel.cdg.helpdesk.sistema.SistemaChamados;
import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;
import java.util.InputMismatchException;
import java.util.Scanner;

// Integrante 1 – menu do console
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaChamados sistema = new SistemaChamados();
        ArquivoManager arquivo = new ArquivoManager("dados");
        arquivo.carregar(sistema);

        int opcao = -1;
        do {
            exibirMenu();
            try {
                opcao = lerInteiro(scanner, "Escolha uma opção: ");

                switch (opcao) {
                    case 1:
                        cadastrarCliente(scanner, sistema);
                        arquivo.salvar(sistema);
                        break;
                    case 2:
                        cadastrarAtendente(scanner, sistema);
                        arquivo.salvar(sistema);
                        break;
                    case 3:
                        abrirChamado(scanner, sistema);
                        arquivo.salvar(sistema);
                        break;
                    case 4:
                        atribuirAtendente(scanner, sistema);
                        arquivo.salvar(sistema);
                        break;
                    case 5:
                        fecharChamado(scanner, sistema);
                        arquivo.salvar(sistema);
                        break;
                    case 6:
                        buscarChamado(scanner, sistema);
                        break;
                    case 7:
                        sistema.listarChamados();
                        break;
                    case 8:
                        sistema.listarPorPrioridade();
                        break;
                    case 9:
                        sistema.listarAtrasados();
                        break;
                    case 0:
                        System.out.println("Encerrando o Help Desk. Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha um número de 0 a 9.");
                }
            } catch (InputMismatchException e) {
                // o usuário digitou letra no lugar de número: descarta o que sobrou na linha
                System.out.println("Entrada inválida: digite apenas números.");
                scanner.nextLine();
            } catch (ChamadoNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (StatusInvalidoException e) {
                System.out.println("Operação não permitida: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Dado inválido: " + e.getMessage());
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== HELP DESK =====");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Cadastrar atendente");
        System.out.println("3 - Abrir chamado");
        System.out.println("4 - Atribuir atendente");
        System.out.println("5 - Fechar chamado");
        System.out.println("6 - Buscar chamado por id");
        System.out.println("7 - Listar chamados");
        System.out.println("8 - Listar por prioridade");
        System.out.println("9 - Listar atrasados");
        System.out.println("0 - Sair");
    }

    private static void cadastrarCliente(Scanner scanner, SistemaChamados sistema) {
        String nome = lerTexto(scanner, "Nome: ");
        String email = lerTexto(scanner, "E-mail: ");
        String setor = lerTexto(scanner, "Setor: ");

        Cliente cliente = new Cliente(nome, email, setor);
        sistema.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com o id " + cliente.getId() + ".");
    }

    private static void cadastrarAtendente(Scanner scanner, SistemaChamados sistema) {
        String nome = lerTexto(scanner, "Nome: ");
        String email = lerTexto(scanner, "E-mail: ");
        System.out.println("Especialidade: 1 - Hardware / 2 - Software / 3 - Rede");
        int tipo = lerInteiro(scanner, "Escolha: ");

        String especialidade;
        switch (tipo) {
            case 1:
                especialidade = "Hardware";
                break;
            case 2:
                especialidade = "Software";
                break;
            case 3:
                especialidade = "Rede";
                break;
            default:
                throw new IllegalArgumentException("Especialidade inválida: escolha 1, 2 ou 3.");
        }

        Atendente atendente = new Atendente(nome, email, especialidade);
        sistema.cadastrarAtendente(atendente);
        System.out.println("Atendente cadastrado com o id " + atendente.getId() + ".");
    }

    private static void abrirChamado(Scanner scanner, SistemaChamados sistema) {
        if (sistema.getClientes().isEmpty()) {
            throw new IllegalArgumentException("Cadastre um cliente antes de abrir um chamado.");
        }

        System.out.println("Clientes cadastrados:");
        for (Cliente c : sistema.getClientes()) {
            c.mostraInfo();
        }
        int idCliente = lerInteiro(scanner, "Id do cliente: ");
        Cliente cliente = sistema.buscarCliente(idCliente);

        System.out.println("Tipo do chamado: 1 - Hardware / 2 - Software / 3 - Rede");
        int tipo = lerInteiro(scanner, "Escolha: ");
        if (tipo < 1 || tipo > 3) {
            throw new IllegalArgumentException("Tipo inválido: escolha 1, 2 ou 3.");
        }

        String titulo = lerTexto(scanner, "Título: ");
        String descricao = lerTexto(scanner, "Descrição: ");

        // cada tipo tem os seus campos próprios; todos viram um Chamado
        Chamado chamado;
        switch (tipo) {
            case 1:
                String equipamento = lerTexto(scanner, "Equipamento: ");
                chamado = new ChamadoHardware(titulo, descricao, cliente, equipamento);
                break;
            case 2:
                String sistemaAfetado = lerTexto(scanner, "Sistema: ");
                String versao = lerTexto(scanner, "Versão: ");
                chamado = new ChamadoSoftware(titulo, descricao, cliente, sistemaAfetado, versao);
                break;
            default:
                String enderecoIp = lerTexto(scanner, "Endereço IP: ");
                chamado = new ChamadoRede(titulo, descricao, cliente, enderecoIp);
        }

        sistema.abrirChamado(chamado);
    }

    private static void atribuirAtendente(Scanner scanner, SistemaChamados sistema)
            throws StatusInvalidoException {
        if (sistema.getAtendentes().isEmpty()) {
            throw new IllegalArgumentException("Cadastre um atendente antes de atribuir um chamado.");
        }

        System.out.println("Atendentes cadastrados:");
        for (Atendente a : sistema.getAtendentes()) {
            a.mostraInfo();
        }
        int idChamado = lerInteiro(scanner, "Id do chamado: ");
        int idAtendente = lerInteiro(scanner, "Id do atendente: ");

        sistema.atribuirChamado(idChamado, idAtendente);
    }

    private static void fecharChamado(Scanner scanner, SistemaChamados sistema)
            throws StatusInvalidoException {
        int idChamado = lerInteiro(scanner, "Id do chamado: ");
        sistema.fecharChamado(idChamado);
    }

    private static void buscarChamado(Scanner scanner, SistemaChamados sistema) {
        int idChamado = lerInteiro(scanner, "Id do chamado: ");
        sistema.buscarChamado(idChamado).mostraInfo();
    }

    // Lê um número inteiro.
    private static int lerInteiro(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    // Lê um texto obrigatório. O ';' é trocado por ',' porque ele separa os campos
    // do .csv.
    private static String lerTexto(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String texto = scanner.nextLine().replace(";", ",").trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("o campo não pode ficar vazio.");
        }
        return texto;
    }
}