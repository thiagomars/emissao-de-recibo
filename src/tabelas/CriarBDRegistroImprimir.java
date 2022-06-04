package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CriarBDRegistroImprimir {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDRegistroImprimir (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS registro_imprimir (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "registro_data VARCHAR (10)," +
            "registro_hora VARCHAR (8)," +
            "id_usuario INTEGER REFERENCES registro_login (id)," +
            "nome_usuario VARCHAR (70) REFERENCES usuario (usuario)" +
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
