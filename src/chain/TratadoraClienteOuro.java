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
public class TratadoraClienteOuro extends Tratadora {

    public TratadoraClienteOuro(double limiteInferior, double limiteSuperior) {
        super(limiteInferior, limiteSuperior);
    }

    @Override
    public boolean aceita(double renda) {
        return (renda > limiteInferior);
    }

    @Override
    public String doHandle() {
        return "Cliente Ouro";
    }
}
