package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;

// Integrante 2
public class ChamadoHardware extends Chamado {

    private String equipamento;

    public ChamadoHardware(String titulo, String descricao, Cliente cliente, String equipamento) {
        super(titulo, descricao, cliente);
        this.equipamento = equipamento;
    }

    public ChamadoHardware(int idChamado, String titulo, String descricao, StatusChamado status,
                           LocalDateTime dataAbertura, Cliente cliente, Atendente atendente,
                           String equipamento) {
        super(idChamado, titulo, descricao, status, dataAbertura, cliente, atendente);
        this.equipamento = equipamento;
    }

    @Override
    public int calcularPrazoHoras() {
        return 48;
    }

    @Override
    public String getCategoria() {
        return "Hardware";
    }

    // ...;equipamento
    @Override
    public String paraLinhaCsv() {
        return super.paraLinhaCsv() + ";" + equipamento;
    }

    @Override
    public void mostraInfo() {
        super.mostraInfo();
        System.out.println("Equipamento: " + equipamento);
    }
}
