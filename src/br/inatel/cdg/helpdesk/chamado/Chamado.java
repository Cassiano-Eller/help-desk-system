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
        // TODO (Integrante 2): idChamado = proximoId++; status = ABERTO; dataAbertura = LocalDateTime.now();
    }

    // Construtor usado ao carregar do arquivo
    public Chamado(int idChamado, String titulo, String descricao, StatusChamado status,
                   LocalDateTime dataAbertura, Cliente cliente, Atendente atendente) {
        // TODO (Integrante 2): guardar tudo e fazer proximoId = Math.max(proximoId, idChamado + 1);
    }

    public void abrirChamado() {
        // TODO (Integrante 2): imprimir e cliente.enviarNotificacao("Chamado #" + idChamado + " aberto");
    }

    public void atribuirAtendente(Atendente atendente) throws StatusInvalidoException {
        // TODO (Integrante 2): alterarStatus(EM_ATENDIMENTO), guardar o atendente e notificar os dois
    }

    public void fecharChamado() throws StatusInvalidoException {
        // TODO (Integrante 2): alterarStatus(FECHADO) e notificar o cliente
    }

    // Valida a transição: ABERTO -> EM_ATENDIMENTO -> FECHADO. Qualquer outra lança a exceção
    protected void alterarStatus(StatusChamado novoStatus) throws StatusInvalidoException {
        // TODO (Integrante 2)
    }

    public boolean estaAtrasado(LocalDateTime agora) {
        // TODO (Integrante 2): status != FECHADO && agora.isAfter(dataAbertura.plusHours(calcularPrazoHoras()))
        return false;
    }

    public abstract int calcularPrazoHoras();

    // "Hardware", "Software" ou "Rede"
    public abstract String getCategoria();

    // Formato: id;categoria;titulo;descricao;status;dataAbertura;idCliente;idAtendente
    // (idAtendente vazio quando não tem atendente; as subclasses acrescentam os seus campos)
    public String paraLinhaCsv() {
        // TODO (Integrante 2)
        return "";
    }

    @Override
    public int compareTo(Chamado outro) {
        // TODO (Integrante 2): Integer.compare(this.calcularPrazoHoras(), outro.calcularPrazoHoras())
        return 0;
    }

    public void mostraInfo() {
        // TODO (Integrante 2): imprimir os atributos
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
