package br.inatel.cdg.helpdesk.excecao;

// Integrante 3 – unchecked: o compilador não obriga a tratar
public class ChamadoNaoEncontradoException extends RuntimeException {

    public ChamadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
