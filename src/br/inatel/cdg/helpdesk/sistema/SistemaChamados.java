package br.inatel.cdg.helpdesk.sistema;

import br.inatel.cdg.helpdesk.chamado.Chamado;
import br.inatel.cdg.helpdesk.chamado.StatusChamado;
import br.inatel.cdg.helpdesk.excecao.ChamadoNaoEncontradoException;
import br.inatel.cdg.helpdesk.excecao.StatusInvalidoException;
import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Integrante 3 – guarda clientes, atendentes e chamados em memória
public class SistemaChamados {

    private Map<Integer, Chamado> chamados = new HashMap<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Atendente> atendentes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarAtendente(Atendente atendente) {
        atendentes.add(atendente);
    }

    // Recebe qualquer tipo de chamado (polimorfismo)
    public void abrirChamado(Chamado chamado) {
        chamados.put(chamado.getIdChamado(), chamado);
        chamado.abrirChamado();
    }

    // Igual ao abrirChamado, mas sem notificar (usado pelo ArquivoManager)
    public void adicionarChamadoCarregado(Chamado chamado) {
        chamados.put(chamado.getIdChamado(), chamado);
    }

    public Chamado buscarChamado(int id) {
        Chamado chamado = chamados.get(id);
        if (chamado == null) {
            throw new ChamadoNaoEncontradoException("Chamado #" + id + " não encontrado");
        }
        return chamado;
    }

    public Cliente buscarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        throw new IllegalArgumentException("Cliente #" + id + " não encontrado");
    }

    public Atendente buscarAtendente(int id) {
        for (Atendente atendente : atendentes) {
            if (atendente.getId() == id) {
                return atendente;
            }
        }
        throw new IllegalArgumentException("Atendente #" + id + " não encontrado");
    }

    public void atribuirChamado(int idChamado, int idAtendente) throws StatusInvalidoException {
        Chamado chamado = buscarChamado(idChamado);
        Atendente atendente = buscarAtendente(idAtendente);
        chamado.atribuirAtendente(atendente);
    }

    public void fecharChamado(int idChamado) throws StatusInvalidoException {
        Chamado chamado = buscarChamado(idChamado);
        chamado.fecharChamado();
    }

    public void listarChamados() {
        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado cadastrado.");
            return;
        }
        for (Chamado chamado : getChamados()) {
            chamado.mostraInfo();
        }
    }

    // Só os chamados que ainda não foram fechados, do mais urgente para o menos urgente
    public void listarPorPrioridade() {
        List<Chamado> lista = new ArrayList<>();
        for (Chamado chamado : chamados.values()) {
            if (chamado.getStatus() != StatusChamado.FECHADO) {
                lista.add(chamado);
            }
        }
        if (lista.isEmpty()) {
            System.out.println("Nenhum chamado em aberto.");
            return;
        }
        Collections.sort(lista); // usa o compareTo do Chamado
        for (Chamado chamado : lista) {
            chamado.mostraInfo();
        }
    }

    public void listarAtrasados() {
        LocalDateTime agora = LocalDateTime.now();
        int quantidade = 0;
        for (Chamado chamado : chamados.values()) {
            if (chamado.estaAtrasado(agora)) {
                chamado.mostraInfo();
                quantidade++;
            }
        }
        if (quantidade == 0) {
            System.out.println("Nenhum chamado atrasado.");
        }
    }

    public List<Chamado> getChamados() {
        return new ArrayList<>(chamados.values());
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Atendente> getAtendentes() {
        return atendentes;
    }
}
