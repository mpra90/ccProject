/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.ClienteSqliteDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import observer.ObservadoClienteSQLITE;
import observer.ObservadorBuscaCliente;
import view.BuscaClienteView;

/**
 *
 * @author 
 */
public enum BuscaClientePresenter implements ObservadorBuscaCliente {
    INSTANCE;
    
    private BuscaClienteView view;
    private ArrayList<Cliente> clientes;

    private BuscaClientePresenter() {
        view = new BuscaClienteView();
        TelaInicioPresenter.INSTANCE.addView(view);
        clientes = new ArrayList<>();
        TelaInicioPresenter.INSTANCE.getDao().addObservador(this);
        initListeners();
    }

    public void confTela() {
        view.setVisible(true);
        view.getjTextFieldCliente().setText("");
        popularTabela();
    }

    private void initListeners() {
        view.getjButtonBuscar().addActionListener((e) -> {
            confBotaoBuscar();
        });
        
        view.getjTextFieldCliente().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_BACK_SPACE) {
                    if (view.getjTextFieldCliente().getText().length() == 1) {
                        popularTabela();
                    }
                } else {
                    if (e.getKeyCode() == e.VK_ENTER) {
                        confBotaoBuscar();
                    }
                }
            }
        });
        
        view.getjButtonNovo().addActionListener((e) -> {
            confBotaoNovo();
        });
        
        view.getjButtonVisualizar().addActionListener((e) -> {
            confBotaoVisualizar();
        });
        
        view.getjButtonFechar().addActionListener((e) -> {
            view.setVisible(false);
        });
    }

    private void confBotaoBuscar() {
        String nome = view.getjTextFieldCliente().getText();
        boolean validacaoDoNome = nome.matches("([A-Za-z][A-Za-z][A-Za-z][A-Za-z]*)|(([A-Za-z][A-Za-z][A-Za-z][A-Za-z]*[\\s][A-Za-z][A-Za-z]*)+([\\s][A-Za-z][A-Za-z]*)*)");
        if (validacaoDoNome) {
            ArrayList<Cliente> novosClientes = new ArrayList<>();
            boolean achou = false;
            for (Cliente c : clientes) {
                if (c.getNome().equalsIgnoreCase(nome) || c.getNome().toUpperCase().startsWith(nome) || c.getNome().toLowerCase().startsWith(nome)) {
                    novosClientes.add(c);
                    achou = true;
                }
            }
            if (achou) {
                DefaultTableModel tabela = (DefaultTableModel) view.getjTable().getModel();
                if (tabela.getRowCount() == 0) {
                    for (Cliente c : novosClientes) {
                       tabela.addRow(new Object[]{c.getNome(), c.getIdade(), c.getRenda(), c.getLimiteDeCredito(), c.getClassificacao()});
                    }
                } else {
                    int i = 0;
                    while (i < tabela.getRowCount()) {
                        tabela.removeRow(i);
                    }
                    for (Cliente c : novosClientes) {
                       tabela.addRow(new Object[]{c.getNome(), c.getIdade(), c.getRenda(), c.getLimiteDeCredito(), c.getClassificacao()});
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não há registro de clientes no sistema!"
                        , "Não há registros", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "O nome deve ter pelo menos 3 letras."
                    , "Nome inválido para busca", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void confBotaoNovo() {
        ManterClientePresenter manterCliente = ManterClientePresenter.INSTANCE;
        manterCliente.setVeioPelaTelaBuscar(true);
        view.setVisible(false);
        manterCliente.setCliente(null);
    }
    
    private void confBotaoVisualizar() {
        int index = view.getjTable().getSelectedRow();
        if (index >= 0) {
            ManterClientePresenter manterCliente = ManterClientePresenter.INSTANCE;
            manterCliente.setVeioPelaTelaBuscar(true);
            view.setVisible(false);
            manterCliente.setCliente(clientes.get(index));
        } else {
            JOptionPane.showMessageDialog(null, "Seleciona um campo da tabela para visualizar seu conteúdo."
                    , "Campo inválido", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void popularTabela() {
        clientes.clear();
        clientes.addAll(ClienteSqliteDAO.getClientes());
        DefaultTableModel tabela = (DefaultTableModel) view.getjTable().getModel();
        if (tabela.getRowCount() == 0) {
            for (Cliente c : clientes) {
               tabela.addRow(new Object[]{c.getNome(), c.getIdade(), c.getRenda(), c.getLimiteDeCredito(), c.getClassificacao()});
            }
        } else {
            int i = 0;
            while (i < tabela.getRowCount()) {
                tabela.removeRow(i);
            }
            for (Cliente c : clientes) {
               tabela.addRow(new Object[]{c.getNome(), c.getIdade(), c.getRenda(), c.getLimiteDeCredito(), c.getClassificacao()});
            }
        }
    }

    @Override
    public void atualizar(ObservadoClienteSQLITE observado) {
        popularTabela();
    }
}
