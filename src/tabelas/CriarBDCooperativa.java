package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CriarBDCooperativa {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDCooperativa (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS cooperativa (" +
            "razaosocial VARCHAR (80) NOT NULL," +
            "cnpj VARCHAR (18) PRIMARY KEY UNIQUE NOT NULL," +
            "rua VARCHAR (50)," +
            "bairro VARCHAR (30)," +
            "numero INT (7)," +
            "cidade VARCHAR (30)," +
            "telefone VARCHAR (17) UNIQUE," +
            "uf VARCHAR (25)" +
            ");";
        
        boolean validar = false;
        
        try{
            validar = this.conexaoSQLite.conectar();
            
            Statement stmt = this.conexaoSQLite.criarStatement();
            
            stmt.execute(sql);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " Ocorreu um erro inesperado. Error 55. ", 
                    " Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        } finally{
            if(validar){
                this.conexaoSQLite.desconectar();
            }
        }
    }
}
