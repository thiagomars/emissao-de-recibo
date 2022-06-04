package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CriarBDCheque {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDCheque (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS cheque (" +
            "id_cheque INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "quantidade INT (1)," +
            "nchequeum INT (8)," +
            "datachequeum VARCHAR (10)," +
            "valorchequeum DOUBLE," +
            "nchequedois INT (8)," +
            "datachequedois VARCHAR (10)," +
            "valorchequedois DOUBLE," +
            "nchequetres INT (8)," +
            "datachequetres VARCHAR (10)," +
            "valorchequetres DOUBLE," +
            "nchequequatro INT (8)," +
            "datachequequatro VARCHAR (10)," +
            "valorchequequatro DOUBLE," +
            "nchequecinco INT (8)," +
            "datachequecinco VARCHAR (10)," +
            "valorchequecinco DOUBLE" +
            ");";
        
        boolean validar = false;
        
        try{
            validar = this.conexaoSQLite.conectar();
            
            Statement stmt = this.conexaoSQLite.criarStatement();
            
            stmt.execute(sql);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " Ocorreu um erro inesperado. Error 53. ", 
                    " Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        } finally{
            if(validar){
                this.conexaoSQLite.desconectar();
            }
        }
    }
}
