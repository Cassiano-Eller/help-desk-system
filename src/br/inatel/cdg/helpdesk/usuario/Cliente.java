package br.inatel.cdg.helpdesk.usuario;

// Integrante 1
public class Cliente extends Pessoa implements Notificavel {

    private String setor;

    // Cadastro
    public Cliente(String nome, String email, String setor) {
        super(nome, email);
        this.setor = setor;
    }

    // Carregar do arquivo
    public Cliente(int id, String nome, String email, String setor) {
        super(id, nome, email);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("[E-mail para " + email + "] " + mensagem);
    }

    // Formato: id;nome;email;setor
    @Override
    public String paraLinhaCsv() {
        return super.paraLinhaCsv() + ";" + setor;
    }

    @Override
    public void mostraInfo() {
        super.mostraInfo();
        System.out.println("    Setor: " + setor);
    }
}