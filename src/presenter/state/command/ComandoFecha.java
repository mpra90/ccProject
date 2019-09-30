/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.state.command;

import presenter.BuscaClientePresenter;
import presenter.ManterClientePresenter;

/**
 *
 * @author
 */
public class ComandoFecha implements Command {

    @Override
    public void executar(ManterClientePresenter manterCliente) {
        BuscaClientePresenter buscaCliente = BuscaClientePresenter.INSTANCE;
        buscaCliente.confTela();
    }
}
