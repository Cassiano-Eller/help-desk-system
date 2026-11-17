package br.inatel.cdg.helpdesk.usuario;

// Integrante 1
public class Atendente extends Pessoa implements Notificavel {

    private String especialidade; // "Hardware", "Software" ou "Rede"

    public Atendente(String nome, String email, String especialidade) {
        super(nome, email);
        this.especialidade = especialidade;
    }

    public Atendente(int id, String nome, String email, String especialidade) {
        super(id, nome, email);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String getTipo() {
        return "Atendente";
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("[Painel do atendente " + nome + "] " + mensagem);
    }

    @Override
    public String paraLinhaCsv() {
        return super.paraLinhaCsv() + ";" + especialidade;
    }

    @Override
    public void mostraInfo() {
        super.mostraInfo();
        System.out.println("    Especialidade: " + especialidade);
    }
}