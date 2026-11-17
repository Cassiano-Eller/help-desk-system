package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.excecao.StatusInvalidoException;
import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// Integrante 2 – classe abstrata, mãe de ChamadoHardware, ChamadoSoftware e ChamadoRede
public abstract class Chamado implements Comparable<Chamado> {

    private static int proximoId = 1;

    protected int idChamado;
    protected String titulo;
    protected String descricao;
    protected StatusChamado status;
    protected LocalDateTime dataAbertura;
    protected Cliente cliente;
    protected Atendente atendente; // começa null

    // Construtor usado ao abrir um chamado novo
    public Chamado(String titulo, String descricao, Cliente cliente) {
        this.idChamado = proximoId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = StatusChamado.ABERTO;
        this.dataAbertura = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.cliente = cliente;
        this.atendente = null;
    }

    // Construtor usado ao carregar do arquivo
    public Chamado(int idChamado, String titulo, String descricao, StatusChamado status,
                   LocalDateTime dataAbertura, Cliente cliente, Atendente atendente) {
        this.idChamado = idChamado;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.cliente = cliente;
        this.atendente = atendente;

        proximoId = Math.max(proximoId, idChamado + 1);
    }

    public void abrirChamado() {
        String mensagem = "Chamado #" + idChamado + " aberto.";
        System.out.println(mensagem);
        cliente.enviarNotificacao(mensagem);
    }

    public void atribuirAtendente(Atendente atendente) throws StatusInvalidoException {
        alterarStatus(StatusChamado.EM_ATENDIMENTO);
        this.atendente = atendente;

        cliente.enviarNotificacao("Chamado #" + idChamado + " está em atendimento.");
        atendente.enviarNotificacao("Chamado #" + idChamado + " foi atribuído a você.");
    }

    public void fecharChamado() throws StatusInvalidoException {
        alterarStatus(StatusChamado.FECHADO);
        cliente.enviarNotificacao("Chamado #" + idChamado + " foi fechado.");
    }

    // Valida a transição: ABERTO -> EM_ATENDIMENTO -> FECHADO. Qualquer outra lança a exceção
    protected void alterarStatus(StatusChamado novoStatus) throws StatusInvalidoException {
        if (status == StatusChamado.ABERTO && novoStatus == StatusChamado.EM_ATENDIMENTO) {
            status = novoStatus;
            return;
        }

        if (status == StatusChamado.EM_ATENDIMENTO && novoStatus == StatusChamado.FECHADO) {
            status = novoStatus;
            return;
        }

        throw new StatusInvalidoException("Transição inválida de " + status + " para " + novoStatus);
    }

    public boolean estaAtrasado(LocalDateTime agora) {
        return status != StatusChamado.FECHADO
                && agora.isAfter(dataAbertura.plusHours(calcularPrazoHoras()));
    }

    public abstract int calcularPrazoHoras();

    // "Hardware", "Software" ou "Rede"
    public abstract String getCategoria();

    // Formato: id;categoria;titulo;descricao;status;dataAbertura;idCliente;idAtendente
    // (idAtendente vazio quando não tem atendente; as subclasses acrescentam os seus campos)
    public String paraLinhaCsv() {
        return idChamado + ";" +
           getCategoria() + ";" +
           titulo + ";" +
           descricao + ";" +
           status + ";" +
           dataAbertura + ";" +
           cliente.getId() + ";" +
           (atendente != null ? atendente.getId() : "");
    }

    @Override
    public int compareTo(Chamado outro) {
        return Integer.compare(
            this.calcularPrazoHoras(),
            outro.calcularPrazoHoras());
    }

    public void mostraInfo() {
        System.out.println("ID: " + idChamado);
        System.out.println("Categoria: " + getCategoria());
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Status: " + status);
        System.out.println("Data de abertura: " + dataAbertura);
        System.out.println("Cliente ID: " + cliente.getId());
        System.out.println("Atendente ID: " + (atendente != null ? atendente.getId() : "Não atribuído"));
        System.out.println("Prazo: " + calcularPrazoHoras() + " horas");
    }

    public int getIdChamado() {
        return idChamado;
    }

    public String getTitulo() {
        return titulo;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }
}
