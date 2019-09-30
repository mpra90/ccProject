/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.state.command;

import presenter.ManterClientePresenter;

/**
 *
 * @author
 */
public interface Command {
 
    void executar(ManterClientePresenter manterCliente);
}
