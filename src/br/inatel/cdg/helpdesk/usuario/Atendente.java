package br.inatel.cdg.helpdesk.usuario;

// Integrante 1
public class Atendente extends Pessoa implements Notificavel {

    private String especialidade; // "Hardware", "Software" ou "Rede"

    // Cadastro
    public Atendente(String nome, String email, String especialidade) {
        super(nome, email);
        // TODO (Integrante 1): guardar a especialidade
    }

    // Carregar do arquivo
    public Atendente(int id, String nome, String email, String especialidade) {
        super(id, nome, email);
        // TODO (Integrante 1): guardar a especialidade
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String getTipo() {
        // TODO (Integrante 1)
        return "";
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        // TODO (Integrante 1): System.out.println("[Painel do atendente " + nome + "] " + mensagem);
    }

    // Formato: id;nome;email;especialidade
    @Override
    public String paraLinhaCsv() {
        // TODO (Integrante 1)
        return "";
    }

    @Override
    public void mostraInfo() {
        // TODO (Integrante 1)
    }
}
