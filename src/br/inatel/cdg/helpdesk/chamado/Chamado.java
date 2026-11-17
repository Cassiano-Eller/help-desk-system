package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.excecao.StatusInvalidoException;
import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;

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
        // TODO (Integrante 2): idChamado = proximoId++; status = ABERTO; dataAbertura = LocalDateTime.now(); Feito!!
        this.idChamado = proximoId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = StatusChamado.ABERTO;
        this.dataAbertura = LocalDateTime.now();
        this.cliente = cliente;
        this.atendente = null;
    }

    // Construtor usado ao carregar do arquivo
    public Chamado(int idChamado, String titulo, String descricao, StatusChamado status,
                   LocalDateTime dataAbertura, Cliente cliente, Atendente atendente) {
        // TODO (Integrante 2): guardar tudo e fazer proximoId = Math.max(proximoId, idChamado + 1); Feito!!
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
        // TODO (Integrante 2): imprimir e cliente.enviarNotificacao("Chamado #" + idChamado + " aberto"); - Feito!
        String mensagem = "Chamado #" + idChamado + " aberto.";
        System.out.println(mensagem);
        cliente.enviarNotificacao(mensagem);
    }

    public void atribuirAtendente(Atendente atendente) throws StatusInvalidoException {
        // TODO (Integrante 2): alterarStatus(EM_ATENDIMENTO), guardar o atendente e notificar os dois - Feito!
        alterarStatus(StatusChamado.EM_ATENDIMENTO);
        this.atendente = atendente;
    
        cliente.enviarNotificacao("Chamado #" + idChamado + " está em atendimento.");
        atendente.enviarNotificacao("Chamado #" + idChamado + " foi atribuído a você.");
    }

    public void fecharChamado() throws StatusInvalidoException {
        // TODO (Integrante 2): alterarStatus(FECHADO) e notificar o cliente - Feito!
        alterarStatus(StatusChamado.FECHADO);
        cliente.enviarNotificacao("Chamado #" + idChamado + " foi fechado.");
    }

    // Valida a transição: ABERTO -> EM_ATENDIMENTO -> FECHADO. Qualquer outra lança a exceção
    protected void alterarStatus(StatusChamado novoStatus) throws StatusInvalidoException {
        // TODO (Integrante 2) - Feito!
        if (status == StatusChamado.ABERTO &&
            novoStatus == StatusChamado.EM_ATENDIMENTO) {
            status = novoStatus;
        return;
        }
        
        if (status == StatusChamado.EM_ATENDIMENTO &&
        novoStatus == StatusChamado.FECHADO) {
        status = novoStatus;
        return;
        }

        throw new StatusInvalidoException(
        "Transição inválida de " + status + " para " + novoStatus);
    }

    public boolean estaAtrasado(LocalDateTime agora) {
        // TODO (Integrante 2): status != FECHADO && agora.isAfter(dataAbertura.plusHours(calcularPrazoHoras())) - Feito!
        return status != StatusChamado.FECHADO &&
        agora.isAfter(dataAbertura.plusHours(calcularPrazoHoras()));
    }

    public abstract int calcularPrazoHoras();

    // "Hardware", "Software" ou "Rede"
    public abstract String getCategoria();

    // Formato: id;categoria;titulo;descricao;status;dataAbertura;idCliente;idAtendente
    // (idAtendente vazio quando não tem atendente; as subclasses acrescentam os seus campos)
    public String paraLinhaCsv() {
        // TODO (Integrante 2) - Feito !
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
        // TODO (Integrante 2): Integer.compare(this.calcularPrazoHoras(), outro.calcularPrazoHoras()) - Feito!!
        return Integer.compare(
            this.calcularPrazoHoras(),
            outro.calcularPrazoHoras());
    }

    public void mostraInfo() {
        // TODO (Integrante 2): imprimir os atributos - Feito!!
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
