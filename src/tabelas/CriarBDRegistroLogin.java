package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CriarBDRegistroLogin {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDRegistroLogin (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS registro_login (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "registro_data VARCHAR (10) NOT NULL," +
            "registro_hora VARCHAR (8) NOT NULL," +
            "usuario VARCHAR (70) REFERENCES usuario (usuario) NOT NULL," +
            "cooperativa VARCHAR (18) REFERENCES cooperativa (cnpj) NOT NULL" +
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
