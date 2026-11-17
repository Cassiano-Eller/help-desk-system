package br.inatel.cdg.helpdesk.chamado;

import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;

// Integrante 2
public class ChamadoRede extends Chamado {

    private String enderecoIp;

    public ChamadoRede(String titulo, String descricao, Cliente cliente, String enderecoIp) {
        super(titulo, descricao, cliente);
        this.enderecoIp = enderecoIp;
    }

    public ChamadoRede(int idChamado, String titulo, String descricao, StatusChamado status,
                       LocalDateTime dataAbertura, Cliente cliente, Atendente atendente,
                       String enderecoIp) {
        super(idChamado, titulo, descricao, status, dataAbertura, cliente, atendente);
        this.enderecoIp = enderecoIp;
    }

    @Override
    public int calcularPrazoHoras() {
        return 4;
    }

    @Override
    public String getCategoria() {
        return "Rede";
    }

    // ...;enderecoIp
    @Override
    public String paraLinhaCsv() {
        return super.paraLinhaCsv() + ";" + enderecoIp;
    }

    @Override
    public void mostraInfo() {
        super.mostraInfo();
        System.out.println("Endereço IP: " + enderecoIp);
    }
}
