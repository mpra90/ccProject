/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cliente;
import observer.ObservadoClienteSQLITE;

/**
 *
 * @author 
 */
public abstract class DataBase extends ObservadoClienteSQLITE {
    
    public abstract void inserir(Cliente c);
    
    public abstract void remover(int id);
    
    public abstract void alterar(Cliente c);
    
    public abstract int retornaMaiorRegistro();
}
