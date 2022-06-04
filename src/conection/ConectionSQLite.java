package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConectionSQLite {
    
    private Connection conexao;
    
    public boolean conectar(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:/RC SISTEMA/bd/banco.db";
            this.conexao = DriverManager.getConnection(url);
        } catch(SQLException | ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado. Error 53. ", 
                    " Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
    public boolean desconectar(){
        try{
            if(this.conexao.isClosed() == false){
                this.conexao.close();
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado. Error 54. ", 
                    " Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
    public Statement criarStatement(){
        try{
            return this.conexao.createStatement();
        } catch(SQLException ex){
            return null;
        }
    }
    
    public Connection getConexao(){
        return this.conexao;
    }
    
    public PreparedStatement criarPreparedStatement(String sql){
        try{
            return this.conexao.prepareStatement(sql);
        } catch(SQLException ex){
            return null;
        }
    }
}
