package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;

// Integrante 2
public class ChamadoHardware extends Chamado {

    private String equipamento;

    public ChamadoHardware(String titulo, String descricao, Cliente cliente, String equipamento) {
        super(titulo, descricao, cliente);
        // TODO (Integrante 2): guardar o equipamento
    }

    public ChamadoHardware(int idChamado, String titulo, String descricao, StatusChamado status,
                           LocalDateTime dataAbertura, Cliente cliente, Atendente atendente,
                           String equipamento) {
        super(idChamado, titulo, descricao, status, dataAbertura, cliente, atendente);
        // TODO (Integrante 2): guardar o equipamento
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

    // ...;equipamento
    @Override
    public String paraLinhaCsv() {
        // TODO (Integrante 2): super.paraLinhaCsv() + ";" + equipamento
        return "";
    }

    @Override
    public void mostraInfo() {
        // TODO (Integrante 2)
    }
}
