package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class CriarBDDinheiro {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDDinheiro (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS dinheiro (" +
            "id_dinheiro INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "valor_total DOUBLE," +
            "valor_pagamento DOUBLE," +
            "valor_troco DOUBLE," +
            "observacao VARCHAR (220) DEFAULT ('Nenhuma observação')" +
            ");";
        
        boolean validar = false;
        
        try{
            validar = this.conexaoSQLite.conectar();
            
            Statement stmt = this.conexaoSQLite.criarStatement();
            
            stmt.execute(sql);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " Ocorreu um erro inesperado. Error 52. ", 
                    " Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        } finally{
            if(validar){
                this.conexaoSQLite.desconectar();
            }
        }
    }
}
