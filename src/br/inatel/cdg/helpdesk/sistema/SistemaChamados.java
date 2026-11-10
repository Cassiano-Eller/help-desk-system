package br.inatel.cdg.helpdesk.sistema;

import br.inatel.cdg.helpdesk.chamado.Chamado;
import br.inatel.cdg.helpdesk.excecao.ChamadoNaoEncontradoException;
import br.inatel.cdg.helpdesk.excecao.StatusInvalidoException;
import br.inatel.cdg.helpdesk.usuario.Atendente;
import br.inatel.cdg.helpdesk.usuario.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Integrante 3 – guarda clientes, atendentes e chamados em memória
public class SistemaChamados {

    private Map<Integer, Chamado> chamados = new HashMap<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Atendente> atendentes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        // TODO (Integrante 3)
    }

    public void cadastrarAtendente(Atendente atendente) {
        // TODO (Integrante 3)
    }

    // Recebe qualquer tipo de chamado (polimorfismo)
    public void abrirChamado(Chamado chamado) {
        // TODO (Integrante 3): guardar no map e chamar chamado.abrirChamado()
    }

    // Igual ao abrirChamado, mas sem notificar (usado pelo ArquivoManager)
    public void adicionarChamadoCarregado(Chamado chamado) {
        // TODO (Integrante 3)
    }

    public Chamado buscarChamado(int id) {
        // TODO (Integrante 3): lançar ChamadoNaoEncontradoException se não existir
        throw new ChamadoNaoEncontradoException("Chamado #" + id + " não encontrado");
    }

    public Cliente buscarCliente(int id) {
        // TODO (Integrante 3): lançar IllegalArgumentException se não existir
        return null;
    }

    public Atendente buscarAtendente(int id) {
        // TODO (Integrante 3): lançar IllegalArgumentException se não existir
        return null;
    }

    public void atribuirChamado(int idChamado, int idAtendente) throws StatusInvalidoException {
        // TODO (Integrante 3): buscar os dois e chamar chamado.atribuirAtendente(atendente)
    }

    public void fecharChamado(int idChamado) throws StatusInvalidoException {
        // TODO (Integrante 3)
    }

    public void listarChamados() {
        // TODO (Integrante 3): mostraInfo() de cada chamado
    }

    public void listarPorPrioridade() {
        // TODO (Integrante 3): copiar a lista e usar Collections.sort (usa o compareTo do Chamado)
    }

    public void listarAtrasados() {
        // TODO (Integrante 3): estaAtrasado(LocalDateTime.now())
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
