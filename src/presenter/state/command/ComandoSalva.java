/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.state.command;

import dao.DataBase;
import presenter.ManterClientePresenter;
import presenter.TelaInicioPresenter;

/**
 *
 * @author 
 */
public class ComandoSalva implements Command {

    @Override
    public void executar(ManterClientePresenter manterCliente) {
        DataBase clienteSQLITE = TelaInicioPresenter.INSTANCE.getDao();
        clienteSQLITE.inserir(manterCliente.getCliente());
    }
}
