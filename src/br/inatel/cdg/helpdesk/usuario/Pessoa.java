package br.inatel.cdg.helpdesk.usuario;

// Integrante 1 – classe abstrata, mãe de Cliente e Atendente
public abstract class Pessoa {

    private static int proximoId = 1;

    protected int id;
    protected String nome;
    protected String email;

    // Construtor usado no cadastro (gera o id)
    public Pessoa(String nome, String email) {
        // TODO (Integrante 1): this.id = proximoId++; e guardar nome e email
    }

    // Construtor usado ao carregar do arquivo (o id já existe)
    public Pessoa(int id, String nome, String email) {
        // TODO (Integrante 1): guardar os atributos e fazer proximoId = Math.max(proximoId, id + 1);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    // "Cliente" ou "Atendente"
    public abstract String getTipo();

    // Formato: id;nome;email (as subclasses acrescentam o próprio campo)
    public String paraLinhaCsv() {
        // TODO (Integrante 1)
        return "";
    }

    public void mostraInfo() {
        // TODO (Integrante 1): imprimir os atributos
    }
}
