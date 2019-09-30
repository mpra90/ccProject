/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.state;

import presenter.ManterClientePresenter;

/**
 *
 * @author
 */
public abstract class ManterClienteState {
    
    protected ManterClientePresenter manterCliente;

    public ManterClienteState(ManterClientePresenter manterCliente) {
        this.manterCliente = manterCliente;
    }
    
    public void salvar() {}
    
    public void cancelar() {}
    
    public void excluir() {}
    
    protected int confirmar() {return 0;}
    
    public void editar() {}
    
    public void fechar() {}
}
