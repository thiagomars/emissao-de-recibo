package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CriarBDUsuario {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDUsuario (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
            "nome VARCHAR (70) NOT NULL," +
            "nascimento VARCHAR (10)," +
            "telefone VARCHAR (17) UNIQUE," +
            "usuario VARCHAR (50) PRIMARY KEY UNIQUE NOT NULL," +
            "senha VARCHAR (100) NOT NULL" +
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
