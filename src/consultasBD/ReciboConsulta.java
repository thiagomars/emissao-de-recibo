package consultasBD;

import conection.ConectionSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cooperado;
import model.bean.Recibo;


public class ReciboConsulta {
    
    public List<Recibo> readForDescBuscarRecibo(){
        ConectionSQLite consqlite = new ConectionSQLite();
        consqlite.conectar();
        
        List<Recibo> recibos = new ArrayList();
        PreparedStatement stmt = null;
        
        try{
            stmt = consqlite.criarPreparedStatement("SELECT * FROM recibo;");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Recibo recibo = new Recibo();
                
                recibo.setId(rs.getInt("id_recibo"));
                recibo.setCpf_cooperado(rs.getString("cpf_cooperado"));
                recibo.setValor_da_nfe(rs.getDouble("valor_da_nfe"));
                recibo.setData_da_nfe(rs.getString("data_da_nfe"));
                recibo.setPorcentagem_do_inss(rs.getDouble("porcentagem_do_inss"));
                recibo.setValor_do_inss(rs.getDouble("valor_do_inss"));
                recibo.setObs_do_inss(rs.getString("obs_do_inss"));
                recibo.setTaxa_de_adm(rs.getDouble("taxa_de_adm"));
                recibo.setIntegralizacao(rs.getDouble("integralizacao"));
                recibo.setMensalidades(rs.getDouble("mensalidades"));
                recibo.setDoacoes(rs.getDouble("doacoes"));
                recibo.setRateio_de_perdas(rs.getDouble("rateio_de_perdas"));
                recibo.setValor_total_repasse(rs.getDouble("valor_total_repasse"));
                recibo.setValor_total_liquido(rs.getDouble("valor_total_liquido"));
                recibo.setForma_de_pagamento(rs.getString("forma_de_pagamento"));
                recibo.setId_dinheiro(rs.getInt("id_dinheiro"));
                recibo.setId_cheque(rs.getInt("id_cheque"));
                
                recibos.add(recibo);
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 281");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 282");
                }
            }
        }
        
        consqlite.desconectar();
        
        return recibos;
    }
    
    public List<Recibo> readForDescBuscarReciboCPF(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        consqlite.conectar();
        
        List<Recibo> recibos = new ArrayList();
        PreparedStatement stmt = null;
        
        try{
            stmt = consqlite.criarPreparedStatement("SELECT * FROM recibo WHERE cpf_cooperado = ?;");
            stmt.setString(1, desc);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Recibo recibo = new Recibo();
                
                recibo.setId(rs.getInt("id_recibo"));
                recibo.setCpf_cooperado(rs.getString("cpf_cooperado"));
                recibo.setValor_da_nfe(rs.getDouble("valor_da_nfe"));
                recibo.setData_da_nfe(rs.getString("data_da_nfe"));
                recibo.setPorcentagem_do_inss(rs.getDouble("porcentagem_do_inss"));
                recibo.setValor_do_inss(rs.getDouble("valor_do_inss"));
                recibo.setObs_do_inss(rs.getString("obs_do_inss"));
                recibo.setTaxa_de_adm(rs.getDouble("taxa_de_adm"));
                recibo.setIntegralizacao(rs.getDouble("integralizacao"));
                recibo.setMensalidades(rs.getDouble("mensalidades"));
                recibo.setDoacoes(rs.getDouble("doacoes"));
                recibo.setRateio_de_perdas(rs.getDouble("rateio_de_perdas"));
                recibo.setValor_total_repasse(rs.getDouble("valor_total_repasse"));
                recibo.setValor_total_liquido(rs.getDouble("valor_total_liquido"));
                recibo.setForma_de_pagamento(rs.getString("forma_de_pagamento"));
                recibo.setId_dinheiro(rs.getInt("id_dinheiro"));
                recibo.setId_cheque(rs.getInt("id_cheque"));
                
                recibos.add(recibo);
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 281");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 282");
                }
            }
        }
        
        consqlite.desconectar();
        
        return recibos;
    }
    
    public List<Recibo> readForDescImprimirDR(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        consqlite.conectar();
        
        List<Recibo> recibos = new ArrayList();
        PreparedStatement stmt = null;
        try {
            stmt = consqlite.criarPreparedStatement("SELECT * FROM recibo WHERE id_recibo = ?;");
            stmt.setString(1, desc);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Recibo recibo = new Recibo();
                
                recibo.setId(rs.getInt("id_recibo"));
                recibo.setData_da_nfe(rs.getString("data_da_nfe"));
                recibo.setCpf_cooperado(rs.getString("cpf_cooperado"));
                recibo.setValor_da_nfe(rs.getDouble("valor_da_nfe"));
                recibo.setValor_total_repasse(rs.getDouble("valor_total_repasse"));
                recibo.setValor_total_liquido(rs.getDouble("valor_total_liquido"));
                
                recibos.add(recibo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 310");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 311");
                }
            }
        }
        
        consqlite.desconectar();
        
        return recibos;
    }
    
    public List<Cooperado> readForDescImprimirDC(String desc){
        ConectionSQLite consqlite = new ConectionSQLite();
        consqlite.conectar();
        
        List<Cooperado> cooperados = new ArrayList();
        PreparedStatement stmt = null;
        
        try {
            stmt = consqlite.criarPreparedStatement("SELECT * FROM cooperado WHERE cpf = ?;");
            stmt.setString(1, desc);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cooperado cooperado = new Cooperado();
                
                cooperado.setNome(rs.getString("nome"));
                cooperado.setTelefone(rs.getString("telefone"));
                
                cooperados.add(cooperado);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Error 310");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado. Error 311");
                }
            }
        }
        
        consqlite.desconectar();
        
        return cooperados;
    }
    
}
