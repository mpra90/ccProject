/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author 
 */
public class ClienteSqliteDAO extends DataBase {
    
    private static Connection conectar() {
        String url = "jdbc:sqlite:./database/mydb.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexao com o Banco SQLITE conectada com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    @Override
    public int retornaMaiorRegistro() {
        String query = "SELECT MAX(id) AS maiorId FROM clientes";
        int max = 0;
        try {
            Connection conn = conectar();
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet resultado = stm.executeQuery();
            max = resultado.getInt("maiorId");
            conn.close();
            System.out.println("Conexao com o Banco SQLITE encerrada!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return max;
    }

    @Override
    public void inserir(Cliente c) {
        String query = "INSERT INTO clientes(nome,idade,renda,limiteDeCredito,classificacao) VALUES(?,?,?,?,?)";
        try {
            Connection conn = conectar();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, c.getNome());
            stm.setInt(2, c.getIdade());
            stm.setDouble(3, c.getRenda());
            stm.setDouble(4, c.getLimiteDeCredito());
            stm.setString(5, c.getClassificacao());
            stm.executeUpdate();
            conn.close();
            System.out.println("Conexao com o Banco SQLITE encerrada!");
            notificar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        String query = "DELETE FROM clientes WHERE id = ?";
        try {
            Connection conn = conectar();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.executeUpdate();
            conn.close();
            System.out.println("Conexao com o Banco SQLITE encerrada!");
            notificar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void alterar(Cliente c) {
        String query = "UPDATE clientes SET nome = ?, idade = ?, renda = ?, limiteDeCredito = ?, classificacao = ? WHERE id = ?";
        try {
            Connection conn = conectar();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, c.getNome());
            stm.setInt(2, c.getIdade());
            stm.setDouble(3, c.getRenda());
            stm.setDouble(4, c.getLimiteDeCredito());
            stm.setString(5, c.getClassificacao());
            stm.setInt(6, c.getId());
            stm.executeUpdate();
            conn.close();
            System.out.println("Conexao com o Banco SQLITE encerrada!");
            notificar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<Cliente> getClientes() {
        String query = "SELECT id,nome,idade,renda,limiteDeCredito,classificacao FROM clientes";
        ArrayList<Cliente> clientes = new ArrayList<>();;
        try {
            Connection conn = conectar();
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet resultado = stm.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                int idade = resultado.getInt("idade");
                double renda = resultado.getDouble("renda");
                resultado.getDouble("limiteDeCredito");
                resultado.getString("classificacao");
                Cliente c = new Cliente(id, nome, idade, renda);
                clientes.add(c);
            }
            conn.close();
            System.out.println("Conexao com o Banco SQLITE encerrada!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return clientes;
    }
}
