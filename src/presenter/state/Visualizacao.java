/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.state;

import javax.swing.JOptionPane;
import presenter.ManterClientePresenter;
import presenter.state.command.ComandoExclusao;
import presenter.state.command.ComandoFecha;

/**
 *
 * @author
 */
public class Visualizacao extends ManterClienteState {
    
    public Visualizacao(ManterClientePresenter manterCliente) {
        super(manterCliente);
        confTela();
        initListeners();
    }
    
    private void confTela() {
        manterCliente.limparCampos();
        manterCliente.getView().getjTextFieldNome().setText(manterCliente.getCliente().getNome());
        manterCliente.getView().getjTextFieldIdade().setText(String.valueOf(manterCliente.getCliente().getIdade()));
        manterCliente.getView().getjTextFieldRenda().setText(String.format("%.2f",manterCliente.getCliente().getRenda()));
        manterCliente.getView().getjLabelLimite().setText(String.format("%.2f",manterCliente.getCliente().getLimiteDeCredito()));
        manterCliente.getView().getjLabelClassificacao().setText(manterCliente.getCliente().getClassificacao());
        manterCliente.bloquearCampos();
        manterCliente.getView().getjButtonExcluir().setVisible(true);
        manterCliente.getView().getjButtonEditar().setVisible(true);
        manterCliente.getView().getjButtonFechar().setVisible(true);
        manterCliente.getView().getjButtonSalvar().setVisible(false);
        manterCliente.getView().getjButtonCancelar().setVisible(false);
        manterCliente.getView().setVisible(true);
    }

    private void initListeners() {
        manterCliente.removerListeners();
        
        manterCliente.getView().getjButtonExcluir().addActionListener((e) -> {
            manterCliente.excluir();
        });
        
        manterCliente.getView().getjButtonEditar().addActionListener((e) -> {
            manterCliente.editar();
        });
        
        manterCliente.getView().getjButtonFechar().addActionListener((e) -> {
            manterCliente.fechar();
        });
    }
    
    @Override
    public void fechar() {
        if (manterCliente.veioPelaTelaBuscar()) {
            manterCliente.getView().setVisible(false);
            new ComandoFecha().executar(manterCliente);
        } else {
            manterCliente.setEstado(new Inclusao(manterCliente));
        }
    }

    @Override
    public void editar() {
        manterCliente.setEstado(new Edicao(manterCliente));
    }

    @Override
    public void excluir() {
        if (confirmar() == 0) {
            new ComandoExclusao().executar(manterCliente);
            JOptionPane.showMessageDialog(null, "Cliente " + manterCliente.getCliente().getNome() 
                    + " excluido com sucesso!", "Exclusão Realizada", JOptionPane.INFORMATION_MESSAGE);
            manterCliente.getView().setVisible(false);
        }
    }

    @Override
    protected int confirmar() {
        return JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir o cliente " 
                + manterCliente.getCliente().getNome() + " ?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
    }
}
