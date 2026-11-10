package br.inatel.cdg.helpdesk.usuario;

// Integrante 1
public class Cliente extends Pessoa implements Notificavel {

    private String setor;

    // Cadastro
    public Cliente(String nome, String email, String setor) {
        super(nome, email);
        // TODO (Integrante 1): guardar o setor
    }

    // Carregar do arquivo
    public Cliente(int id, String nome, String email, String setor) {
        super(id, nome, email);
        // TODO (Integrante 1): guardar o setor
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public String getTipo() {
        // TODO (Integrante 1)
        return "";
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        // TODO (Integrante 1): System.out.println("[E-mail para " + email + "] " + mensagem);
    }

    // Formato: id;nome;email;setor
    @Override
    public String paraLinhaCsv() {
        // TODO (Integrante 1): super.paraLinhaCsv() + ";" + setor
        return "";
    }

    @Override
    public void mostraInfo() {
        // TODO (Integrante 1): super.mostraInfo() e imprimir o setor
    }
}
