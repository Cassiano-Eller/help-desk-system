package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;

// Integrante 2
public class ChamadoSoftware extends Chamado {

    private String sistema;
    private String versao;

    public ChamadoSoftware(String titulo, String descricao, Cliente cliente, String sistema, String versao) {
        super(titulo, descricao, cliente);
        // TODO (Integrante 2): guardar sistema e versao
    }

    public ChamadoSoftware(int idChamado, String titulo, String descricao, StatusChamado status,
                           LocalDateTime dataAbertura, Cliente cliente, Atendente atendente,
                           String sistema, String versao) {
        super(idChamado, titulo, descricao, status, dataAbertura, cliente, atendente);
        // TODO (Integrante 2): guardar sistema e versao
    }

    @Override
    public int calcularPrazoHoras() {
        // TODO (Integrante 2)
        return 0;
    }

    @Override
    public String getCategoria() {
        // TODO (Integrante 2)
        return "";
    }

    // ...;sistema;versao
    @Override
    public String paraLinhaCsv() {
        // TODO (Integrante 2)
        return "";
    }

    @Override
    public void mostraInfo() {
        // TODO (Integrante 2)
    }
}
