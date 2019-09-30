/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.DataBase;
import factory.ClienteSqliteFactory;
import factory.IFactoryDataBase;
import java.awt.Component;
import view.TelaInicioView;

/**
 *
 * @author
 */
public enum TelaInicioPresenter {
    INSTANCE;
    
    private TelaInicioView view;
    private IFactoryDataBase fabrica;
    private DataBase dao;
    
    private TelaInicioPresenter() {
        view = new TelaInicioView();
        fabrica = new ClienteSqliteFactory();
        dao = fabrica.criar();
        confTela();
        initListeners();
    }

    private void confTela() {
        view.setVisible(true);
    }

    private void initListeners() {
        view.getjMenuItemNovo().addActionListener((e) -> {
            ManterClientePresenter manterCliente = ManterClientePresenter.INSTANCE;
            manterCliente.setVeioPelaTelaBuscar(false);
            manterCliente.setCliente(null);
        });
        
        view.getjMenuItemBuscar().addActionListener((e) -> {
            BuscaClientePresenter buscaCliente = BuscaClientePresenter.INSTANCE;
            buscaCliente.confTela();
        });
        
        view.getjMenuItemSair().addActionListener((e) -> {
            view.dispose();
        });
    }
    
    public void addView(Component c) {
        view.getjDesktopPane().add(c);
    }

    public DataBase getDao() {
        return dao;
    }
}
