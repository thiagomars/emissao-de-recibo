package consultasBD;

import conection.ConectionSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cooperado;


public class CooperadoConsulta {
    
    public List<Cooperado> readForCooperadoCPF(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cooperado> cooperados = new ArrayList();
        
        try{
            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperado WHERE cpf = ?;");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cooperado cooperado = new Cooperado();
                
                cooperado.setNome(rs.getString("nome"));
                cooperado.setCpf(rs.getString("cpf"));
                cooperado.setTelefone(rs.getString("telefone"));
                cooperado.setN_inscricao(rs.getInt("n_inscricao"));
                cooperado.setCep(rs.getString("cep"));
                cooperado.setUf(rs.getString("uf"));
                cooperado.setCidade(rs.getString("cidade"));
                cooperado.setBairro(rs.getString("bairro"));
                cooperado.setRua(rs.getString("rua"));
                cooperado.setNumero(rs.getInt("numero"));
                
                cooperados.add(cooperado);
            }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 444");
        } finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 354");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperados;
    }
    
    public List<Cooperado> readForCooperadoNome(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cooperado> cooperados = new ArrayList();
        
        try{
            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperado WHERE nome LIKE ?;");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cooperado cooperado = new Cooperado();
                
                cooperado.setNome(rs.getString("nome"));
                cooperado.setCpf(rs.getString("cpf"));
                cooperado.setTelefone(rs.getString("telefone"));
                cooperado.setN_inscricao(rs.getInt("n_inscricao"));
                cooperado.setCep(rs.getString("cep"));
                cooperado.setUf(rs.getString("uf"));
                cooperado.setCidade(rs.getString("cidade"));
                cooperado.setBairro(rs.getString("bairro"));
                cooperado.setRua(rs.getString("rua"));
                cooperado.setNumero(rs.getInt("numero"));
                
                cooperados.add(cooperado);
            }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 494");
        } finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 357");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperados;
    }
    
    public List<Cooperado> readForCooperado(){
        ConectionSQLite consqlite = new ConectionSQLite();
        
        consqlite.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cooperado> cooperados = new ArrayList();
        
        try{
            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperado;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cooperado cooperado = new Cooperado();
                
                cooperado.setNome(rs.getString("nome"));
                cooperado.setCpf(rs.getString("cpf"));
                cooperado.setTelefone(rs.getString("telefone"));
                cooperado.setN_inscricao(rs.getInt("n_inscricao"));
                cooperado.setCep(rs.getString("cep"));
                cooperado.setUf(rs.getString("uf"));
                cooperado.setCidade(rs.getString("cidade"));
                cooperado.setBairro(rs.getString("bairro"));
                cooperado.setRua(rs.getString("rua"));
                cooperado.setNumero(rs.getInt("numero"));
                
                cooperados.add(cooperado);
            }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 495");
        } finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 358");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperados;
    }
    
    
}
