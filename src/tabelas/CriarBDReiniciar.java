
package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class CriarBDReiniciar {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDReiniciar (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS reiniciar (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "motivo VARCHAR (220) DEFAULT ('Nada foi declarado')," +
            "motivo_data VARCHAR (10) NOT NULL" +
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
