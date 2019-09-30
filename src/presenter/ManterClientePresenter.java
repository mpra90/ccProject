/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import chain.Processadora;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Cliente;
import presenter.state.Inclusao;
import presenter.state.ManterClienteState;
import presenter.state.Visualizacao;
import view.ManterClienteView;

/**
 *
 * @author 
 */
public enum ManterClientePresenter {
    INSTANCE;
    
    private ManterClienteView view;
    private ManterClienteState estado;
    private Cliente cliente;
    private boolean veioPelaTelaBuscar;
    
    private ManterClientePresenter() {
        view = new ManterClienteView();
        TelaInicioPresenter.INSTANCE.addView(view);
    }

    public ManterClienteView getView() {
        return view;
    }

    public void setEstado(ManterClienteState estado) {
        this.estado = estado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente == null) {
            estado = new Inclusao(this);
        } else {
            estado = new Visualizacao(this);
        }
    }

    public boolean veioPelaTelaBuscar() {
        return veioPelaTelaBuscar;
    }

    public void setVeioPelaTelaBuscar(boolean veioPelaTelaBuscar) {
        this.veioPelaTelaBuscar = veioPelaTelaBuscar;
    }


    public Cliente getCliente() {
        return cliente;
    }
    
    public void salvar() {
        estado.salvar();
    }
    
    public void cancelar() {
        estado.cancelar();
    }
    
    public void excluir() {
        estado.excluir();
    }
    
    public void editar() {
        estado.editar();
    }
    
    public void fechar() {
        estado.fechar();
    }
    
    public void limparCampos() {
        view.getjTextFieldNome().setText("");
        view.getjTextFieldIdade().setText("");
        view.getjTextFieldIdade().setText("");
        view.getjTextFieldRenda().setText("");
        view.getjLabelLimite().setText("LIMITE");
        view.getjLabelClassificacao().setText("CLASSIFICACAO");
    }
    
    public void bloquearCampos() {
        view.getjTextFieldNome().setEditable(false);
        view.getjTextFieldIdade().setEditable(false);
        view.getjTextFieldRenda().setEditable(false);
    }
    
    public void desbloquearCampos() {
        view.getjTextFieldNome().setEditable(true);
        view.getjTextFieldIdade().setEditable(true);
        view.getjTextFieldRenda().setEditable(true);
    }
    
    public void removerListeners() {
        for (JButton botao : view.retornaTodosBotoes()) {
            for (ActionListener l : botao.getActionListeners()) {
                botao.removeActionListener(l);
            }
        }
    }
    
    public boolean validarCliente() {
        String nome = view.getjTextFieldNome().getText();
        boolean validacaoDoNome = nome.matches("([A-Za-z][A-Za-z]*[\\s][A-Za-z][A-Za-z]*)+([\\s][A-Za-z][A-Za-z]*)*");
        String idadeString = view.getjTextFieldIdade().getText();
        boolean validacaoDaIdade = idadeString.matches("[1-9][0-9]*");
        String rendaString = view.getjTextFieldRenda().getText();
        boolean validacaoDaRenda = rendaString.matches("[1-9][0-9]*+([0-9]*|[.][0-9]*)");
        if (validacaoDoNome) {
            if (validacaoDaIdade) {
                if (validacaoDaRenda) {
                    double renda = Double.parseDouble(rendaString);
                    double limiteDeCredito = renda * 0.4;
                    String limiteDeCreditoString = String.format("%.2f", limiteDeCredito);
                    String classificacao = Processadora.INSTANCE.processar(renda);
                    view.getjLabelLimite().setText(limiteDeCreditoString);
                    view.getjLabelClassificacao().setText(classificacao);
                } else {
                    JOptionPane.showMessageDialog(null, "A renda deve ser informada com um número maior que zero."
                            , "Renda inválida", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "A idade deve ser um número inteiro positivo."
                        , "Idade inválida", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informe um nome e um sobrenome."
                    , "Nome inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
