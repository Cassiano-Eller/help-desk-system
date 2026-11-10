package br.inatel.cdg.helpdesk.excecao;

// Integrante 2 – checked: quem chama é obrigado a tratar (try/catch) ou repassar (throws)
public class StatusInvalidoException extends Exception {

    public StatusInvalidoException(String mensagem) {
        super(mensagem);
    }
}
