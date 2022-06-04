package consultasBD;

import conection.ConectionSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class UsuarioConsulta {
    
    public boolean checkLogin(String login, String senha){
        ConectionSQLite consqlite = new ConectionSQLite();
        consqlite.conectar();
        PreparedStatement stmt = null;
        
        boolean check = false;
        
        try{
            stmt = consqlite.criarPreparedStatement("SELECT * FROM usuario WHERE usuario = ? AND senha = ?;");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                check = true;
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 302");
                }
            }
        }
        
        consqlite.desconectar();
        
        return check;
    }
    
    
    
}
