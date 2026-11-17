package br.inatel.cdg.helpdesk.usuario;

// Integrante 1 – classe abstrata, mãe de Cliente e Atendente
public abstract class Pessoa {

    private static int proximoId = 1;

    protected int id;
    protected String nome;
    protected String email;

    public Pessoa(String nome, String email) {
        this.id = proximoId++;
        this.nome = nome;
        this.email = email;
    }

    public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        proximoId = Math.max(proximoId, id + 1);
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

    public abstract String getTipo();

    public String paraLinhaCsv() {
        return id + ";" + nome + ";" + email;
    }

    public void mostraInfo() {
        System.out.println("[" + getTipo() + " #" + id + "] " + nome + " | " + email);
    }
}