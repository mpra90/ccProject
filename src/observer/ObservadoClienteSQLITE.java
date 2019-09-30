/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.ArrayList;

/**
 *
 * @author
 */
public abstract class ObservadoClienteSQLITE {
    
    protected ArrayList<ObservadorBuscaCliente> observadores;

    public ObservadoClienteSQLITE() {
        observadores = new ArrayList<>();
    }
    
    
    public void addObservador(ObservadorBuscaCliente observador) {
        observadores.add(observador);
    }
    
    public void notificar() {
        for (ObservadorBuscaCliente o : observadores) {
            o.atualizar(this);
        }
    }
}
