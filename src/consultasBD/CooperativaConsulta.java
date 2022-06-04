package consultasBD;

import conection.ConectionSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cooperativa;


public class CooperativaConsulta {
    
    public List<Cooperativa> read(){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        List<Cooperativa> cooperativas = new ArrayList();

        PreparedStatement stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperativa;");

        try {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cooperativa cooperativa = new Cooperativa();
            
                cooperativa.setRazaosocial(rs.getString("razaosocial"));
                cooperativa.setCnpj(rs.getString("cnpj"));
                cooperativa.setRua(rs.getString("rua"));
                cooperativa.setUf(rs.getString("uf"));
                cooperativa.setBairro(rs.getString("bairro"));
                cooperativa.setNumero(rs.getInt("numero"));
                cooperativa.setCidade(rs.getString("cidade"));
                cooperativa.setTelefone(rs.getString("telefone"));
                
                cooperativas.add(cooperativa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 301");
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
        
        return cooperativas;
    }
    
    public List<Cooperativa> readForDescRS(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        List<Cooperativa> cooperativas = new ArrayList();

        PreparedStatement stmt = null;
        try {

            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperativa WHERE razaosocial LIKE ?;");
            stmt.setString(1, "%" + desc + "%");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cooperativa cooperativa = new Cooperativa();
        
                cooperativa.setRazaosocial(rs.getString("razaosocial"));
                cooperativa.setCnpj(rs.getString("cnpj"));
                cooperativa.setRua(rs.getString("rua"));
                cooperativa.setUf(rs.getString("uf"));
                cooperativa.setBairro(rs.getString("bairro"));
                cooperativa.setNumero(rs.getInt("numero"));
                cooperativa.setCidade(rs.getString("cidade"));
                cooperativa.setTelefone(rs.getString("telefone"));
                
                cooperativas.add(cooperativa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 303");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 304");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperativas;
    }
    
    public List<Cooperativa> readForDescCNPJ(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        List<Cooperativa> cooperativas = new ArrayList();

        PreparedStatement stmt = null;
        try {

            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperativa WHERE cnpj LIKE ?;");
            stmt.setString(1, desc);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cooperativa cooperativa = new Cooperativa();
        
                cooperativa.setRazaosocial(rs.getString("razaosocial"));
                cooperativa.setCnpj(rs.getString("cnpj"));
                cooperativa.setRua(rs.getString("rua"));
                cooperativa.setUf(rs.getString("uf"));
                cooperativa.setBairro(rs.getString("bairro"));
                cooperativa.setNumero(rs.getInt("numero"));
                cooperativa.setCidade(rs.getString("cidade"));
                cooperativa.setTelefone(rs.getString("telefone"));
                
                cooperativas.add(cooperativa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 305");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 306");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperativas;
    }
    
    public List<Cooperativa> readForDescUF(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        List<Cooperativa> cooperativas = new ArrayList();

        PreparedStatement stmt = null;
        try {

            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperativa WHERE uf LIKE ?;");
            stmt.setString(1, desc);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cooperativa cooperativa = new Cooperativa();
        
                cooperativa.setRazaosocial(rs.getString("razaosocial"));
                cooperativa.setCnpj(rs.getString("cnpj"));
                cooperativa.setRua(rs.getString("rua"));
                cooperativa.setUf(rs.getString("uf"));
                cooperativa.setBairro(rs.getString("bairro"));
                cooperativa.setNumero(rs.getInt("numero"));
                cooperativa.setCidade(rs.getString("cidade"));
                cooperativa.setTelefone(rs.getString("telefone"));
                
                cooperativas.add(cooperativa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 305");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 306");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperativas;
    }
    
}
