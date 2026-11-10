package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;

// Integrante 2
public class ChamadoRede extends Chamado {

    private String enderecoIp;

    public ChamadoRede(String titulo, String descricao, Cliente cliente, String enderecoIp) {
        super(titulo, descricao, cliente);
        // TODO (Integrante 2): guardar o enderecoIp
    }

    public ChamadoRede(int idChamado, String titulo, String descricao, StatusChamado status,
                       LocalDateTime dataAbertura, Cliente cliente, Atendente atendente,
                       String enderecoIp) {
        super(idChamado, titulo, descricao, status, dataAbertura, cliente, atendente);
        // TODO (Integrante 2): guardar o enderecoIp
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

    // ...;enderecoIp
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
