/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

/**
 *
 * @author 
 */
public abstract class Tratadora {
    
    protected double limiteInferior;
    protected double limiteSuperior;

    public Tratadora(double limiteInferior, double limiteSuperior) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }
    
    public abstract boolean aceita(double renda);
    
    public abstract String doHandle();
}
