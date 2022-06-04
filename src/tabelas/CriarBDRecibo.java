package tabelas;

import conection.ConectionSQLite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CriarBDRecibo {
    
    private final ConectionSQLite conexaoSQLite;
    
    public CriarBDRecibo (ConectionSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criarTabelaAtivar(){
        String sql = "CREATE TABLE IF NOT EXISTS recibo (" +
            "id_recibo INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "cpf_cooperado VARCHAR (14)  REFERENCES cooperado (cpf) NOT NULL," +
            "valor_da_nfe DOUBLE," +
            "data_da_nfe VARCHAR (10)," +
            "porcentagem_do_inss DOUBLE," +
            "valor_do_inss DOUBLE," +
            "obs_do_inss VARCHAR (210) DEFAULT ('Nenhuma Observação')," +
            "taxa_de_adm DOUBLE," +
            "integralizacao DOUBLE," +
            "mensalidades DOUBLE," +
            "doacoes DOUBLE," +
            "rateio_de_perdas DOUBLE," +
            "valor_total_repasse DOUBLE," +
            "valor_total_liquido DOUBLE," +
            "forma_de_pagamento VARCHAR (8)," +
            "id_dinheiro INT (9) REFERENCES dinheiro (id_dinheiro) UNIQUE," +
            "id_cheque INT (9) REFERENCES cheque (id_cheque) UNIQUE" +
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
