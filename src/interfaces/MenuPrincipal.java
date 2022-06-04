package interfaces;

import classes.ManipularImagem;
import conection.ConectionSQLite;
import consultasBD.CooperadoConsulta;
import consultasBD.ReciboConsulta;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cheque;
import model.bean.Usuario;
import model.bean.Cooperado;
import model.bean.Dinheiro;
import model.bean.Recibo;
import model.bean.Reiniciar;

public class MenuPrincipal extends javax.swing.JFrame {

    String cpf;
    boolean vcpf = false, doccont = false;
    BufferedImage imagem;
    int nposicao, pposicao, numrec = 0;
    boolean auu1 = false, auu2 = false, auu3 = false;
    boolean validarcpf1, validarcpf2, validarcpf3;
    String separar1, separar2, separar3, ucaracter;
    String prox1, prox2, prox3, sep1, sep2, sep3;
    
    public MenuPrincipal() {
        initComponents();
        
        //Iniciar com a Tabela de Recibos atualizada
        readForDescRecibo();
        
        //Iniciar com a Tabela dos Cooperados atualizadas
        readForDescCooperado();
        
        
        //Inidicador da tecla CapsLock
        jLabel77.setVisible(false);
        
        //Ordenação da Tabela
        DefaultTableModel modelo2 = (DefaultTableModel) jTable2.getModel();
        jTable2.setRowSorter(new TableRowSorter(modelo2));
        
        //Ordenação da Tabela Recibo
        DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter(modelo1));
        
        //Iniciar o aviso de pagamento em cheque invisivel
        jLabel8.setVisible(false);
        
        //Confirma excluir
        jCheckBox1.setVisible(false);
        
        //jPanel com os dados/conteudo
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
        
        //////////////////////////////////////////////////////////////////////////////////////////////
        //Iniciar com os jPanel do pagamento invisiveis /////////////////////////////////////////////
        dinheiro.setVisible(false);
        cheque.setVisible(false);
        finalizar.setVisible(false);
        //Pegar a data do Sistema
        java.util.Date d = new Date();
        String dataatual = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        //Mandar a data para o campo
        jTextField7.setText(dataatual);
        jTextField58.setText(dataatual);
        //Termino do comando //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        
        //verificar logo de fundo
        File caminho = new File("/RC SISTEMA/temp/logo.jpg");
        if(caminho.exists() == true){
            campologo.setIcon(new javax.swing.ImageIcon("/RC SISTEMA/temp/logo.jpg"));
        }
    }
    
    public void readForDescImprimirDR(String desc){
        ReciboConsulta recibo = new ReciboConsulta();
        
        for(Recibo r: recibo.readForDescImprimirDR(desc)){
            jTextField43.setText(r.getData_da_nfe());
            jTextField44.setText(String.valueOf(r.getValor_da_nfe()));
            jTextField48.setText(String.valueOf(r.getValor_da_nfe()));
            jTextField46.setText(r.getCpf_cooperado());
            jTextField49.setText(String.valueOf(r.getValor_total_repasse()));
            jTextField50.setText(String.valueOf(r.getValor_total_liquido()));
        }
    }
    
    public void readForDescImprimirDC(String desc){
        ReciboConsulta recibo = new ReciboConsulta();
        
        for(Cooperado r: recibo.readForDescImprimirDC(desc)){
            jTextField45.setText(r.getNome());
            jTextField47.setText(r.getTelefone());
        }
    }
    
    public void readForDescCoop(String desc){
        CooperadoConsulta cooperado = new CooperadoConsulta();
        
        for(Cooperado c: cooperado.readForCooperadoCPF(desc)){
            jTextField34.setText(c.getNome());
            jTextField36.setText(c.getRua());
            jTextField37.setText(c.getBairro());
            jTextField40.setText(c.getUf());
            jTextField38.setText(c.getCidade());
            jTextField41.setText(String.valueOf(c.getNumero()));
            jTextField39.setText(c.getTelefone());
        }
    }
    
    public void readForDescRecibo(){
        DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
        modelo1.setNumRows(0);
        
        ReciboConsulta recibo = new ReciboConsulta();
        
        for(Recibo r: recibo.readForDescBuscarRecibo()){
            modelo1.addRow(new Object[]{
                r.getId(),
                r.getData_da_nfe(),
                r.getValor_da_nfe(),
                r.getPorcentagem_do_inss(),
                r.getValor_do_inss(),
                r.getObs_do_inss(),
                r.getTaxa_de_adm(),
                r.getIntegralizacao(),
                r.getMensalidades(),
                r.getDoacoes(),
                r.getRateio_de_perdas(),
                r.getValor_total_repasse(),
                r.getValor_total_liquido(),
                r.getForma_de_pagamento()
            });
        }
    }
    
    public void readForDescBuscarBuscaReciboCPF(String desc){
        DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
        modelo1.setNumRows(0);
        
        ReciboConsulta recibo = new ReciboConsulta();
        
        for(Recibo r: recibo.readForDescBuscarReciboCPF(desc)){
            modelo1.addRow(new Object[]{
                r.getId(),
                r.getData_da_nfe(),
                r.getValor_da_nfe(),
                r.getPorcentagem_do_inss(),
                r.getValor_do_inss(),
                r.getObs_do_inss(),
                r.getTaxa_de_adm(),
                r.getIntegralizacao(),
                r.getMensalidades(),
                r.getDoacoes(),
                r.getRateio_de_perdas(),
                r.getValor_total_repasse(),
                r.getValor_total_liquido(),
                r.getForma_de_pagamento()
            });
        }
    }
    
    public void readForDescCooperadoNome(String desc){
        CooperadoConsulta cooperado = new CooperadoConsulta();
        
        DefaultTableModel modelo2 = (DefaultTableModel) jTable2.getModel();
        modelo2.setNumRows(0);
        
        for(Cooperado c: cooperado.readForCooperadoNome(desc)){
            modelo2.addRow(new Object[]{
                c.getN_inscricao(),
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getCep(),
                c.getUf(),
                c.getCidade(),
                c.getBairro(),
                c.getRua(),
                c.getNumero()
            });
        }
    }
    
    public void readForDescCooperadoCpf(String desc){
        CooperadoConsulta cooperado = new CooperadoConsulta();
        
        DefaultTableModel modelo2 = (DefaultTableModel) jTable2.getModel();
        modelo2.setNumRows(0);
        
        for(Cooperado c: cooperado.readForCooperadoCPF(desc)){
            modelo2.addRow(new Object[]{
                c.getN_inscricao(),
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getCep(),
                c.getUf(),
                c.getCidade(),
                c.getBairro(),
                c.getRua(),
                c.getNumero()
            });
        }
    }
    
    public void readForDescCooperado(){
        CooperadoConsulta cooperado = new CooperadoConsulta();
        
        DefaultTableModel modelo2 = (DefaultTableModel) jTable2.getModel();
        modelo2.setNumRows(0);
        
        for(Cooperado c: cooperado.readForCooperado()){
            modelo2.addRow(new Object[]{
                c.getN_inscricao(),
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getCep(),
                c.getUf(),
                c.getCidade(),
                c.getBairro(),
                c.getRua(),
                c.getNumero()
            });
        }
    }
    
    
    
    
    Color verdees = new Color (0, 172, 1);
    Color verdecl = new Color(43,227,45);
    Color verdemaes = new Color (0, 205, 0);
    Color cinzab = new Color (240, 240, 240);
    Color cinzac = new Color (153, 153, 153);
    Color cinzadesabilitado = new Color(249,249,249);
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel32 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jTextField47 = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel27 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JFormattedTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel21 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel22 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField15 = new javax.swing.JFormattedTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jTextField23 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jTextField33 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField52 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jTextField53 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        finalizar = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jTextField54 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jTextField55 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        dinheiro = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        cheque = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        ncheque1 = new javax.swing.JTextField();
        vcheque1 = new javax.swing.JTextField();
        ncheque2 = new javax.swing.JTextField();
        vcheque2 = new javax.swing.JTextField();
        ncheque3 = new javax.swing.JTextField();
        vcheque3 = new javax.swing.JTextField();
        ncheque4 = new javax.swing.JTextField();
        vcheque4 = new javax.swing.JTextField();
        ncheque5 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        vcheque5 = new javax.swing.JTextField();
        dcheque5 = new javax.swing.JFormattedTextField();
        dcheque1 = new javax.swing.JFormattedTextField();
        dcheque2 = new javax.swing.JFormattedTextField();
        dcheque3 = new javax.swing.JFormattedTextField();
        dcheque4 = new javax.swing.JFormattedTextField();
        jLabel78 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        campologo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(43, 227, 45));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(43, 227, 45));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/invoice (1).png"))); // NOI18N
        jLabel4.setText("Consultar Recibo");
        jLabel4.setIconTextGap(15);
        jLabel4.setInheritsPopupMenu(false);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 230, 28));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 250, 50));

        jPanel3.setBackground(new java.awt.Color(43, 227, 45));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printer-.png"))); // NOI18N
        jLabel5.setText("Gerar Recibo");
        jLabel5.setIconTextGap(15);
        jLabel5.setInheritsPopupMenu(false);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 230, 28));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 250, 50));

        jPanel4.setBackground(new java.awt.Color(43, 227, 45));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/userr.png"))); // NOI18N
        jLabel6.setText("Adicionar Usuário");
        jLabel6.setIconTextGap(15);
        jLabel6.setInheritsPopupMenu(false);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 230, 28));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 250, 50));

        jPanel5.setBackground(new java.awt.Color(43, 227, 45));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/invoice.png"))); // NOI18N
        jLabel3.setText("Novo Recibo");
        jLabel3.setIconTextGap(15);
        jLabel3.setInheritsPopupMenu(false);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 230, 28));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 250, 50));

        jPanel6.setBackground(new java.awt.Color(43, 227, 45));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/seo.png"))); // NOI18N
        jLabel2.setText("Consultar Cooperado");
        jLabel2.setIconTextGap(15);
        jLabel2.setInheritsPopupMenu(false);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 230, 28));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 250, 50));

        jPanel7.setBackground(new java.awt.Color(43, 227, 45));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard-with-pencil-.png"))); // NOI18N
        jLabel1.setText("Cadastrar Cooperado");
        jLabel1.setIconTextGap(15);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 230, 28));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 250, 50));

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/RC 250x.png"))); // NOI18N
        jLabel74.setToolTipText("Área de Trabalho");
        jLabel74.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 180));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(26, 218, 28));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("GRUPO CDS");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 564, 250, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 590));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel16MouseEntered(evt);
            }
        });
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jLabel73.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel73.setText("Adicionar Usuário");

        jLabel67.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel67.setText("Nome Completo:");

        jLabel68.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel68.setText("Data de Nascimento:");

        jLabel69.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel69.setText("Telefone:");

        jLabel70.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel70.setText("Nome de Usuário:");

        jLabel71.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel71.setText("Senha:");

        jLabel72.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel72.setText("Repetir Senha:");

        jTextField51.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField51.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField51.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField51CaretUpdate(evt);
            }
        });
        jTextField51.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField51FocusLost(evt);
            }
        });
        jTextField51.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField51KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField51KeyTyped(evt);
            }
        });

        jTextField56.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField56.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField56.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField56FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField56FocusLost(evt);
            }
        });
        jTextField56.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField56KeyTyped(evt);
            }
        });

        try {
            jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jFormattedTextField3.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jFormattedTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField3FocusLost(evt);
            }
        });

        try {
            jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jFormattedTextField4.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jPasswordField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jPasswordField2CaretUpdate(evt);
            }
        });
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusLost(evt);
            }
        });
        jPasswordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField2KeyPressed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Adicionar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText(" ");

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("Limpar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 20, 20));
        jLabel77.setText("* Tecla CapsLock ligada");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel73)
                        .addComponent(jSeparator6)
                        .addComponent(jLabel67)
                        .addComponent(jTextField51)
                        .addGroup(jPanel32Layout.createSequentialGroup()
                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel68)
                                .addComponent(jLabel71)
                                .addComponent(jFormattedTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addComponent(jPasswordField1))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel32Layout.createSequentialGroup()
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel69)
                                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(38, 38, 38)
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel70)
                                        .addComponent(jTextField56, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                                .addGroup(jPanel32Layout.createSequentialGroup()
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel72)
                                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField56)
                    .addComponent(jFormattedTextField3)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jLayeredPane1.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel57.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel57.setText("Gerar Recibo");

        jLabel58.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel58.setText("Nº do Recibo:");

        jTextField42.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField42.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField42.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField42CaretUpdate(evt);
            }
        });
        jTextField42.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField42KeyTyped(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Gerar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel59.setText("Data do Recibo:");

        jTextField43.setEditable(false);
        jTextField43.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField43.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField44.setEditable(false);
        jTextField44.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField44.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jLabel60.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel60.setText("Valor do Recibo:");

        jPanel30.setBackground(new java.awt.Color(249, 249, 249));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cooperado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel61.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel61.setText("Nome:");

        jLabel62.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel62.setText("CPF/CNPJ:");

        jTextField45.setEditable(false);
        jTextField45.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField45.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField46.setEditable(false);
        jTextField46.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField46.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jLabel63.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel63.setText("Telefone:");

        jTextField47.setEditable(false);
        jTextField47.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField47.setMargin(new java.awt.Insets(2, 5, 2, 5));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField45))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField47)))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField46)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(jLabel63)
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel31.setBackground(new java.awt.Color(249, 249, 249));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Final", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel64.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel64.setText("Valor do Recibo:");

        jLabel65.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel65.setText("Valor Total do Repasse:");

        jLabel66.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel66.setText("Valor Total Liquido a Receber:");

        jTextField48.setEditable(false);
        jTextField48.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField48.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField48.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField49.setEditable(false);
        jTextField49.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField49.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField49.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField50.setEditable(false);
        jTextField50.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField50.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField50.setMargin(new java.awt.Insets(2, 5, 2, 5));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField50))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField48))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField49)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel57)
                    .addComponent(jSeparator5)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addGap(185, 185, 185)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addGap(29, 29, 29)))
                        .addGap(143, 143, 143)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField42)
                    .addComponent(jTextField44)
                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jLayeredPane1.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel48.setText("Consultar Recibo");

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Cooperado pelo CPF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel49.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel49.setText("Nome:");

        jLabel50.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel50.setText("CPF:");

        jTextField34.setEditable(false);
        jTextField34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField34.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        try {
            jTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextField8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField8.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField8.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField8CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField34))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))))
        );

        jPanel28.setBackground(new java.awt.Color(249, 249, 249));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cooperado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel51.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel51.setText("Rua:");

        jLabel52.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel52.setText("Bairro:");

        jLabel53.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel53.setText("UF:");

        jLabel54.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel54.setText("Cidade:");

        jLabel55.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel55.setText("Nº");

        jLabel56.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel56.setText("Telefone:");

        jTextField36.setEditable(false);
        jTextField36.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

        jTextField37.setEditable(false);
        jTextField37.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

        jTextField38.setEditable(false);
        jTextField38.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

        jTextField39.setEditable(false);
        jTextField39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

        jTextField40.setEditable(false);
        jTextField40.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

        jTextField41.setEditable(false);
        jTextField41.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField39))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField37)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField38, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel55)
                                .addGap(9, 9, 9)))
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField41)
                            .addComponent(jTextField40, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 27));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº da NF-e", "Data da NF-e", "Valor da NF-e", "Taxa do INSS", "Valor do INSS", "Obs do INSS", "Taxa de ADM", "Integralizações", "Mensalidades", "Doações", "Rateio de Perdas", "Valor de Repasse", "Valor Liquido", "Forma de Pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(0.0F);
        jTable1.setAlignmentY(0.0F);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setAutoscrolls(false);
        jTable1.setFillsViewportHeight(true);
        jTable1.setMinimumSize(new java.awt.Dimension(681, 272));
        jTable1.setRowHeight(22);
        jTable1.setRowMargin(0);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(65);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(220);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(85);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(9).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(55);
            jTable1.getColumnModel().getColumn(10).setResizable(false);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(95);
            jTable1.getColumnModel().getColumn(11).setResizable(false);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(95);
            jTable1.getColumnModel().getColumn(12).setResizable(false);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(13).setResizable(false);
            jTable1.getColumnModel().getColumn(13).setPreferredWidth(118);
        }

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton13.setText("Excluir");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText(" Estou ciente e quero prosseguir com a exclusão");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox1.setFocusPainted(false);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBox1.setMargin(new java.awt.Insets(2, 5, 2, 2));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel48)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)))
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel27.setText("Novo Recibo");

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel28.setText("Nome:");

        jLabel29.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel29.setText("CPF:");

        jTextField14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField14.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField14FocusGained(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField15.setBackground(new java.awt.Color(240, 240, 240));
        try {
            jTextField15.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextField15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField15FocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jButton2)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(249, 249, 249));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel30.setText("Rua:");

        jLabel31.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel31.setText("Bairro:");

        jLabel32.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel32.setText("Cidade:");

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField16.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField17.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField18.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jLabel33.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel33.setText("Nº");

        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField19.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jLabel34.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel34.setText("Telefone:");

        jLabel35.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel35.setText("UF:");

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField20.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField21.setEditable(false);
        jTextField21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField21.setMargin(new java.awt.Insets(2, 5, 2, 5));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField16))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField16))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField18)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField19)))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField20)
                    .addComponent(jTextField21))
                .addGap(93, 93, 93))
        );

        jLabel36.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel36.setText("Valor da Nota Fiscal:");

        jTextField22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField22.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField22CaretUpdate(evt);
            }
        });
        jTextField22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField22FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField22FocusLost(evt);
            }
        });
        jTextField22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField22KeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel37.setText("R$");

        jPanel24.setBackground(new java.awt.Color(249, 249, 249));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTextField23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setEnabled(false);
        jTextField23.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField23CaretUpdate(evt);
            }
        });
        jTextField23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField23FocusLost(evt);
            }
        });
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });
        jTextField23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField23KeyTyped(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel38.setText("% de");

        jTextField24.setEditable(false);
        jTextField24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel39.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel39.setText("=");

        jTextField25.setEditable(false);
        jTextField25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel40.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel40.setText("Obs:");

        jTextField26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField26.setEnabled(false);
        jTextField26.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField26CaretUpdate(evt);
            }
        });
        jTextField26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField26KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField26KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField26)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField23)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField24)
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField25)
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(249, 249, 249));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel41.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Taxa de Adm");

        jTextField27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setEnabled(false);
        jTextField27.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField27CaretUpdate(evt);
            }
        });
        jTextField27.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField27FocusLost(evt);
            }
        });
        jTextField27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField27KeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Integralizações");

        jTextField28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField28.setEnabled(false);
        jTextField28.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField28CaretUpdate(evt);
            }
        });
        jTextField28.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField28FocusLost(evt);
            }
        });
        jTextField28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField28KeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Mensalidades");

        jTextField29.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setEnabled(false);
        jTextField29.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField29CaretUpdate(evt);
            }
        });
        jTextField29.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField29FocusLost(evt);
            }
        });
        jTextField29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField29KeyTyped(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Doações");

        jTextField30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setEnabled(false);
        jTextField30.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField30CaretUpdate(evt);
            }
        });
        jTextField30.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField30FocusLost(evt);
            }
        });
        jTextField30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField30KeyTyped(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Rateio de Perdas");

        jTextField31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField31.setEnabled(false);
        jTextField31.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField31CaretUpdate(evt);
            }
        });
        jTextField31.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField31FocusLost(evt);
            }
        });
        jTextField31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField31KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField28, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField29, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField30, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField31, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel45)
                            .addGap(6, 6, 6)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addComponent(jLabel43)
                                    .addGap(6, 6, 6)
                                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel41)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Adicionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("Limpar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(249, 249, 249));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores finais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTextField33.setEditable(false);
        jTextField33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField33.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField33.setToolTipText("");

        jLabel47.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel47.setText("Valor Total Liquido a Receber:");

        jLabel46.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel46.setText("Valor Total do Repasse:");

        jTextField32.setEditable(false);
        jTextField32.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTextField32.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField32.setToolTipText("");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField33, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField32)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField33)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37))))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel18.setText("Consultar Cooperado");

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar pelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel19.setText("Nome:");

        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel20.setText("CPF/CNPJ:");

        jTextField6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField6.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField6.setName(""); // NOI18N
        jTextField6.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField6CaretUpdate(evt);
            }
        });
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jTextField7.setBackground(new java.awt.Color(240, 240, 240));
        try {
            jTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextField7.setToolTipText("");
        jTextField7.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField7.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField7CaretUpdate(evt);
            }
        });
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 397, Short.MAX_VALUE))
                    .addComponent(jTextField6))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jTextField7))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Telefone", "CEP", "UF", "Cidade", "Bairro", "Rua", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAlignmentX(0.0F);
        jTable2.setAlignmentY(0.0F);
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTable2.setRowHeight(25);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(45);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(25);
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(170);
            jTable2.getColumnModel().getColumn(7).setResizable(false);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(180);
            jTable2.getColumnModel().getColumn(8).setResizable(false);
            jTable2.getColumnModel().getColumn(8).setPreferredWidth(230);
            jTable2.getColumnModel().getColumn(9).setResizable(false);
            jTable2.getColumnModel().getColumn(9).setPreferredWidth(70);
        }

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18)
                    .addComponent(jSeparator2)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel9.setText("Cadastrar Cooperado");

        jLabel10.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel10.setText("Nome:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel11.setText("CPF:");

        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel12.setText("Telefone:");

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel13.setText("Rua:");

        jLabel14.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel14.setText("Bairro:");

        jLabel15.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel15.setText("CEP:");

        jLabel16.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel16.setText("Número:");

        jLabel17.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel17.setText("UF:");

        jTextField1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField4.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField5.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jFormattedTextField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusLost(evt);
            }
        });
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyTyped(evt);
            }
        });

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jFormattedTextField2.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jComboBox1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federa", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField52.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField52.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField52.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField52KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField52KeyTyped(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel75.setText("Cidade:");

        try {
            jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jFormattedTextField5.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jTextField53.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextField53.setForeground(new java.awt.Color(153, 153, 153));
        jTextField53.setText("Definido pelo Sistema");
        jTextField53.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField53.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField53FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField53FocusLost(evt);
            }
        });
        jTextField53.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField53KeyTyped(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel76.setText("Nº de Inscrição:");

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setText("Limpar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel12)
                                                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel13)
                                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel15)
                                                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                                                        .addGap(43, 43, 43)
                                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel76)
                                                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel75)
                                                                .addComponent(jTextField52, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))))
                                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(40, 40, 40))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(425, 425, 425)))
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        jLayeredPane1.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jComboBox2.setMaximumRowCount(4);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Dinheiro", "Cheque" }));
        jComboBox2.setLightWeightPopupEnabled(false);
        jComboBox2.setRequestFocusEnabled(false);
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel80.setText("Selecione a forma de pagamento:");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        finalizar.setBackground(new java.awt.Color(255, 255, 255));
        finalizar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel81.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel81.setText("Número do Recibo:");

        jTextField54.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField54.setForeground(new java.awt.Color(153, 153, 153));
        jTextField54.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField54.setText("Continuar Sequência do BD");
        jTextField54.setMargin(new java.awt.Insets(2, 5, 2, 8));
        jTextField54.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField54FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField54FocusLost(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel82.setText("Forma de Pagamento:");

        jTextField55.setEditable(false);
        jTextField55.setBackground(new java.awt.Color(255, 255, 255));
        jTextField55.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField55.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField55.setMargin(new java.awt.Insets(2, 5, 2, 8));
        jTextField55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField55ActionPerformed(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel83.setText("Valor Total Liquido:");

        jTextField57.setEditable(false);
        jTextField57.setBackground(new java.awt.Color(255, 255, 255));
        jTextField57.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField57.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField57.setMargin(new java.awt.Insets(2, 5, 2, 8));

        jLabel84.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel84.setText("Data do Recibo:");

        jTextField58.setEditable(false);
        jTextField58.setBackground(new java.awt.Color(255, 255, 255));
        jTextField58.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField58.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField58.setMargin(new java.awt.Insets(2, 5, 2, 8));
        jTextField58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField58ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton10.setText("Finalizar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout finalizarLayout = new javax.swing.GroupLayout(finalizar);
        finalizar.setLayout(finalizarLayout);
        finalizarLayout.setHorizontalGroup(
            finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(finalizarLayout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField54, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                    .addGroup(finalizarLayout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField58))
                    .addGroup(finalizarLayout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField55))
                    .addGroup(finalizarLayout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField57)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        finalizarLayout.setVerticalGroup(
            finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalizarLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(finalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel15.add(finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 280));

        dinheiro.setBackground(new java.awt.Color(255, 255, 255));
        dinheiro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel85.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel85.setText("Valor Total Liquido a Receber:");

        jLabel86.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel86.setText("Troco para:");

        jLabel87.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel87.setText("Observação:");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField59.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField59.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField59.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField59CaretUpdate(evt);
            }
        });
        jTextField59.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField59KeyTyped(evt);
            }
        });

        jTextField60.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField60.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel88.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel88.setText("R$");

        jLabel89.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel89.setText("R$");

        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setText("Continuar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel95.setText("Valor Troco:");

        jTextField61.setEditable(false);
        jTextField61.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextField61.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField61.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField61FocusLost(evt);
            }
        });
        jTextField61.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel96.setText("R$");

        javax.swing.GroupLayout dinheiroLayout = new javax.swing.GroupLayout(dinheiro);
        dinheiro.setLayout(dinheiroLayout);
        dinheiroLayout.setHorizontalGroup(
            dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dinheiroLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
            .addGroup(dinheiroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dinheiroLayout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField61))
                    .addGroup(dinheiroLayout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField60))
                    .addGroup(dinheiroLayout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3))
                    .addGroup(dinheiroLayout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField59)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        dinheiroLayout.setVerticalGroup(
            dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dinheiroLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88))
                .addGap(18, 18, 18)
                .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addGap(18, 18, 18)
                .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96))
                .addGap(18, 18, 18)
                .addGroup(dinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel15.add(dinheiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 280));

        cheque.setBackground(new java.awt.Color(255, 255, 255));
        cheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        cheque.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel91.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel91.setText("Quantidade de Cheques:");
        cheque.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        jComboBox3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        cheque.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 13, 50, -1));

        jLabel92.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel92.setText("Nº do Cheque");
        cheque.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 71, -1, -1));

        jLabel93.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel93.setText("Data do Cheque");
        cheque.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 71, -1, -1));

        jLabel94.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel94.setText("Valor do Cheque");
        cheque.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 71, -1, -1));
        cheque.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 50, 366, 10));

        ncheque1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        ncheque1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ncheque1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncheque1KeyTyped(evt);
            }
        });
        cheque.add(ncheque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 97, 87, -1));

        vcheque1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        vcheque1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vcheque1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vcheque1FocusLost(evt);
            }
        });
        vcheque1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vcheque1KeyTyped(evt);
            }
        });
        cheque.add(vcheque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 97, 103, -1));

        ncheque2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        ncheque2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ncheque2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncheque2KeyTyped(evt);
            }
        });
        cheque.add(ncheque2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 125, 87, -1));

        vcheque2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        vcheque2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vcheque2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vcheque2FocusLost(evt);
            }
        });
        vcheque2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vcheque2KeyTyped(evt);
            }
        });
        cheque.add(vcheque2, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 125, 103, -1));

        ncheque3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        ncheque3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ncheque3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncheque3KeyTyped(evt);
            }
        });
        cheque.add(ncheque3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 153, 87, -1));

        vcheque3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        vcheque3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vcheque3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vcheque3FocusLost(evt);
            }
        });
        vcheque3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vcheque3KeyTyped(evt);
            }
        });
        cheque.add(vcheque3, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 153, 103, -1));

        ncheque4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        ncheque4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ncheque4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncheque4KeyTyped(evt);
            }
        });
        cheque.add(ncheque4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 181, 87, -1));

        vcheque4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        vcheque4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vcheque4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vcheque4FocusLost(evt);
            }
        });
        vcheque4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vcheque4KeyTyped(evt);
            }
        });
        cheque.add(vcheque4, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 181, 103, -1));

        ncheque5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        ncheque5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ncheque5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncheque5KeyTyped(evt);
            }
        });
        cheque.add(ncheque5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 209, 87, -1));

        jButton12.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton12.setText("Continuar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        cheque.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 237, 100, 30));

        vcheque5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        vcheque5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vcheque5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vcheque5FocusLost(evt);
            }
        });
        vcheque5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vcheque5KeyTyped(evt);
            }
        });
        cheque.add(vcheque5, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 209, 103, -1));

        try {
            dcheque5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dcheque5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dcheque5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        cheque.add(dcheque5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 209, 100, 23));

        try {
            dcheque1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dcheque1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dcheque1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        cheque.add(dcheque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 97, 100, 23));

        try {
            dcheque2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dcheque2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dcheque2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        cheque.add(dcheque2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 125, 100, 23));

        try {
            dcheque3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dcheque3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dcheque3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        cheque.add(dcheque3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 153, 100, 23));

        try {
            dcheque4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dcheque4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dcheque4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        cheque.add(dcheque4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 181, 100, 23));

        jPanel15.add(cheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 280));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel78.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel78.setText("Forma de Pagamento");

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("* Selecione a quantidade de cheques que serão usados para realizar o pagamento.");

        jButton14.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton14.setText("Voltar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );

        jLayeredPane1.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 590));

        jPanel16.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 600));

        campologo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel16.add(campologo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 702, 568));

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 730, 590));

        jMenuBar1.setBorderPainted(false);

        jMenu1.setText("Arquivo");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/go-home-5.png"))); // NOI18N
        jMenuItem9.setText("Página Inicial");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        jMenuItem1.setText("Reiniciar Sistema");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application-exit-5.png"))); // NOI18N
        jMenuItem2.setText("Fechar Sistema");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application-exit.png"))); // NOI18N
        jMenuItem3.setText("Fazer Logoff");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator7);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folder_explore.png"))); // NOI18N
        jMenuItem4.setText("Inserir Logo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/picture-delete.png"))); // NOI18N
        jMenuItem15.setText("Deletar Logo");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Novo");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-suit.png"))); // NOI18N
        jMenuItem5.setText("Cooperado");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document_a4_add.png"))); // NOI18N
        jMenuItem6.setText("Recibo");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);
        jMenu4.add(jSeparator9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/group-key.png"))); // NOI18N
        jMenuItem10.setText("Usuário");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Consultar");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-suit.png"))); // NOI18N
        jMenuItem7.setText("Cooperado");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page_white_magnify.png"))); // NOI18N
        jMenuItem8.setText("Recibo");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Mais");

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document-print-5.png"))); // NOI18N
        jMenuItem14.setText("Gerar Recibo");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator.png"))); // NOI18N
        jMenuItem11.setText("Calculadora");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);
        jMenu2.add(jSeparator8);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help-contents.png"))); // NOI18N
        jMenuItem12.setText("Sobre");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.png"))); // NOI18N
        jMenuItem13.setText("Ajuda");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        //Animação de cores com o mouse
        jPanel9.setVisible(false);
        jPanel10.setVisible(true);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(true);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        jPanel9.setVisible(true);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(true);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(true);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(true);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(true);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(true);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(true);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(true);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(true);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(true);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        jPanel7.setBackground(verdees);
        jPanel2.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(verdees);
        jPanel2.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        jPanel5.setBackground(verdees);
        jPanel2.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        jPanel2.setBackground(verdees);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        jPanel3.setBackground(verdees);
        jPanel2.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        jPanel4.setBackground(verdees);
        jPanel2.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseEntered
        jPanel2.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel16MouseEntered

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        jPanel2.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel6.setBackground(verdecl);
        jPanel7.setBackground(verdecl);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        //Animação de cores com o mouse
        jPanel9.setVisible(false);
        jPanel10.setVisible(true);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(true);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        jPanel9.setVisible(true);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(true);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(true);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(true);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(true);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(true);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(true);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(true);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(true);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(true);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        jTextField6.setBackground(Color.WHITE);
        jTextField7.setBackground(cinzab);
        jTextField7.setText("");
    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jFormattedTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyTyped
        String caracteres = "1234567890./-";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jFormattedTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusGained
        //Animação de cor dos jTextField
        jTextField15.setBackground(cinzab);
        jTextField14.setBackground(Color.WHITE);
        
        //Apagar os dados dos campos
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
    }//GEN-LAST:event_jTextField14FocusGained

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField22KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField22KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField22KeyTyped

    private void jTextField23KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField23KeyTyped

    private void jTextField27KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField27KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField27KeyTyped

    private void jTextField28KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField28KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField28KeyTyped

    private void jTextField29KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField29KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField29KeyTyped

    private void jTextField30KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField30KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField30KeyTyped

    private void jTextField31KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField31KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField31KeyTyped

    private void jTextField42KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField42KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField42KeyTyped

    private void jTextField51KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField51KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField51KeyTyped

    private void jTextField56KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField56KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField56KeyTyped

    private void jTextField51CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField51CaretUpdate
        jLabel7.setText("* Por segurança, digite seu nome completo.");
    }//GEN-LAST:event_jTextField51CaretUpdate

    private void jTextField51FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField51FocusLost
        jLabel7.setText(" ");
    }//GEN-LAST:event_jTextField51FocusLost

    private void jFormattedTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField3FocusGained
        jLabel7.setText("<html>* Campo não obrigatório. Por segurança, digite o numero do <b>SEU</b> celular.</html>");
    }//GEN-LAST:event_jFormattedTextField3FocusGained

    private void jTextField56FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField56FocusGained
        jLabel7.setText("* Você pode usar letras, números e ponto. Letras Maiúsculas e Minúsculas diferem.");
    }//GEN-LAST:event_jTextField56FocusGained

    private void jTextField56FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField56FocusLost
        jLabel7.setText(" ");
    }//GEN-LAST:event_jTextField56FocusLost

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        boolean teclado = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (teclado) {
            jLabel77.setVisible(true);
        } else {
            jLabel77.setVisible(false);
        }
        
        jLabel7.setText("* Letras maiúsculas e minúsculas diferem.");
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jPasswordField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jPasswordField2CaretUpdate
        if(!String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))){
            jLabel7.setText("<html><font color = red>* As senhas não coincidem.</font></html>");
        }else{
            if("".equals(jTextField51.getText()) || "  /  /    ".equals(jFormattedTextField4.getText()) ||
                    "".equals(jTextField56.getText())){
                jLabel7.setText("* ATENÇÃO: Alguns campos são obrigatórios. Verifique e tente novamente.");
            }else{
                jLabel7.setText(" ");
            }
        }
    }//GEN-LAST:event_jPasswordField2CaretUpdate

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        jLabel7.setText(" ");
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        jLabel7.setText(" ");
    }//GEN-LAST:event_jPasswordField2FocusLost

    private void jFormattedTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField3FocusLost
        jLabel7.setText(" ");
    }//GEN-LAST:event_jFormattedTextField3FocusLost

    private void jTextField22CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField22CaretUpdate
        //Dados e Valores
        jTextField24.setText(jTextField22.getText());
        
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
        
        //Validar os próximos campos
        try{
            char charAt = jTextField22.getText().charAt(0);
            float campo = Float.parseFloat(jTextField22.getText());
            if(campo > 0){
                jPanel24.setBackground(Color.WHITE);
                jTextField23.setEnabled(true);
                jTextField26.setEnabled(true);
            }
        } catch(Error | Exception ex){
            jPanel24.setBackground(cinzadesabilitado);
                jTextField23.setEnabled(false);
                jTextField26.setEnabled(false);
        }
        
    }//GEN-LAST:event_jTextField22CaretUpdate

    private void jTextField23CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField23CaretUpdate
        float v1, v2;
        
        v1 = Float.parseFloat(jTextField23.getText());
        v2 = Float.parseFloat(jTextField24.getText());
        
        float valor = ((v1 / 100) * v2);
        
        jTextField25.setText(String.valueOf(valor));
        
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
        
        //Validar os próximos campos
        try{
            char charAt = jTextField23.getText().charAt(0);
            
            jPanel25.setBackground(Color.WHITE);
            jTextField27.setEnabled(true);
            jTextField28.setEnabled(true);
            jTextField29.setEnabled(true);
            jTextField30.setEnabled(true);
            jTextField31.setEnabled(true);
            
        } catch(Error | Exception ex){
            jPanel25.setBackground(cinzadesabilitado);
            jTextField27.setEnabled(false);
            jTextField28.setEnabled(false);
            jTextField29.setEnabled(false);
            jTextField30.setEnabled(false);
            jTextField31.setEnabled(false);
        }
        
        
    }//GEN-LAST:event_jTextField23CaretUpdate

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JOptionPane.showMessageDialog(null, "Existe alguns requisitos para você poder inserir sua LOGO: \n\n"
                + "1- A imagem deve ser até 700px de largura e 530px de altura; \n"
                + "2- Para ter um melhor desempenho do Sistema, recomendamos a imagem ser até 3 megas; \n"
                + "3- Ao invés de usar imagens/logos sem fundo, utilize arquivos com o fundo branco; \n"
                + "4- Caso a imagem ultrapasse o tamanho máximo permitido, a imagem será diminuida \n"
                + "automáticamente para poder caber; \n"
                + "5- É recomendável você utilizar arquivos com extenção jpg. Outras extenções podem ou \n"
                + "não funcionar.", "Recomendações", JOptionPane.INFORMATION_MESSAGE);
        
        
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);
        
        if(res == JFileChooser.APPROVE_OPTION){
            File arquivo = fc.getSelectedFile();
            
            try{
                try{
                    //Deletar a Imagem para melhor manipulação
                    File deleteimg = new File("/RC SISTEMA/temp/logo.jpg");
                    deleteimg.delete();
                    campologo.setIcon(null);
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Erro inesperado. Error 46.", 
                        " Error", JOptionPane.ERROR_MESSAGE);
                }
                
                //Inserir imagem
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 700, 538);
                campologo.setIcon(new ImageIcon(imagem));
            } catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao tentar manipular a imagem");
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Você não selecionou a imagem.");
        }
        
        try{
            File outputfile = new File("/RC SISTEMA/temp/" + "logo.jpg");
            ImageIO.write(imagem, "jpg", outputfile);
            JOptionPane.showMessageDialog(null, "Logo adicionada");
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Erro inesperado. Error 47.", 
                    " Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jTextField31CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField31CaretUpdate
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
    }//GEN-LAST:event_jTextField31CaretUpdate

    private void jTextField27CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField27CaretUpdate
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
    }//GEN-LAST:event_jTextField27CaretUpdate

    private void jTextField28CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField28CaretUpdate
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
    }//GEN-LAST:event_jTextField28CaretUpdate

    private void jTextField29CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField29CaretUpdate
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
    }//GEN-LAST:event_jTextField29CaretUpdate

    private void jTextField30CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField30CaretUpdate
        Float camp1, camp2, camp3, camp4, camp5, camp6, camp7, soma, result;
        
        if("".equals(jTextField22.getText())){
           camp1 = (float)0.0; 
        }else{
            camp1 = Float.parseFloat(jTextField22.getText());
        }
        
        if("".equals(jTextField25.getText())){
            camp2 = (float)0.0;
        }else{
            camp2 = Float.parseFloat(jTextField25.getText());
        }
        
        if("".equals(jTextField27.getText())){
            camp3 = (float)0.0;
        }else{
            camp3 = Float.parseFloat(jTextField27.getText());
        }
        
        if("".equals(jTextField28.getText())){
            camp4 = (float)0.0;
        }else{
            camp4 = Float.parseFloat(jTextField28.getText());
        }
        
        if("".equals(jTextField29.getText())){
            camp5 = (float)0.0;
        }else{
            camp5 = Float.parseFloat(jTextField29.getText());
        }
        
        if("".equals(jTextField30.getText())){
            camp6 = (float)0.0;
        }else{
            camp6 = Float.parseFloat(jTextField30.getText());
        }
        
        if("".equals(jTextField31.getText())){
            camp7 = (float)0.0;
        }else{
            camp7 = Float.parseFloat(jTextField31.getText());
        }
        
        result = camp1 - (camp2 + camp3 + camp4 + camp5 + camp6 + camp7);
        soma = camp2 + camp3 + camp4 + camp5 + camp6 + camp7;
        
        jTextField32.setText(String.valueOf(soma));
        jTextField33.setText(String.valueOf(result));
    }//GEN-LAST:event_jTextField30CaretUpdate

    private void jTextField22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField22FocusLost
//        jTextField22.setText(String.valueOf(Float.parseFloat(jTextField22.getText())));
//        if("   .   .   -  ".equals(jTextField15.getText()) || "".equals(jTextField14.getText())){
//            jTextField22.setText("0.0");
//        }
    }//GEN-LAST:event_jTextField22FocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if("".equals(jTextField14.getText()) || "   .   .   -  ".equals(jTextField15.getText()) || 
                "".equals(jTextField22.getText()) || "0.0".equals(jTextField22.getText())){
            JOptionPane.showMessageDialog(null, "Preencha todos os dados necessários para prosseguir.");
        }else{
            //Ir para o Painel de "Forma de Pagamento e Pagamento"
            jPanel21.setVisible(false);
            jPanel33.setVisible(true);
            //Pegar os valores finais da Nota Fiscal
            jTextField57.setText(jTextField33.getText());
            jTextField3.setText(jTextField33.getText());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField27FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField27FocusLost
        jTextField27.setText(String.valueOf(Float.parseFloat(jTextField27.getText())));
    }//GEN-LAST:event_jTextField27FocusLost

    private void jTextField28FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField28FocusLost
        jTextField28.setText(String.valueOf(Float.parseFloat(jTextField28.getText())));
    }//GEN-LAST:event_jTextField28FocusLost

    private void jTextField29FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField29FocusLost
        jTextField29.setText(String.valueOf(Float.parseFloat(jTextField29.getText())));
    }//GEN-LAST:event_jTextField29FocusLost

    private void jTextField30FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField30FocusLost
        jTextField30.setText(String.valueOf(Float.parseFloat(jTextField30.getText())));
    }//GEN-LAST:event_jTextField30FocusLost

    private void jTextField31FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField31FocusLost
        jTextField31.setText(String.valueOf(Float.parseFloat(jTextField31.getText())));
    }//GEN-LAST:event_jTextField31FocusLost

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        this.dispose();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            
        }
        new Tela1().setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String opn = JOptionPane.showInputDialog(null, "Olá, o motivo pelo qual você está reiniciando o "
                + "programa, é algum erro? Se sua resposta \nfor SIM, comente aí em baixo com detalhes, "
                + "se possível, qual foi esse erro. Obrigado! )");
        
        java.util.Date d = new Date();
        String dataatual = java.text.DateFormat.getDateInstance(DateFormat.DEFAULT).format(d);
        
        if(!"".equals(opn) | !" ".equals(opn) | opn != null){
            //Banco de Dados
            ConectionSQLite consqlite = new ConectionSQLite();
            Reiniciar reiniciar = new Reiniciar();
            
            reiniciar.setMotivo(opn);
            reiniciar.setMotivo_data(dataatual);
            
            consqlite.conectar();
            
            PreparedStatement stmt = consqlite.criarPreparedStatement("INSERT INTO reiniciar "
                    + "(motivo, motivo_data) VALUES (?,?);");
            
            try{
                stmt.setString(1, reiniciar.getMotivo());
                stmt.setString(2, reiniciar.getMotivo_data());
                
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Muito Obrigado. Sua opnião é muito importante para nós. ", 
                        " Feedback", JOptionPane.PLAIN_MESSAGE);
                
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Não foi possível enviar seu feedback. Reinicie o programa e"
                        + "tente novamente. ", " Error", JOptionPane.ERROR_MESSAGE);
            }finally{
                if(stmt != null){
                    try{
                        stmt.close();
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error 201. ", " Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            consqlite.desconectar();
        }
        
        this.dispose();
        new Tela1().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            
        }
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //Animação de cores com o mouse
        jPanel9.setVisible(false);
        jPanel10.setVisible(true);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(true);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(true);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(true);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        jPanel32.setVisible(true);
        
        //Deixar os outros jFrames invisível
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        jPanel9.setVisible(true);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(true);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(true);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(true);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir a Calculadora.");
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new Sobre().setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!jTextField51.getText().equals("") && !jFormattedTextField4.getText().equals("  /  /    ")
                && !jTextField56.getText().equals("")){
            String senhaum, senhadois;
            senhaum = String.valueOf(jPasswordField1.getPassword());
            senhadois = String.valueOf(jPasswordField2.getPassword());

            if(!senhaum.equals(senhadois)){
                jLabel7.setText("<html><font color = red>* As senhas não coincidem.</font></html>");
            }else{
               if(senhaum.equals("") && senhadois.equals("")){
                    jLabel7.setText("<html><font color = red>* As senhas não podem estar em branco.</font></html>");
                }else{
                    //BANCO DE DADOS
                    ConectionSQLite consqlite = new ConectionSQLite();
                    Usuario user = new Usuario();
                    
                    user.setNome(jTextField51.getText());
                    user.setNascimento(jFormattedTextField4.getText());
                    user.setTelefone(jFormattedTextField3.getText());
                    user.setUsuario(jTextField56.getText());
                    user.setSenha(String.valueOf(jPasswordField1.getPassword()));
                    
                    consqlite.conectar();
                    
                    String sqlInsertUsuario = "INSERT INTO usuario "
                            + "(nome, nascimento, telefone, usuario, senha) "
                            + "VALUES (?,?,?,?,?);";
                    
                    PreparedStatement stmt = consqlite.criarPreparedStatement(sqlInsertUsuario);
                    
                    try{
                        stmt.setString(1, user.getNome());
                        stmt.setString(2, user.getNascimento());
                        stmt.setString(3, user.getTelefone());
                        stmt.setString(4, user.getUsuario());
                        stmt.setString(5, user.getSenha());
                        
                        stmt.executeUpdate();
                        
                        //Limpar todos os campos
                        jTextField51.setText("");
                        jFormattedTextField4.setText("");
                        jFormattedTextField3.setText("");
                        jPasswordField1.setText("");
                        jPasswordField2.setText("");
                        jTextField56.setText("");
                        
                        JOptionPane.showMessageDialog(null, "O Usuário " + jTextField56.getText() + 
                            " foi cadastrado com Sucesso!", " Cadastro", JOptionPane.INFORMATION_MESSAGE);
                
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Não foi possível fazer o Cadastro do Usuário. \n"
                                + "Tente novamente. Error 65. ", " Erro de Inserção",JOptionPane.ERROR_MESSAGE);
                    } finally{
                        if(stmt != null){
                            try {
                                stmt.close();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, " Erro Inesperado. Error 62.",
                                    "Erro de Inserção", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                    consqlite.desconectar();
                    
               }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Alguns campos obrigatórios estão vazios. ", 
                    " Campos em branco", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField15FocusGained
        //Animação de cor do jTextField
        jTextField14.setBackground(cinzab);
        jTextField15.setBackground(Color.WHITE);
        
        //Apagar os dados dos campos
        jTextField14.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
    }//GEN-LAST:event_jTextField15FocusGained

    private void jTextField52KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField52KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField52KeyTyped

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField53KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField53KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField53KeyTyped

    private void jTextField23FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField23FocusLost
        if("".equals(jTextField23.getText())){
            jTextField23.setText("0");
        }
    }//GEN-LAST:event_jTextField23FocusLost

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        
        
        
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        jTextField14.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField15.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField22.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField23.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField24.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField25.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField26.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField27.setText("");
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            
        }
        jTextField28.setText("");
        jTextField29.setText("");
        jTextField30.setText("");
        jTextField31.setText("");
        jTextField32.setText("");
        jTextField33.setText("");
            
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained
        jTextField6.setBackground(cinzab);
        jTextField7.setBackground(Color.WHITE);
        jTextField6.setText("");
    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        String caracteres = "1234567890./-";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField53FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField53FocusGained
        if(jTextField53.getText().equals("Definido pelo Sistema")){
            jTextField53.setText("");
            jTextField53.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField53FocusGained

    private void jTextField53FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField53FocusLost
        if(jTextField53.getText().equals("")){
            jTextField53.setText("Definido pelo Sistema");
            jTextField53.setForeground(cinzac);
        }
    }//GEN-LAST:event_jTextField53FocusLost

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        int cpf0, cpf1, cpf2, cpf4, cpf5, cpf6, cpf8, cpf9, cpf10, cpf12, cpf13;
        
        cpf0 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(0)));
        cpf1 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(1)));
        cpf2 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(2)));
        cpf4 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(4)));
        cpf5 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(5)));
        cpf6 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(6)));
        cpf8 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(8)));
        cpf9 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(9)));
        cpf10 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(10)));
        cpf12 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(12)));
        cpf13 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(13)));
        
        //Calcular o primeiro digito verificador
        //Soma e divisão dos primeiros digitos verificadores
        int restosoma1 = (((cpf0 * 10) + (cpf1 * 9) + (cpf2 * 8) + (cpf4 * 7) + (cpf5 * 6) + (cpf6 * 5) + 
                (cpf8 * 4) + (cpf9 * 3) + (cpf10 * 2)) % 11);
        
        //Veerificar se o digito é válido
        if(restosoma1 < 2){
            if(cpf12 == 0){
                validarcpf1 = true;
            }else{
                validarcpf1 = false;
            }
        }else{
            if(11 - restosoma1 == cpf12){
                validarcpf1 = true;
            }else{
                validarcpf1 = false;
            }
        }
        
        //Calcular o segundo digito verificador
        //Soma e divisão dos primeiros digitos verificadores
        int restosoma2 = (((cpf0 * 11) + (cpf1 * 10) + (cpf2 * 9) + (cpf4 * 8) + (cpf5 * 7) + (cpf6 * 6) + 
                (cpf8 * 5) + (cpf9 * 4) + (cpf10 * 3) + (cpf12 * 2)) % 11);
        
        //Veerificar se o digito é válido
        if(restosoma2 < 2){
            if(cpf13 == 0){
                validarcpf2 = true;
            }else{
                validarcpf2 = false;
            }
        }else{
            if(11 - restosoma2 == cpf13){
                validarcpf2 = true;
            }else{
                validarcpf2 = false;
            }
        }
        
        //Validar CPF
        if(validarcpf1 == false || validarcpf2 == false){
            validarcpf3 = false;
            jFormattedTextField1.setForeground(Color.RED);
        }else{
            validarcpf3 = true;
            jFormattedTextField1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jFormattedTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusGained
        jFormattedTextField1.setForeground(Color.BLACK);
    }//GEN-LAST:event_jFormattedTextField1FocusGained

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jTextField1.setText(jTextField1.getText().toUpperCase());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField52KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField52KeyReleased
        jTextField52.setText(jTextField52.getText().toUpperCase());
    }//GEN-LAST:event_jTextField52KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        jTextField4.setText(jTextField4.getText().toUpperCase());
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        jTextField2.setText(jTextField2.getText().toUpperCase());
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        jTextField6.setText(jTextField6.getText().toUpperCase());
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        jTextField14.setText(jTextField14.getText().toUpperCase());
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jTextField51KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField51KeyReleased
        jTextField51.setText(jTextField51.getText().toUpperCase());
    }//GEN-LAST:event_jTextField51KeyReleased

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        new Ajuda().setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTextField51.setText("");
        jTextField56.setText("");
        jFormattedTextField3.setText("");
        jFormattedTextField4.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        boolean teclado = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (teclado) {
            jLabel77.setVisible(true);
        } else {
            jLabel77.setVisible(false);
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jPasswordField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField2KeyPressed
        boolean teclado = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (teclado) {
            jLabel77.setVisible(true);
        } else {
            jLabel77.setVisible(false);
        }
    }//GEN-LAST:event_jPasswordField2KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField52.setText("");
        jTextField53.setText("Definido pelo Sistema");
        jFormattedTextField1.setText("");
        jFormattedTextField2.setText("");
        jFormattedTextField5.setText("");
        
        jTextField53.setForeground(cinzac);
        jComboBox1.setSelectedIndex(0); 
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        if(jComboBox2.getSelectedItem() == "Dinheiro"){
            dinheiro.setVisible(true);
            cheque.setVisible(false);
            finalizar.setVisible(false);
            jTextField55.setText("Dinheiro");
            jLabel8.setVisible(false);
            
        }else{
            if(jComboBox2.getSelectedItem() == "Cheque"){
                dinheiro.setVisible(false);
                cheque.setVisible(true);
                finalizar.setVisible(false);
                jTextField55.setText("Cheque");
                jLabel8.setVisible(true);
                
            }else{
                dinheiro.setVisible(false);
                cheque.setVisible(false);
                finalizar.setVisible(false);
                jTextField5.setText("");
                
                jLabel8.setVisible(false);
            }
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField54FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField54FocusGained
        if(jTextField54.getText().equals("Continuar Sequência do BD")){
            jTextField54.setText("");
            jTextField54.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField54FocusGained

    private void jTextField54FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField54FocusLost
        if(jTextField54.getText().equals("") || jTextField54.getText().equals(" ")){
            jTextField54.setText("Continuar Sequência do BD");
            jTextField54.setForeground(cinzac);
        }
    }//GEN-LAST:event_jTextField54FocusLost

    private void jTextField58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField58ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        finalizar.setVisible(true);
        cheque.setVisible(false);
        dinheiro.setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        if("1".equals(jComboBox3.getSelectedItem())){
            //Numero do Cheque
            ncheque1.setVisible(true);
            ncheque2.setVisible(false);
            ncheque3.setVisible(false);
            ncheque4.setVisible(false);
            ncheque5.setVisible(false);
            //Data do Cheque
            dcheque1.setVisible(true);
            dcheque2.setVisible(false);
            dcheque3.setVisible(false);
            dcheque4.setVisible(false);
            dcheque5.setVisible(false);
            //Valor do Cheque
            vcheque1.setVisible(true);
            vcheque2.setVisible(false);
            vcheque3.setVisible(false);
            vcheque4.setVisible(false);
            vcheque5.setVisible(false);
        }else{
            if("2".equals(jComboBox3.getSelectedItem())){
            //Numero do Cheque
            ncheque1.setVisible(true);
            ncheque2.setVisible(true);
            ncheque3.setVisible(false);
            ncheque4.setVisible(false);
            ncheque5.setVisible(false);
            //Data do Cheque
            dcheque1.setVisible(true);
            dcheque2.setVisible(true);
            dcheque3.setVisible(false);
            dcheque4.setVisible(false);
            dcheque5.setVisible(false);
            //Valor do Cheque
            vcheque1.setVisible(true);
            vcheque2.setVisible(true);
            vcheque3.setVisible(false);
            vcheque4.setVisible(false);
            vcheque5.setVisible(false);
        }else{
            if("3".equals(jComboBox3.getSelectedItem())){
            //Numero do Cheque
            ncheque1.setVisible(true);
            ncheque2.setVisible(true);
            ncheque3.setVisible(true);
            ncheque4.setVisible(false);
            ncheque5.setVisible(false);
            //Data do Cheque
            dcheque1.setVisible(true);
            dcheque2.setVisible(true);
            dcheque3.setVisible(true);
            dcheque4.setVisible(false);
            dcheque5.setVisible(false);
            //Valor do Cheque
            vcheque1.setVisible(true);
            vcheque2.setVisible(true);
            vcheque3.setVisible(true);
            vcheque4.setVisible(false);
            vcheque5.setVisible(false);
        }else{
            if("4".equals(jComboBox3.getSelectedItem())){
            //Numero do Cheque
            ncheque1.setVisible(true);
            ncheque2.setVisible(true);
            ncheque3.setVisible(true);
            ncheque4.setVisible(true);
            ncheque5.setVisible(false);
            //Data do Cheque
            dcheque1.setVisible(true);
            dcheque2.setVisible(true);
            dcheque3.setVisible(true);
            dcheque4.setVisible(true);
            dcheque5.setVisible(false);
            //Valor do Cheque
            vcheque1.setVisible(true);
            vcheque2.setVisible(true);
            vcheque3.setVisible(true);
            vcheque4.setVisible(true);
            vcheque5.setVisible(false);
        }else{
            if("5".equals(jComboBox3.getSelectedItem())){
            //Numero do Cheque
            ncheque1.setVisible(true);
            ncheque2.setVisible(true);
            ncheque3.setVisible(true);
            ncheque4.setVisible(true);
            ncheque5.setVisible(true);
            //Data do Cheque
            dcheque1.setVisible(true);
            dcheque2.setVisible(true);
            dcheque3.setVisible(true);
            dcheque4.setVisible(true);
            dcheque5.setVisible(true);
            //Valor do Cheque
            vcheque1.setVisible(true);
            vcheque2.setVisible(true);
            vcheque3.setVisible(true);
            vcheque4.setVisible(true);
            vcheque5.setVisible(true);
        }else{
            //Numero do Cheque
            ncheque1.setVisible(false);
            ncheque2.setVisible(false);
            ncheque3.setVisible(false);
            ncheque4.setVisible(false);
            ncheque5.setVisible(false);
            //Data do Cheque
            dcheque1.setVisible(false);
            dcheque2.setVisible(false);
            dcheque3.setVisible(false);
            dcheque4.setVisible(false);
            dcheque5.setVisible(false);
            //Valor do Cheque
            vcheque1.setVisible(false);
            vcheque2.setVisible(false);
            vcheque3.setVisible(false);
            vcheque4.setVisible(false);
            vcheque5.setVisible(false);
        }}}}}
        
        if("".equals(jComboBox3.getSelectedItem())){
            jLabel8.setVisible(true);
        }
        if(!"".equals(jComboBox3.getSelectedItem())){
            jLabel8.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        finalizar.setVisible(true);
        cheque.setVisible(false);
        dinheiro.setVisible(false);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //Banco de Dados
        ConectionSQLite consqlite = new ConectionSQLite();
        Recibo recibo = new Recibo();
        Cheque cheque = new Cheque();
        Dinheiro dinheiro = new Dinheiro();
        
        int id = 1;
        boolean continuar = false;
        
        if("Dinheiro".equals(jComboBox2.getSelectedItem())){
            while(continuar == false){
                try{
                    dinheiro.setValor(Double.parseDouble(jTextField3.getText()));
                    dinheiro.setTroco_para(Double.parseDouble(jTextField59.getText()));
                    dinheiro.setValor_troco(Double.parseDouble(jTextField61.getText()));
                    dinheiro.setObs(jTextField60.getText());
                    dinheiro.setId_dinheiro(id);
                    
                    recibo.setCpf_cooperado(jTextField15.getText());
                    recibo.setValor_da_nfe(Double.parseDouble(jTextField22.getText()));
                    recibo.setPorcentagem_do_inss(Double.parseDouble(jTextField23.getText()));
                    recibo.setValor_do_inss(Double.parseDouble(jTextField25.getText()));
                    recibo.setObs_do_inss(jTextField26.getText());
                    recibo.setTaxa_de_adm(Double.parseDouble(jTextField27.getText()));
                    recibo.setIntegralizacao(Double.parseDouble(jTextField28.getText()));
                    recibo.setMensalidades(Double.parseDouble(jTextField29.getText()));
                    recibo.setDoacoes(Double.parseDouble(jTextField30.getText()));
                    recibo.setRateio_de_perdas(Double.parseDouble(jTextField31.getText()));
                    recibo.setValor_total_repasse(Double.parseDouble(jTextField32.getText()));
                    recibo.setValor_total_liquido(Double.parseDouble(jTextField33.getText()));
                    recibo.setForma_de_pagamento("Dinheiro");
                    recibo.setId_cheque(id);
                    recibo.setId_dinheiro(id);
                    recibo.setData_da_nfe(jTextField58.getText());
                    
                    consqlite.conectar();
                    
                    String sqlInsertRecibo = "INSERT INTO recibo "
                            + "(cpf_cooperado, valor_da_nfe, data_da_nfe, porcentagem_do_inss, valor_do_inss, "
                            + "obs_do_inss, taxa_de_adm, integralizacao, mensalidades, doacoes, rateio_de_perdas, "
                            + "valor_total_repasse, valor_total_liquido, forma_de_pagamento, id_dinheiro,"
                            + " data_da_nfe) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    
                    String sqlInsertDinheiro = "INSERT INTO dinheiro "
                            + "(id_dinheiro, valor_total, valor_pagamento, valor_troco, observacao) "
                            + "VALUES (?,?,?,?,?);";
                    
                    PreparedStatement stmtR = consqlite.criarPreparedStatement(sqlInsertRecibo);
                    PreparedStatement stmtD = consqlite.criarPreparedStatement(sqlInsertDinheiro);
                    
                    try{
                        stmtR.setString(1, recibo.getCpf_cooperado());
                        stmtR.setDouble(2, recibo.getValor_da_nfe());
                        stmtR.setString(3, recibo.getData_da_nfe());
                        stmtR.setDouble(4, recibo.getPorcentagem_do_inss());
                        stmtR.setDouble(5, recibo.getValor_do_inss());
                        stmtR.setString(6, recibo.getObs_do_inss());
                        stmtR.setDouble(7, recibo.getTaxa_de_adm());
                        stmtR.setDouble(8, recibo.getIntegralizacao());
                        stmtR.setDouble(9, recibo.getMensalidades());
                        stmtR.setDouble(10, recibo.getDoacoes());
                        stmtR.setDouble(11, recibo.getRateio_de_perdas());
                        stmtR.setDouble(12, recibo.getValor_total_repasse());
                        stmtR.setDouble(13, recibo.getValor_total_liquido());
                        stmtR.setString(14, recibo.getForma_de_pagamento());
                        stmtR.setInt(15, recibo.getId_dinheiro());
                        stmtR.setString(16, recibo.getData_da_nfe());
                        
                        stmtD.setInt(1, id);
                        stmtD.setDouble(2, dinheiro.getValor());
                        stmtD.setDouble(3, dinheiro.getTroco_para());
                        stmtD.setDouble(4, dinheiro.getValor_troco());
                        if(!"".equals(dinheiro.getObs()) || !" ".equals(dinheiro.getObs())){
                            stmtD.setString(5, dinheiro.getObs());
                        }
                        
                        stmtD.executeUpdate();
                        stmtR.executeUpdate();
                        
                        continuar = true;
                        
                        consqlite.desconectar();
                        
                        JOptionPane.showMessageDialog(null, "O Recibo foi adicionado com Sucesso! "
                                + "O mesmo foi pago em Dinheiro. \n" + "O Valor Total Liquido é de " 
                                + jTextField57.getText() + " R$.", " Recibo Cadastrado", JOptionPane.INFORMATION_MESSAGE);
                        
                        //Limpar os campos do Dinheiro
                        jTextField59.setText("");
                        jTextField60.setText("");
                        jTextField61.setText("");
                        
                        //Voltar para o Painel Inicial do Cadastro de Recibo
                        jPanel33.setVisible(false);
                        jPanel21.setVisible(true);
                        
                    } catch(SQLException ex){
                        id ++;
                    }
                    
                } catch(Error | Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro inesperado. Error 81. \n" + ex,
                            " Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }else{
            while(continuar == false){
                try{
                    int quantidade = jComboBox3.getSelectedIndex();
                    String sqlInsertCheque = "";
                    
                    if(quantidade == 1){
                        cheque.setId_cheque(id);
                        cheque.setQuantidade(1);
                        
                        cheque.setNchequeum(Integer.parseInt(ncheque1.getText()));
                        cheque.setDchequeum(dcheque1.getText());
                        cheque.setVchequeum(Double.parseDouble(vcheque1.getText()));
                        
                        sqlInsertCheque = "INSERT INTO cheque "
                                + "(id_cheque, quantidade, nchequeum, datachequeum, valorchequeum) "
                                + "VALUES (?,?,?,?,?);";
                        
                    }
                    if(quantidade == 2){
                        cheque.setId_cheque(id);
                        cheque.setQuantidade(2);
                        
                        cheque.setNchequeum(Integer.parseInt(ncheque1.getText()));
                        cheque.setDchequeum(dcheque1.getText());
                        cheque.setVchequeum(Double.parseDouble(vcheque1.getText()));
                        
                        cheque.setNchequedois(Integer.parseInt(ncheque2.getText()));
                        cheque.setDchequedois(dcheque2.getText());
                        cheque.setVchequedois(Double.parseDouble(vcheque2.getText()));
                        
                        sqlInsertCheque = "INSERT INTO cheque "
                                + "(id_cheque, quantidade, nchequeum, datachequeum, valorchequeum, "
                                + "nchequedois, datachequedois, valorchequedois) "
                                + "VALUES (?,?,?,?,?,?,?,?);";
                    }
                    if(quantidade == 3){
                        cheque.setId_cheque(id);
                        cheque.setQuantidade(3);
                        
                        cheque.setNchequeum(Integer.parseInt(ncheque1.getText()));
                        cheque.setDchequeum(dcheque1.getText());
                        cheque.setVchequeum(Double.parseDouble(vcheque1.getText()));
                        
                        cheque.setNchequedois(Integer.parseInt(ncheque2.getText()));
                        cheque.setDchequedois(dcheque2.getText());
                        cheque.setVchequedois(Double.parseDouble(vcheque2.getText()));
                        
                        cheque.setNchequetres(Integer.parseInt(ncheque3.getText()));
                        cheque.setDchequetres(dcheque3.getText());
                        cheque.setVchequetres(Double.parseDouble(vcheque3.getText()));
                        
                        sqlInsertCheque = "INSERT INTO cheque "
                                + "(id_cheque, quantidade, nchequeum, datachequeum, valorchequeum, "
                                + "nchequedois, datachequedois, valorchequedois, nchequetres, datachequetres, "
                                + "valorchequetres) "
                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
                    }
                    if(quantidade == 4){
                        cheque.setId_cheque(id);
                        cheque.setQuantidade(4);
                        
                        cheque.setNchequeum(Integer.parseInt(ncheque1.getText()));
                        cheque.setDchequeum(dcheque1.getText());
                        cheque.setVchequeum(Double.parseDouble(vcheque1.getText()));
                        
                        cheque.setNchequedois(Integer.parseInt(ncheque2.getText()));
                        cheque.setDchequedois(dcheque2.getText());
                        cheque.setVchequedois(Double.parseDouble(vcheque2.getText()));
                        
                        cheque.setNchequetres(Integer.parseInt(ncheque3.getText()));
                        cheque.setDchequetres(dcheque3.getText());
                        cheque.setVchequetres(Double.parseDouble(vcheque3.getText()));
                        
                        cheque.setNchequequatro(Integer.parseInt(ncheque4.getText()));
                        cheque.setDchequequatro(dcheque4.getText());
                        cheque.setVchequequatro(Double.parseDouble(vcheque4.getText()));
                        
                        sqlInsertCheque = "INSERT INTO cheque "
                                + "(id_cheque, quantidade, nchequeum, datachequeum, valorchequeum, "
                                + "nchequedois, datachequedois, valorchequedois, nchequetres, datachequetres, "
                                + "valorchequetres, nchequequatro, datachequequatro, valorchequequatro) "
                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    }
                    if(quantidade == 5){
                        cheque.setId_cheque(id);
                        cheque.setQuantidade(5);
                        
                        cheque.setNchequeum(Integer.parseInt(ncheque1.getText()));
                        cheque.setDchequeum(dcheque1.getText());
                        cheque.setVchequeum(Double.parseDouble(vcheque1.getText()));
                        
                        cheque.setNchequedois(Integer.parseInt(ncheque2.getText()));
                        cheque.setDchequedois(dcheque2.getText());
                        cheque.setVchequedois(Double.parseDouble(vcheque2.getText()));
                        
                        cheque.setNchequetres(Integer.parseInt(ncheque3.getText()));
                        cheque.setDchequetres(dcheque3.getText());
                        cheque.setVchequetres(Double.parseDouble(vcheque3.getText()));
                        
                        cheque.setNchequequatro(Integer.parseInt(ncheque4.getText()));
                        cheque.setDchequequatro(dcheque4.getText());
                        cheque.setVchequequatro(Double.parseDouble(vcheque4.getText()));
                        
                        cheque.setNchequecinco(Integer.parseInt(ncheque5.getText()));
                        cheque.setDchequecinco(dcheque5.getText());
                        cheque.setVchequecinco(Double.parseDouble(vcheque5.getText()));
                        
                        sqlInsertCheque = "INSERT INTO cheque "
                                + "(id_cheque, quantidade, nchequeum, datachequeum, valorchequeum, "
                                + "nchequedois, datachequedois, valorchequedois, nchequetres, datachequetres, "
                                + "valorchequetres, nchequequatro, datachequequatro, valorchequequatro, nchequecinco, "
                                + "datachequecinco, valorchequecinco) "
                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    }
                    
                    recibo.setCpf_cooperado(jTextField15.getText());
                    recibo.setValor_da_nfe(Double.parseDouble(jTextField22.getText()));
                    recibo.setPorcentagem_do_inss(Double.parseDouble(jTextField23.getText()));
                    recibo.setValor_do_inss(Double.parseDouble(jTextField25.getText()));
                    recibo.setObs_do_inss(jTextField26.getText());
                    recibo.setTaxa_de_adm(Double.parseDouble(jTextField27.getText()));
                    recibo.setIntegralizacao(Double.parseDouble(jTextField28.getText()));
                    recibo.setMensalidades(Double.parseDouble(jTextField29.getText()));
                    recibo.setDoacoes(Double.parseDouble(jTextField30.getText()));
                    recibo.setRateio_de_perdas(Double.parseDouble(jTextField31.getText()));
                    recibo.setValor_total_repasse(Double.parseDouble(jTextField32.getText()));
                    recibo.setValor_total_liquido(Double.parseDouble(jTextField33.getText()));
                    recibo.setForma_de_pagamento("Cheque");
                    recibo.setId_cheque(id);
                    recibo.setId_dinheiro(id);
                    recibo.setData_da_nfe(jTextField58.getText());
                    
                    consqlite.conectar();
                    
                    String sqlInsertRecibo = "INSERT INTO recibo "
                            + "(cpf_cooperado, valor_da_nfe, data_da_nfe, porcentagem_do_inss, valor_do_inss, "
                            + "obs_do_inss, taxa_de_adm, integralizacao, mensalidades, doacoes, rateio_de_perdas, "
                            + "valor_total_repasse, valor_total_liquido, forma_de_pagamento, id_cheque, data_da_nfe) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    
                    PreparedStatement stmtR = consqlite.criarPreparedStatement(sqlInsertRecibo);
                    PreparedStatement stmtC = consqlite.criarPreparedStatement(sqlInsertCheque);
                    
                    try{
                        stmtR.setString(1, recibo.getCpf_cooperado());
                        stmtR.setDouble(2, recibo.getValor_da_nfe());
                        stmtR.setString(3, recibo.getData_da_nfe());
                        stmtR.setDouble(4, recibo.getPorcentagem_do_inss());
                        stmtR.setDouble(5, recibo.getValor_do_inss());
                        stmtR.setString(6, recibo.getObs_do_inss());
                        stmtR.setDouble(7, recibo.getTaxa_de_adm());
                        stmtR.setDouble(8, recibo.getIntegralizacao());
                        stmtR.setDouble(9, recibo.getMensalidades());
                        stmtR.setDouble(10, recibo.getDoacoes());
                        stmtR.setDouble(11, recibo.getRateio_de_perdas());
                        stmtR.setDouble(12, recibo.getValor_total_repasse());
                        stmtR.setDouble(13, recibo.getValor_total_liquido());
                        stmtR.setString(14, recibo.getForma_de_pagamento());
                        stmtR.setInt(15, recibo.getId_cheque());
                        stmtR.setString(16, recibo.getData_da_nfe());
                        
                        if(quantidade == 1){
                            stmtC.setInt(1, id);
                            stmtC.setInt(2, 1);
                            stmtC.setInt(3, cheque.getNchequeum());
                            stmtC.setString(4, cheque.getDchequeum());
                            stmtC.setDouble(5, cheque.getVchequeum());
                        }
                        if(quantidade == 2){
                            stmtC.setInt(1, id);
                            stmtC.setInt(2, 2);
                            stmtC.setInt(3, cheque.getNchequeum());
                            stmtC.setString(4, cheque.getDchequeum());
                            stmtC.setDouble(5, cheque.getVchequeum());
                            stmtC.setInt(6, cheque.getNchequedois());
                            stmtC.setString(7, cheque.getDchequedois());
                            stmtC.setDouble(8, cheque.getVchequedois());
                        }
                        if(quantidade == 3){
                            stmtC.setInt(1, id);
                            stmtC.setInt(2, 3);
                            stmtC.setInt(3, cheque.getNchequeum());
                            stmtC.setString(4, cheque.getDchequeum());
                            stmtC.setDouble(5, cheque.getVchequeum());
                            stmtC.setInt(6, cheque.getNchequedois());
                            stmtC.setString(7, cheque.getDchequedois());
                            stmtC.setDouble(8, cheque.getVchequedois());
                            stmtC.setInt(9, cheque.getNchequetres());
                            stmtC.setString(10, cheque.getDchequetres());
                            stmtC.setDouble(11, cheque.getVchequetres());
                        }
                        if(quantidade == 4){
                            stmtC.setInt(1, id);
                            stmtC.setInt(2, 4);
                            stmtC.setInt(3, cheque.getNchequeum());
                            stmtC.setString(4, cheque.getDchequeum());
                            stmtC.setDouble(5, cheque.getVchequeum());
                            stmtC.setInt(6, cheque.getNchequedois());
                            stmtC.setString(7, cheque.getDchequedois());
                            stmtC.setDouble(8, cheque.getVchequedois());
                            stmtC.setInt(9, cheque.getNchequetres());
                            stmtC.setString(10, cheque.getDchequetres());
                            stmtC.setDouble(11, cheque.getVchequetres());
                            stmtC.setInt(12, cheque.getNchequequatro());
                            stmtC.setString(13, cheque.getDchequequatro());
                            stmtC.setDouble(14, cheque.getVchequequatro());
                        }
                        if(quantidade == 5){
                            stmtC.setInt(1, id);
                            stmtC.setInt(2, 5);
                            stmtC.setInt(3, cheque.getNchequeum());
                            stmtC.setString(4, cheque.getDchequeum());
                            stmtC.setDouble(5, cheque.getVchequeum());
                            stmtC.setInt(6, cheque.getNchequedois());
                            stmtC.setString(7, cheque.getDchequedois());
                            stmtC.setDouble(8, cheque.getVchequedois());
                            stmtC.setInt(9, cheque.getNchequetres());
                            stmtC.setString(10, cheque.getDchequetres());
                            stmtC.setDouble(11, cheque.getVchequetres());
                            stmtC.setInt(12, cheque.getNchequequatro());
                            stmtC.setString(13, cheque.getDchequequatro());
                            stmtC.setDouble(14, cheque.getVchequequatro());
                            stmtC.setInt(15, cheque.getNchequecinco());
                            stmtC.setString(16, cheque.getDchequecinco());
                            stmtC.setDouble(17, cheque.getVchequecinco());
                        }
                        
                        stmtC.executeUpdate();
                        stmtR.executeUpdate();
                        
                        continuar = true;
                        
                        consqlite.desconectar();
                        
                        JOptionPane.showMessageDialog(null, "O Recibo foi adicionado com Sucesso! "
                                + "O mesmo foi pago em Cheque. \n" + "O Valor Total Liquido é de " 
                                + jTextField57.getText() + " R$.", " Recibo Cadastrado", JOptionPane.INFORMATION_MESSAGE);
                        
                        //Limpar os campos de Cheque
                        ncheque1.setText("");
                        ncheque2.setText("");
                        ncheque3.setText("");
                        ncheque4.setText("");
                        ncheque5.setText("");
                        dcheque1.setText("");
                        dcheque2.setText("");
                        dcheque3.setText("");
                        dcheque4.setText("");
                        dcheque5.setText("");
                        vcheque1.setText("");
                        vcheque2.setText("");
                        vcheque3.setText("");
                        vcheque4.setText("");
                        vcheque5.setText("");
                        
                        //Voltar para o Painel Inicial do Cadastro de Recibo
                        jPanel33.setVisible(false);
                        jPanel21.setVisible(true);
                        
                    } catch(SQLException ex){
                        id ++;
                    }
                    
                } catch(Error | Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro inesperado. Error 82. \n" + ex,
                            " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(validarcpf3 == true){
            //Banco de Dados
            ConectionSQLite consqlite = new ConectionSQLite();
            Cooperado cooperado = new Cooperado();
            
            boolean num_insc = false;
            
            cooperado.setNome(jTextField1.getText());
            cooperado.setCpf(jFormattedTextField1.getText());
            cooperado.setTelefone(jFormattedTextField2.getText());
            if(!"Definido pelo Sistema".equals(jTextField53.getText())){
                cooperado.setN_inscricao(Integer.parseInt(jTextField53.getText()));
                num_insc = true;
            }
            cooperado.setUf(String.valueOf(jComboBox1.getSelectedItem()));
            cooperado.setCep(jFormattedTextField5.getText());
            cooperado.setCidade(jTextField52.getText());
            cooperado.setBairro(jTextField4.getText());
            cooperado.setRua(jTextField2.getText());
            cooperado.setNumero(Integer.parseInt(jTextField5.getText()));
            
            consqlite.conectar();
            
            String sqlInsertCooperado1 = "INSERT INTO cooperado "
                    + "(nome, cpf, telefone, n_inscricao, cep, uf, cidade, bairro, rua, numero) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);";
            
            String sqlInsertCooperado2 = "INSERT INTO cooperado "
                    + "(nome, cpf, telefone, cep, uf, cidade, bairro, rua, numero) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);";
            
            if(num_insc == true){
                PreparedStatement stmt = consqlite.criarPreparedStatement(sqlInsertCooperado1);
                
                try{
                    stmt.setString(1, cooperado.getNome());
                    stmt.setString(2, cooperado.getCpf());
                    stmt.setString(3, cooperado.getTelefone());
                    stmt.setInt(4, cooperado.getN_inscricao());
                    stmt.setString(5, cooperado.getCep());
                    stmt.setString(6, cooperado.getUf());
                    stmt.setString(7, cooperado.getCidade());
                    stmt.setString(8, cooperado.getBairro());
                    stmt.setString(9, cooperado.getRua());
                    stmt.setInt(10, cooperado.getNumero());
                    
                    stmt.executeUpdate();
                    
                    //Limpar os Campos
                    jComboBox1.setSelectedIndex(0);
                    jTextField1.setText("");
                    jFormattedTextField1.setText("");
                    jFormattedTextField2.setText("");
                    jTextField53.setText("Definido pelo Sistema");
                    jTextField53.setForeground(cinzac);
                    jFormattedTextField5.setText("");
                    jTextField52.setText("");
                    jTextField4.setText("");
                    jTextField2.setText("");
                    jTextField5.setText("");
                    
                    JOptionPane.showMessageDialog(null, "<html>O Cooperado <b>" + jTextField1.getText() + 
                            "</b> foi cadastrsdo com Sucesso!</html>", " Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch(SQLException ex){
                    System.out.println("tenta 4");
                }finally{
                    if(stmt != null){
                        try{
                            stmt.close();
                        } catch(SQLException ex){
                            System.out.println("tenta 3");
                        }
                    }
                }
            }else{
                PreparedStatement stmt = consqlite.criarPreparedStatement(sqlInsertCooperado2);
                
                try{
                    stmt.setString(1, cooperado.getNome());
                    stmt.setString(2, cooperado.getCpf());
                    stmt.setString(3, cooperado.getTelefone());
                    stmt.setString(4, cooperado.getCep());
                    stmt.setString(5, cooperado.getUf());
                    stmt.setString(6, cooperado.getCidade());
                    stmt.setString(7, cooperado.getBairro());
                    stmt.setString(8, cooperado.getRua());
                    stmt.setInt(9, cooperado.getNumero());
                    
                    stmt.executeUpdate();
                    
                    //Limpar os Campos
                    jTextField1.setText("");
                    jFormattedTextField1.setText("");
                    jFormattedTextField2.setText("");
                    jTextField53.setText("Definido pelo Sistema");
                    jTextField53.setForeground(cinzac);
                    jComboBox1.setSelectedIndex(0);
                    jFormattedTextField5.setText("");
                    jTextField52.setText("");
                    jTextField4.setText("");
                    jTextField2.setText("");
                    jTextField5.setText("");
                    
                    JOptionPane.showMessageDialog(null, "<html>O Cooperado <b>" + jTextField1.getText() + 
                            "</b> foi cadastrsdo com Sucesso!</html>", " Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch(SQLException ex){
                    System.out.println("tenta 2");
                }finally{
                    if(stmt != null){
                        try{
                            stmt.close();
                        } catch(SQLException ex){
                            System.out.println("tenta 1");
                        }
                    }
                }
                
                consqlite.desconectar();
                consqlite.desconectar();
                consqlite.desconectar();
                
                num_insc = false;

            }
            
            //Atualizar a Tabela com as Cooperativas
//            readJTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ConectionSQLite consqlite = new ConectionSQLite();
        
        ResultSet resultset = null;
        Statement stmt = null;
        
        consqlite.conectar();
        
        if(!"".equals(jTextField14.getText())){
            String sqlBuscaCoop = "SELECT * FROM cooperado WHERE nome LIKE ?";
            
            PreparedStatement stmts = consqlite.criarPreparedStatement(sqlBuscaCoop);
            
            try{
                stmts.setString(1, "%" + jTextField14.getText() + "%");
                resultset = stmts.executeQuery();

                if(resultset.getString("nome") != null){
                    while(resultset.next()){
                        jTextField15.setText(resultset.getString("cpf"));
                        jTextField16.setText(resultset.getString("rua"));
                        jTextField17.setText(resultset.getString("bairro"));
                        jTextField18.setText(resultset.getString("cidade"));
                        jTextField19.setText(resultset.getString("numero"));
                        if("Acre".equals(resultset.getString("uf"))){
                            jTextField20.setText("AC");
                        }
                        if("Alagoas".equals(resultset.getString("uf"))){
                            jTextField20.setText("AL");
                        }
                        if("Amapá".equals(resultset.getString("uf"))){
                            jTextField20.setText("AP");
                        }
                        if("Amazonas".equals(resultset.getString("uf"))){
                            jTextField20.setText("AM");
                        }
                        if("Bahia".equals(resultset.getString("uf"))){
                            jTextField20.setText("BA");
                        }
                        if("Ceará".equals(resultset.getString("uf"))){
                            jTextField20.setText("CE");
                        }
                        if("Distrito Federal".equals(resultset.getString("uf"))){
                            jTextField20.setText("DF");
                        }
                        if("Espírito Santo".equals(resultset.getString("uf"))){
                            jTextField20.setText("ES");
                        }
                        if("Goiás".equals(resultset.getString("uf"))){
                            jTextField20.setText("GO");
                        }
                        if("Maranhão".equals(resultset.getString("uf"))){
                            jTextField20.setText("MA");
                        }
                        if("Mato Grosso".equals(resultset.getString("uf"))){
                            jTextField20.setText("MT");
                        }
                        if("Mato Grosso do Sul".equals(resultset.getString("uf"))){
                            jTextField20.setText("MS");
                        }
                        if("Minas Gerais".equals(resultset.getString("uf"))){
                            jTextField20.setText("MG");
                        }
                        if("Pará".equals(resultset.getString("uf"))){
                            jTextField20.setText("PA");
                        }
                        if("Paraíba".equals(resultset.getString("uf"))){
                            jTextField20.setText("PB");
                        }
                        if("Paraná".equals(resultset.getString("uf"))){
                            jTextField20.setText("PR");
                        }
                        if("Pernambuco".equals(resultset.getString("uf"))){
                            jTextField20.setText("PE");
                        }
                        if("Piauí".equals(resultset.getString("uf"))){
                            jTextField20.setText("PI");
                        }
                        if("Rio de Janeiro".equals(resultset.getString("uf"))){
                            jTextField20.setText("RJ");
                        }
                        if("Rio Grande do Norte".equals(resultset.getString("uf"))){
                            jTextField20.setText("RN");
                        }
                        if("Rio Grande do Sul".equals(resultset.getString("uf"))){
                            jTextField20.setText("RS");
                        }
                        if("Rondônia".equals(resultset.getString("uf"))){
                            jTextField20.setText("RO");
                        }
                        if("Roraima".equals(resultset.getString("uf"))){
                            jTextField20.setText("RR");
                        }
                        if("Santa Catarina".equals(resultset.getString("uf"))){
                            jTextField20.setText("SC");
                        }
                        if("São Paulo".equals(resultset.getString("uf"))){
                            jTextField20.setText("SP");
                        }
                        if("Sergipe".equals(resultset.getString("uf"))){
                            jTextField20.setText("SE");
                        }
                        if("Tocantins".equals(resultset.getString("uf"))){
                            jTextField20.setText("TO");
                        }
                        jTextField21.setText(resultset.getString("telefone"));
                    }
                }
            } catch(SQLException ex){
                jTextField14.setForeground(Color.RED);
            }
        }else{
            if(!"   .   .   -  ".equals(jTextField15.getText())){
                String sqlBuscaCoop = "SELECT * FROM cooperado WHERE cpf LIKE ?";

                PreparedStatement stmts = consqlite.criarPreparedStatement(sqlBuscaCoop);

                try{
                    stmts.setString(1, jTextField15.getText());
                    resultset = stmts.executeQuery();

                    if(resultset.getString("cpf") != null){
                        while(resultset.next()){
                            jTextField14.setText(resultset.getString("nome"));
                            jTextField16.setText(resultset.getString("rua"));
                            jTextField17.setText(resultset.getString("bairro"));
                            jTextField18.setText(resultset.getString("cidade"));
                            jTextField19.setText(resultset.getString("numero"));
                            if("Acre".equals(resultset.getString("uf"))){
                                jTextField20.setText("AC");
                            }
                            if("Alagoas".equals(resultset.getString("uf"))){
                                jTextField20.setText("AL");
                            }
                            if("Amapá".equals(resultset.getString("uf"))){
                                jTextField20.setText("AP");
                            }
                            if("Amazonas".equals(resultset.getString("uf"))){
                                jTextField20.setText("AM");
                            }
                            if("Bahia".equals(resultset.getString("uf"))){
                                jTextField20.setText("BA");
                            }
                            if("Ceará".equals(resultset.getString("uf"))){
                                jTextField20.setText("CE");
                            }
                            if("Distrito Federal".equals(resultset.getString("uf"))){
                                jTextField20.setText("DF");
                            }
                            if("Espírito Santo".equals(resultset.getString("uf"))){
                                jTextField20.setText("ES");
                            }
                            if("Goiás".equals(resultset.getString("uf"))){
                                jTextField20.setText("GO");
                            }
                            if("Maranhão".equals(resultset.getString("uf"))){
                                jTextField20.setText("MA");
                            }
                            if("Mato Grosso".equals(resultset.getString("uf"))){
                                jTextField20.setText("MT");
                            }
                            if("Mato Grosso do Sul".equals(resultset.getString("uf"))){
                                jTextField20.setText("MS");
                            }
                            if("Minas Gerais".equals(resultset.getString("uf"))){
                                jTextField20.setText("MG");
                            }
                            if("Pará".equals(resultset.getString("uf"))){
                                jTextField20.setText("PA");
                            }
                            if("Paraíba".equals(resultset.getString("uf"))){
                                jTextField20.setText("PB");
                            }
                            if("Paraná".equals(resultset.getString("uf"))){
                                jTextField20.setText("PR");
                            }
                            if("Pernambuco".equals(resultset.getString("uf"))){
                                jTextField20.setText("PE");
                            }
                            if("Piauí".equals(resultset.getString("uf"))){
                                jTextField20.setText("PI");
                            }
                            if("Rio de Janeiro".equals(resultset.getString("uf"))){
                                jTextField20.setText("RJ");
                            }
                            if("Rio Grande do Norte".equals(resultset.getString("uf"))){
                                jTextField20.setText("RN");
                            }
                            if("Rio Grande do Sul".equals(resultset.getString("uf"))){
                                jTextField20.setText("RS");
                            }
                            if("Rondônia".equals(resultset.getString("uf"))){
                                jTextField20.setText("RO");
                            }
                            if("Roraima".equals(resultset.getString("uf"))){
                                jTextField20.setText("RR");
                            }
                            if("Santa Catarina".equals(resultset.getString("uf"))){
                                jTextField20.setText("SC");
                            }
                            if("São Paulo".equals(resultset.getString("uf"))){
                                jTextField20.setText("SP");
                            }
                            if("Sergipe".equals(resultset.getString("uf"))){
                                jTextField20.setText("SE");
                            }
                            if("Tocantins".equals(resultset.getString("uf"))){
                                jTextField20.setText("TO");
                            }
                            jTextField21.setText(resultset.getString("telefone"));
                        }
                    }
                } catch(SQLException ex){
                    jTextField14.setForeground(Color.RED);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Digite alguma informação sobre o Cooperado para "
                        + "podermos fazer a busca.", " Campo Vazio", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        consqlite.desconectar();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField6CaretUpdate
        if("".equals(jTextField6.getText())){
            readForDescCooperado();
        }else{
            readForDescCooperadoNome(jTextField6.getText());
        }
    }//GEN-LAST:event_jTextField6CaretUpdate

    private void jTextField7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField7CaretUpdate
        readForDescCooperadoCpf(jTextField7.getText());
    }//GEN-LAST:event_jTextField7CaretUpdate

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(!"   .   .   -  ".equals(jTextField8.getText())){
            readForDescBuscarBuscaReciboCPF(jTextField8.getText());
            readForDescCoop(jTextField8.getText());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jCheckBox1.setVisible(true);
        if(jCheckBox1.isSelected() == true){
            
        }else{
            
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField59KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField59KeyTyped
        String caracteres = ".1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField59KeyTyped

    private void jTextField55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField55ActionPerformed

    private void ncheque1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncheque1KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_ncheque1KeyTyped

    private void vcheque1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcheque1KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_vcheque1KeyTyped

    private void vcheque2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcheque2KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_vcheque2KeyTyped

    private void vcheque3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcheque3KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_vcheque3KeyTyped

    private void vcheque4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcheque4KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_vcheque4KeyTyped

    private void vcheque5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vcheque5KeyTyped
        String caracteres = "1234567890.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_vcheque5KeyTyped

    private void ncheque2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncheque2KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_ncheque2KeyTyped

    private void ncheque3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncheque3KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_ncheque3KeyTyped

    private void ncheque4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncheque4KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_ncheque4KeyTyped

    private void ncheque5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncheque5KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_ncheque5KeyTyped

    private void vcheque1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vcheque1FocusLost
        vcheque1.setText(String.valueOf(Double.parseDouble(vcheque1.getText())));
    }//GEN-LAST:event_vcheque1FocusLost

    private void vcheque2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vcheque2FocusLost
        vcheque2.setText(String.valueOf(Double.parseDouble(vcheque2.getText())));
    }//GEN-LAST:event_vcheque2FocusLost

    private void vcheque3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vcheque3FocusLost
        vcheque3.setText(String.valueOf(Double.parseDouble(vcheque3.getText())));
    }//GEN-LAST:event_vcheque3FocusLost

    private void vcheque4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vcheque4FocusLost
        vcheque4.setText(String.valueOf(Double.parseDouble(vcheque4.getText())));
    }//GEN-LAST:event_vcheque4FocusLost

    private void vcheque5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vcheque5FocusLost
        vcheque5.setText(String.valueOf(Double.parseDouble(vcheque5.getText())));
    }//GEN-LAST:event_vcheque5FocusLost

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        jPanel21.setVisible(true);
        jPanel33.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
        boolean teclado = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (teclado) {
            jLabel77.setVisible(true);
        } else {
            jLabel77.setVisible(false);
        }
    }//GEN-LAST:event_jPasswordField2FocusGained

    private void jTextField26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField26KeyPressed
        
        
    }//GEN-LAST:event_jTextField26KeyPressed

    private void jTextField26KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField26KeyReleased
        if(evt.getKeyChar() == 40){
            jTextField26.setText(jTextField26.getText() +")");
            jTextField26.setCaretPosition(pposicao - 1);
        }
    }//GEN-LAST:event_jTextField26KeyReleased

    private void jTextField26CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField26CaretUpdate
        pposicao = evt.getDot();
        nposicao = evt.getMark();
    }//GEN-LAST:event_jTextField26CaretUpdate

    private void jTextField61FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField61FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField61FocusLost

    private void jTextField61KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField61KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField61KeyTyped

    private void jTextField59CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField59CaretUpdate
        Double pvalor, svalor, tvalor;
        pvalor = Double.parseDouble(jTextField59.getText());
        svalor = Double.parseDouble(jTextField3.getText());
        tvalor = pvalor - svalor;
        jTextField61.setText(String.valueOf(tvalor));
    }//GEN-LAST:event_jTextField59CaretUpdate

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        File deleteimg = new File("/RC SISTEMA/temp/logo.jpg");
        
        if(deleteimg.exists() == false){
            JOptionPane.showMessageDialog(null, "Não possui nenhuma imagem no diretório.");
        }else{
            deleteimg.delete();
            campologo.setIcon(null);
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jTextField22FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField22FocusGained
//        if("   .   .   -  ".equals(jTextField15.getText()) || "".equals(jTextField14.getText())){
//            JOptionPane.showMessageDialog(null, "Para poder validar o processo, você deve informar os dados do "
//                    + "cliente (Nome e CPF).\nSomente quando todos os dados do Cliente estiverem preenchidos, "
//                    + "você poderá continuar \ncom a operação. ", " Campos Incompletos", 
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_jTextField22FocusGained

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        //Animação de cores com o mouse
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(true);
        jPanel14.setVisible(false);
        
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(true);
        jPanel32.setVisible(false);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
        //Abrir jPanel
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
        jPanel21.setVisible(false);
        jPanel26.setVisible(false);
        jPanel29.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
        
        //Animação de cores com o mouse
        jPanel9.setVisible(true);
        jPanel10.setVisible(true);
        jPanel11.setVisible(true);
        jPanel12.setVisible(true);
        jPanel13.setVisible(true);
        jPanel14.setVisible(true);
    }//GEN-LAST:event_jLabel74MouseClicked

    private void jTextField42CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField42CaretUpdate
        if(!"".equals(jTextField42.getText())){
            readForDescImprimirDR(jTextField42.getText());
            readForDescImprimirDC(jTextField46.getText());
        }else{
            jTextField43.setText("");
            jTextField44.setText("");
            jTextField45.setText("");
            jTextField46.setText("");
            jTextField47.setText("");
            jTextField48.setText("");
            jTextField49.setText("");
            jTextField50.setText("");
        }
    }//GEN-LAST:event_jTextField42CaretUpdate

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField8CaretUpdate
        if("   .   .   -  ".equals(jTextField8.getText())){
            readForDescRecibo();
            
            jTextField34.setText("");
            jTextField36.setText("");
            jTextField37.setText("");
            jTextField40.setText("");
            jTextField38.setText("");
            jTextField41.setText("");
            jTextField39.setText("");
        }
    }//GEN-LAST:event_jTextField8CaretUpdate

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel campologo;
    private javax.swing.JPanel cheque;
    private javax.swing.JFormattedTextField dcheque1;
    private javax.swing.JFormattedTextField dcheque2;
    private javax.swing.JFormattedTextField dcheque3;
    private javax.swing.JFormattedTextField dcheque4;
    private javax.swing.JFormattedTextField dcheque5;
    private javax.swing.JPanel dinheiro;
    private javax.swing.JPanel finalizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JFormattedTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JFormattedTextField jTextField7;
    private javax.swing.JFormattedTextField jTextField8;
    private javax.swing.JTextField ncheque1;
    private javax.swing.JTextField ncheque2;
    private javax.swing.JTextField ncheque3;
    private javax.swing.JTextField ncheque4;
    private javax.swing.JTextField ncheque5;
    private javax.swing.JTextField vcheque1;
    private javax.swing.JTextField vcheque2;
    private javax.swing.JTextField vcheque3;
    private javax.swing.JTextField vcheque4;
    private javax.swing.JTextField vcheque5;
    // End of variables declaration//GEN-END:variables
}
