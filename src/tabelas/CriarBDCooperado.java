package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class CriarBDCooperado {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDCooperado (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS cooperado (" +
            "n_inscricao INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "nome VARCHAR (50) NOT NULL," +
            "cpf VARCHAR (14) UNIQUE NOT NULL," +
            "telefone VARCHAR (17) UNIQUE," +
            "cep VARCHAR (11)," +
            "uf VARCHAR (25)," +
            "cidade VARCHAR (50)," +
            "bairro VARCHAR (30)," +
            "rua VARCHAR (50)," +
            "numero INT (7) UNIQUE" +
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
