package interfaces;

import conection.ConectionSQLite;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tabelas.CriarBDAtivar;
import tabelas.CriarBDCheque;
import tabelas.CriarBDCooperado;
import tabelas.CriarBDCooperativa;
import tabelas.CriarBDDinheiro;
import tabelas.CriarBDRecibo;
import tabelas.CriarBDRegistroImprimir;
import tabelas.CriarBDRegistroLogin;
import tabelas.CriarBDReiniciar;
import tabelas.CriarBDUsuario;

public class Inicializar extends javax.swing.JFrame {
    
    private JFrame Inicializar = this;
    boolean botao = false;
    
    public Inicializar() {
        initComponents();
        
        //
        jProgressBar2.setVisible(false);
        
        new Thread(){
            @Override
            public void run(){
                try{
                    sleep(5000);
                    
                    try{
                        jLabel2.setText("0%");
                        Thread.sleep(700);
                        
                        //Criar Pasta Raiz
                        new File ("/RC SISTEMA").mkdir();
                        jProgressBar2.setValue(1);
                        Thread.sleep(50);
                        jLabel2.setText("1%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/doc").mkdir();
                        jProgressBar2.setValue(2);
                        Thread.sleep(50);
                        jLabel2.setText("2%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/doc/config").mkdir();
                        jProgressBar2.setValue(3);
                        Thread.sleep(50);
                        jLabel2.setText("3%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/doc/cont").mkdir();
                        jProgressBar2.setValue(4);
                        Thread.sleep(200);
                        jLabel2.setText("4%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/execute").mkdir();
                        jProgressBar2.setValue(5);
                        Thread.sleep(200);
                        jLabel2.setText("5%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/files").mkdir();
                        jProgressBar2.setValue(6);
                        Thread.sleep(50);
                        jLabel2.setText("6%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/files/life").mkdir();
                        jProgressBar2.setValue(7);
                        Thread.sleep(50);
                        jLabel2.setText("7%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/files/nrecb").mkdir();
                        jProgressBar2.setValue(8);
                        Thread.sleep(50);
                        jLabel2.setText("8%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/files/register").mkdir();
                        jProgressBar2.setValue(9);
                        Thread.sleep(50);
                        jLabel2.setText("9%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/html").mkdir();
                        jProgressBar2.setValue(10);
                        Thread.sleep(50);
                        jLabel2.setText("10%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/html/utilities").mkdir();
                        jProgressBar2.setValue(11);
                        Thread.sleep(50);
                        jLabel2.setText("11%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/temp").mkdir();
                        jProgressBar2.setValue(12);
                        Thread.sleep(200);
                        jLabel2.setText("12%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/temp/cooperado").mkdir();
                        jProgressBar2.setValue(13);
                        Thread.sleep(50);
                        jLabel2.setText("13%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/temp/cooperativa").mkdir();
                        jProgressBar2.setValue(14);
                        Thread.sleep(50);
                        jLabel2.setText("14%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/temp/recibo").mkdir();
                        jProgressBar2.setValue(15);
                        Thread.sleep(50);
                        jLabel2.setText("15%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/temp/user").mkdir();
                        jProgressBar2.setValue(16);
                        Thread.sleep(50);
                        jLabel2.setText("16%");
                        
                        //Criar Pasta
                        new File ("/RC SISTEMA/bd").mkdir();
                        jProgressBar2.setValue(17);
                        Thread.sleep(50);
                        jLabel2.setText("17%");
                        
                        //Indicar criar os arquivos
                        jProgressBar2.setValue(20);
                        Thread.sleep(800);
                        jLabel2.setText("20%");
                        
                        //Criar Arquivo
                        File arquivo1 = new File ("/RC SISTEMA/temp/cooperativa/accop.txt");
                        arquivo1.createNewFile();
                        jProgressBar2.setValue(21);
                        Thread.sleep(200);
                        jLabel2.setText("21%");
                        
                        //Criar Arquivo
                        File arquivo2 = new File ("/RC SISTEMA/files/verific.xml");
                        arquivo2.createNewFile();
                        jProgressBar2.setValue(22);
                        Thread.sleep(50);
                        jLabel2.setText("22%");
                        
                        //Criar Arquivo
                        File arquivo3 = new File ("/RC SISTEMA/files/life/rooms.xml");
                        arquivo3.createNewFile();
                        jProgressBar2.setValue(23);
                        Thread.sleep(50);
                        jLabel2.setText("23%");
                        
                        //Criar Arquivo
                        File arquivo4 = new File ("/RC SISTEMA/files/activation.txt");
                        arquivo4.createNewFile();
                        jProgressBar2.setValue(24);
                        Thread.sleep(200);
                        jLabel2.setText("24%");
                        
                        //Criar Arquivo
                        File arquivo5 = new File("/RC SISTEMA/files/ankey.txt");
                        arquivo5.createNewFile();
                        jProgressBar2.setValue(25);
                        Thread.sleep(200);
                        jLabel2.setText("25%");
                        
                        //Verificar o arquivo
                        FileReader caminho1a = new FileReader("/RC SISTEMA/files/activation.txt");
                        BufferedReader caminho1b = new BufferedReader(caminho1a);
                        String linhaa = caminho1b.readLine();
                        
                        Thread.sleep(600);
                        jLabel2.setText("30%");
                        
                        while(linhaa == null){
                            FileWriter file1 = new FileWriter("/RC SISTEMA/files/activation.txt");
                            BufferedWriter escrever1 = new BufferedWriter(file1);
                            escrever1.write("*key activation ### = &()@NO");
                            
                            escrever1.close();
                            linhaa = caminho1b.readLine();
                            jProgressBar2.setValue(32);
                            
                            Thread.sleep(200);
                            jLabel2.setText("31%");
                        
                        }

                        //Verificar o arquivo
                        FileReader caminho2a = new FileReader("/RC SISTEMA/files/ankey.txt");
                        BufferedReader caminho2b = new BufferedReader(caminho2a);
                        String linhab = caminho2b.readLine();
                        jProgressBar2.setValue(34);
                        
                        while(linhab == null){
                            FileWriter file2 = new FileWriter("/RC SISTEMA/files/ankey.txt");
                            BufferedWriter escrever2 = new BufferedWriter(file2);
                            escrever2.write("8ijd8m.s7klzt(kajaqwcepl922;ppceo9ksnvie9@kwk[qw.s]1dkdkdfikms9209839021091920120n883901)");
                            
                            escrever2.close();
                            linhab = caminho2b.readLine();
                            jProgressBar2.setValue(35);
                            
                            Thread.sleep(200);
                            jLabel2.setText("32%");
                        
                        }
                        
                        //Testar Conexão com o Banco de Dados
                        Thread.sleep(2500);
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            sqlite.conectar();
                            jLabel2.setText("50%");
                            
                            Thread.sleep(1000);
                            sqlite.desconectar();
                            jLabel2.setText("55%");
                        } catch(Error | Exception ex){
                            JOptionPane.showMessageDialog(null, "Não foi possível conectar com o Banco de Dados, "
                                    + "Entre em contato com um \nde nossos atendentes para corrigir "
                                    + "o erro. \nError 50.", " Banco de Dados", JOptionPane.ERROR_MESSAGE);
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar tabela de Ativação se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDAtivar ativar = new CriarBDAtivar(sqlite);
                            ativar.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("60%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Cheque se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDCheque cheque = new CriarBDCheque(sqlite);
                            cheque.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("61%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Cooperado se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDCooperado cooperado = new CriarBDCooperado(sqlite);
                            cooperado.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("62%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela da Cooperativa se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDCooperativa cooperativa = new CriarBDCooperativa(sqlite);
                            cooperativa.criarTabelaAtivar();;
                            
                            Thread.sleep(200);
                            jLabel2.setText("63%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Dinheiro se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDDinheiro dinheiro = new CriarBDDinheiro(sqlite);
                            dinheiro.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("64%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Recibo se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDRecibo recibo = new CriarBDRecibo(sqlite);
                            recibo.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("65%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Registro de Impressão se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDRegistroImprimir registroimprimir = new CriarBDRegistroImprimir(sqlite);
                            registroimprimir.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("66%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Registro de Login se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDRegistroLogin registrologin = new CriarBDRegistroLogin(sqlite);
                            registrologin.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("67%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela de Reiniciar se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDReiniciar reiniciar = new CriarBDReiniciar(sqlite);
                            reiniciar.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("68%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        //Criar Tabela do Usuario se necessário
                        try{
                            ConectionSQLite sqlite = new ConectionSQLite();
                            CriarBDUsuario usuario = new CriarBDUsuario(sqlite);
                            usuario.criarTabelaAtivar();
                            
                            Thread.sleep(200);
                            jLabel2.setText("69%");
                        } catch(Error | Exception ex){
                            jLabel2.setForeground(Color.RED);
                        }
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        //Pausa para Iniciar
                        Thread.sleep(1000);
                        jLabel2.setText("70%");
                        
                        Thread.sleep(800);
                        jLabel2.setText("80%");
                        
                        Thread.sleep(800);
                        jLabel2.setText("100%");
                        
                        Thread.sleep(2000);
                        jLabel2.setText("Tudo Pronto");
                        Thread.sleep(600);
                        
                        new Tela1().setVisible(true);
                        Inicializar.dispose();
                        
                    } catch(IOException | InterruptedException | Error ex){
                        JOptionPane.showMessageDialog(null, "Erro inesperado. Error 1001.", 
                                " Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Inicializar.class.getName()).log(Level.SEVERE, null, ex);
                }        
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SF UI Text", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Iniciando");
        jLabel2.setToolTipText("");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 1000, 20));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inicio 2.0.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 560));

        jProgressBar2.setForeground(new java.awt.Color(32, 32, 32));
        jProgressBar2.setAlignmentX(0.0F);
        jProgressBar2.setAlignmentY(0.0F);
        jProgressBar2.setBorderPainted(false);
        jProgressBar2.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jProgressBar2.setFocusable(false);
        jProgressBar2.setRequestFocusEnabled(false);
        getContentPane().add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 140, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicializar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicializar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicializar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicializar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicializar().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar2;
    // End of variables declaration//GEN-END:variables
}
