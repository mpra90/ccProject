/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.state;

import chain.Processadora;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import model.Cliente;
import presenter.ManterClientePresenter;
import presenter.TelaInicioPresenter;
import presenter.state.command.ComandoCancela;
import presenter.state.command.ComandoSalva;

/**
 *
 * @author
 */
public class Inclusao extends ManterClienteState {
    
    public Inclusao(ManterClientePresenter manterCliente) {
        super(manterCliente);
        confTela();
        initListeners();
    }
    
    private void confTela() {
        manterCliente.limparCampos();
        manterCliente.desbloquearCampos();
        manterCliente.getView().getjButtonSalvar().setVisible(true);
        manterCliente.getView().getjButtonCancelar().setVisible(true);
        manterCliente.getView().getjButtonEditar().setVisible(false);
        manterCliente.getView().getjButtonExcluir().setVisible(false);
        manterCliente.getView().getjButtonFechar().setVisible(false);
        manterCliente.getView().setVisible(true);
    }
    
    private void initListeners() {
        manterCliente.removerListeners();
        
        manterCliente.getView().getjTextFieldRenda().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    String rendaString = e.getDocument().getText(0, e.getDocument().getLength());
                    boolean validacao = rendaString.matches("[1-9][0-9]*+([0-9]*|[.][0-9]*)");
                    if (validacao) {
                        double renda = Double.parseDouble(rendaString);
                        double limiteDeCredito = renda * 0.4;
                        String limiteDeCreditoString = String.format("%.2f", limiteDeCredito);
                        String classificacao = Processadora.INSTANCE.processar(renda);
                        manterCliente.getView().getjLabelLimite().setText(limiteDeCreditoString);
                        manterCliente.getView().getjLabelClassificacao().setText(classificacao);
                    } else {
                        manterCliente.getView().getjLabelLimite().setText("LIMITE");
                        manterCliente.getView().getjLabelClassificacao().setText("CLASSIFICACAO");
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(Inclusao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    String rendaString = e.getDocument().getText(0, e.getDocument().getLength());
                    if (!rendaString.equals("")) {
                        boolean validacao = rendaString.matches("[1-9][0-9]*+([0-9]*|[.][0-9]*)");
                        if (validacao) {
                            double renda = Double.parseDouble(rendaString);
                            double limiteDeCredito = renda * 0.4;
                            String limiteDeCreditoString = String.format("%.2f", limiteDeCredito);
                            String classificacao = Processadora.INSTANCE.processar(renda);
                            manterCliente.getView().getjLabelLimite().setText(limiteDeCreditoString);
                            manterCliente.getView().getjLabelClassificacao().setText(classificacao);
                        } else {
                            manterCliente.getView().getjLabelLimite().setText("LIMITE");
                            manterCliente.getView().getjLabelClassificacao().setText("CLASSIFICACAO");
                        }
                    } else {
                        manterCliente.getView().getjLabelLimite().setText("LIMITE");
                        manterCliente.getView().getjLabelClassificacao().setText("CLASSIFICACAO");
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(Inclusao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Operacao ainda nao suportada.");
            }
        });
       
        manterCliente.getView().getjButtonSalvar().addActionListener((e) -> {
            manterCliente.salvar();
        });
        
        manterCliente.getView().getjButtonCancelar().addActionListener((e) -> {
            manterCliente.cancelar();
        });
    }

    @Override
    public void salvar() {
        if (manterCliente.validarCliente()) {
            String nome = manterCliente.getView().getjTextFieldNome().getText();
            int idade = Integer.parseInt(manterCliente.getView().getjTextFieldIdade().getText());
            double renda = Double.parseDouble(manterCliente.getView().getjTextFieldRenda().getText());            
            int id = TelaInicioPresenter.INSTANCE.getDao().retornaMaiorRegistro();
            
            Cliente cliente = new Cliente(id, nome, idade, renda);
            manterCliente.setCliente(cliente);
            
            new ComandoSalva().executar(manterCliente);
            
            JOptionPane.showMessageDialog(null, "Cliente " + manterCliente.getCliente().getNome() 
                + " salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            manterCliente.setEstado(new Visualizacao(manterCliente));
        }
    }

    @Override
    public void cancelar() {
        new ComandoCancela().executar(manterCliente);
    }
}
