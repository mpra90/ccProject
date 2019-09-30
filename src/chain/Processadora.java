/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

import java.util.ArrayList;

/**
 *
 * @author
 */
public enum Processadora {
    INSTANCE;
    
    private ArrayList<Tratadora> tratadoras;

    private Processadora() {
        tratadoras = new ArrayList<>();
        tratadoras.add(new TratadoraClienteBronze(0.0, 1000.0));
        tratadoras.add(new TratadoraClientePrata(1000.0, 3000.0));
        //como o cliente ouro n possui um limite superior, passei o zero mesmo pois eu n o utilizo
        //na verificacao do aceita. Porém, caso exista mais classificações no futuro, é só mudar o valor do 
        //limite superior desta tratadora para se adequar ao requisito.
        tratadoras.add(new TratadoraClienteOuro(3000.0, 0.0));
    }
    
    public void addTratadora(Tratadora tratadora) {
        tratadoras.add(tratadora);
    }
    
    public String processar(double renda) {
        for (Tratadora t : tratadoras) {
            if (t.aceita(renda)) {
                return t.doHandle();
            }
        }
        return null;
    } 
}
