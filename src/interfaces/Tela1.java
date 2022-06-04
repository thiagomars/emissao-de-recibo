package interfaces;

import classes.ManipularImagem;
import conection.ConectionSQLite;
import consultasBD.CooperativaConsulta;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cooperativa;
import model.bean.Reiniciar;
import model.bean.Usuario;

public class Tela1 extends javax.swing.JFrame {

    int contar = 0, botaois = 0, botaoin = 0, validarexcluir = 0;
    String addcop_rs, addcop_cnpj, addcop_rua, addcop_bairro, addcop_n, addcop_cid, addcop_telf;
    String buscop_rs, buscop_cnpj;
    String adduser_nomecomp, adduser_dat, adduser_telef, adduser_user, adduser_senha, adduser_reptsenha;
    String mensagemerro, busca = null, tipobusca = null;
    static float valor = (float) 0.5;
    float opac = (float) 0.99;
    boolean excluir = false, validarexx = true, validarcnpj1, validarcnpj2, validarcnpj3 = false, senha = false;
    BufferedImage imagem;
    
    public Tela1() {
            initComponents();
            
            //Iniciar com a Tabela já atualizada e com dados
            readJTable();
            
            //Ordenação da Tabela
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            jTable1.setRowSorter(new TableRowSorter(modelo));
            
            //Iniciar o indicador 
            jLabel28.setVisible(false);
            
            //Iniciar com todos os jDesktopPane invisiveis
            jDesktopPane1.setVisible(false);
            jDesktopPane2.setVisible(false);
            jDesktopPane3.setVisible(false);
            jDesktopPane4.setVisible(false);
            
            //Iniciar com os botões SIM e NÃO invisiveis
            jButton7.setVisible(false);
            jButton8.setVisible(false);
            
            //verificar logo de fundo
            campologo.setIcon(null);
            File caminho = new File("/RC SISTEMA/temp/logo.jpg");
            if(caminho.exists()){
                campologo.setIcon(new javax.swing.ImageIcon("/RC SISTEMA/temp/logo.jpg"));
            }
            
            //Verificar ativação
            try{
                FileReader ler = new FileReader("/RC SISTEMA/files/activation.txt");
                BufferedReader lerb = new BufferedReader(ler);
                String linha = lerb.readLine();
                
                while(linha != null){
                    
                    if(linha.equals("*key activation ### = &()@NO")){
                        jLabel26.setText("* ATIVAÇÃO NECESSÁRIA *");
                    }else{
                        if(linha.equals("*key activation ### = $1[]YES")){
                            jLabel26.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "Ops! Parece que algum arquivo foi modificado. Entre "
                                    + "em contato com \n um técnico para tentar resolver o problema. (99)99999-9999",
                                    " Erro de Diretórios/Arquivos", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    linha = lerb.readLine();
                    
                }
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Erro inesperado. Error 48.", 
                    " Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void readJTable(){
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        CooperativaConsulta cdao = new CooperativaConsulta();
        
        for(Cooperativa c: cdao.read()){
            modelo.addRow(new Object[]{
                c.getRazaosocial(),
                c.getCnpj(),
                c.getTelefone(),
                c.getUf(),
                c.getRua(),
                c.getBairro(),
                c.getNumero()
            });
        }
    }
    
    public void readJTableForDescRS(String desc){
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        CooperativaConsulta cdao = new CooperativaConsulta();
        
        for(Cooperativa c: cdao.readForDescRS(desc)){
            modelo.addRow(new Object[]{
                c.getRazaosocial(),
                c.getCnpj(),
                c.getTelefone(),
                c.getUf(),
                c.getRua(),
                c.getBairro(),
                c.getNumero()
            });
        }
    }
    
    public void readJTableForDescCNPJ(String desc){
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        CooperativaConsulta cdao = new CooperativaConsulta();
        
        for(Cooperativa c: cdao.readForDescCNPJ(desc)){
            modelo.addRow(new Object[]{
                c.getRazaosocial(),
                c.getCnpj(),
                c.getTelefone(),
                c.getUf(),
                c.getRua(),
                c.getBairro(),
                c.getNumero()
            });
        }
    }
    
    public void readJTableForDescUF(String desc){
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        CooperativaConsulta cdao = new CooperativaConsulta();
        
        for(Cooperativa c: cdao.readForDescUF(desc)){
            modelo.addRow(new Object[]{
                c.getRazaosocial(),
                c.getCnpj(),
                c.getTelefone(),
                c.getUf(),
                c.getRua(),
                c.getBairro(),
                c.getNumero()
            });
        }
    }
    
    Color verdees = new Color (0, 172, 1);
    Color verdecl = new Color(43,227,45);
    Color verdemaes = new Color (0, 205, 0);
    Color cinzab = new Color (240, 240, 240);
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jPanel8 = new javax.swing.JPanel();
        mens1 = new javax.swing.JLabel();
        mens2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        mens4 = new javax.swing.JLabel();
        mens3 = new javax.swing.JLabel();
        aviso = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        mens5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField7 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        campologo = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" Página Inicial");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(43, 227, 45));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel3.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/one-finger-click-black-hand-symbol.png"))); // NOI18N
        jLabel1.setText("Primeiro Acesso");
        jLabel1.setIconTextGap(15);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 11, 190, 28);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel14);
        jPanel14.setBounds(0, 0, 4, 50);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 250, 50));

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
        jPanel4.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/magnifier.png"))); // NOI18N
        jLabel2.setText("Buscar Cooperativa");
        jLabel2.setIconTextGap(15);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2);
        jLabel2.setBounds(10, 11, 210, 28);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel12);
        jPanel12.setBounds(0, 0, 4, 50);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 250, 50));

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
        jPanel5.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-button-inside-black-circle.png"))); // NOI18N
        jLabel3.setText("Adicionar Cooperativa");
        jLabel3.setIconTextGap(15);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel3);
        jLabel3.setBounds(10, 11, 230, 28);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel13);
        jPanel13.setBounds(0, 0, 4, 50);

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 250, 50));

        jPanel10.setBackground(new java.awt.Color(43, 227, 45));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
        });
        jPanel10.setLayout(null);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/userf.png"))); // NOI18N
        jLabel24.setText("Criar Usuário");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.setIconTextGap(15);
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel24);
        jLabel24.setBounds(10, 11, 190, 28);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel15);
        jPanel15.setBounds(0, 0, 4, 50);

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 250, 50));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("ATIVAÇÃO NECESSÁRIA");
        jLabel26.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
        });
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 527, 230, 20));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/RC 250x.png"))); // NOI18N
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 150, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 560));

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
        });

        mens1.setFont(new java.awt.Font("SF UI Text", 0, 18)); // NOI18N
        mens1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mens1.setText(" ");

        mens2.setFont(new java.awt.Font("SF UI Text", 0, 18)); // NOI18N
        mens2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mens2.setText(" ");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setText("Iniciar");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        mens4.setFont(new java.awt.Font("SF UI Text", 0, 18)); // NOI18N
        mens4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mens4.setText(" ");

        mens3.setFont(new java.awt.Font("SF UI Text", 0, 18)); // NOI18N
        mens3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mens3.setText(" ");

        aviso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        aviso.setForeground(new java.awt.Color(171, 171, 171));
        aviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aviso.setText(" ");

        jLabel23.setFont(new java.awt.Font("Roboto Black", 0, 44)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("PRIMEIRA VEZ ???");

        mens5.setFont(new java.awt.Font("SF UI Text", 0, 18)); // NOI18N
        mens5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mens5.setText(" ");

        jButton7.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 3, 24)); // NOI18N
        jButton7.setText("SIM");
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 3, 24)); // NOI18N
        jButton8.setText("NÃO");
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusPainted(false);
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mens1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mens2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                            .addComponent(mens4, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                            .addComponent(mens3, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                            .addComponent(aviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mens5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(mens1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mens2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mens3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mens4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mens5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jDesktopPane3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 560));

        jLayeredPane1.add(jDesktopPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 560));

        jDesktopPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel7.setText("Razão Social:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel8.setText("CNPJ:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel9.setText("Rua:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel10.setText("Bairro:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel11.setText("Nº");

        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel12.setText("Telefone:");

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel13.setText("Cidade:");

        jLabel14.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel14.setText("UF:");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField3.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField4.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField5.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField6.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.setAlignmentX(0.0F);
        jFormattedTextField2.setAlignmentY(0.0F);
        jFormattedTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFormattedTextField2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jFormattedTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField2FocusLost(evt);
            }
        });

        try {
            jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFormattedTextField3.setMargin(new java.awt.Insets(2, 5, 2, 5));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "   ", "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));

        jLabel15.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel15.setText("Adicionar Cooperativa");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jComboBox2, 0, 160, Short.MAX_VALUE)
                                                .addGap(18, 18, 18))))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                    .addComponent(jLabel13)
                                    .addComponent(jTextField5))))))
                .addGap(43, 43, 43))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(46, 46, 46))
        );

        jDesktopPane2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 560));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar pela Cooperativa"));

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel4.setText("Razão Social:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel5.setText("CNPJ:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel6.setText("UF:");

        jTextField1.setBackground(new java.awt.Color(240, 240, 240));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
        });

        jFormattedTextField1.setBackground(new java.awt.Color(240, 240, 240));
        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFormattedTextField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusGained(evt);
            }
        });
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Razão Social", "CNPJ", "Telefone", "UF", "Rua", "Bairro", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(2);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(220);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(52);
        }

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Iniciar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Excluir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jDesktopPane1.add(jPanel2);
        jPanel2.setBounds(0, 0, 720, 560);

        jLayeredPane1.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 560));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel16.setText("Nome Completo:");

        jLabel17.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel17.setText("Data de Nascimento:");

        jLabel18.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel18.setText("Telefone:");

        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel19.setText("Nome de Usuário:");

        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel20.setText("Senha:");

        jLabel21.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel21.setText("Repetir Senha:");

        jLabel22.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel22.setText("Criar Usuário");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField7.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField9.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        try {
            jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFormattedTextField4.setMargin(new java.awt.Insets(2, 5, 2, 5));

        try {
            jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFormattedTextField5.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jFormattedTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField5FocusLost(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPasswordField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jPasswordField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jPasswordField1CaretUpdate(evt);
            }
        });
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

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPasswordField2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jPasswordField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jPasswordField2CaretUpdate(evt);
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

        jLabel25.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setText(" ");

        jLabel28.setFont(new java.awt.Font("Elite Danger Semi-Bold Expanded", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 20, 20));
        jLabel28.setText("* Tecla CapsLock ligada");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(64, 64, 64)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextField5)
                                            .addComponent(jPasswordField2)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(jLabel21))
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField5)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField4)))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addGap(78, 78, 78))
        );

        jDesktopPane4.add(jPanel9);
        jPanel9.setBounds(0, 0, 720, 623);

        jLayeredPane1.add(jDesktopPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 560));

        campologo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        campologo.setAutoscrolls(true);
        jLayeredPane1.add(campologo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 540));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 720, 560));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 720, 560));

        jMenuBar1.setBorderPainted(false);

        jMenu1.setText("Arquivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        jMenuItem1.setText("Reiniciar Sistema ");
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

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application-vnd.sun.xml.draw.template.png"))); // NOI18N
        jMenuItem9.setText("Área de Trabalho");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);
        jMenu1.add(jSeparator3);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folder_explore.png"))); // NOI18N
        jMenuItem3.setText("Inserir Logo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/picture-delete.png"))); // NOI18N
        jMenuItem10.setText("Deletar Logo");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mais");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database-save.png"))); // NOI18N
        jMenuItem7.setText("Banco de Dados ");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/key.png"))); // NOI18N
        jMenuItem8.setText("Ativação");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator.png"))); // NOI18N
        jMenuItem4.setText("Calculadora");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);
        jMenu2.add(jSeparator4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help-contents.png"))); // NOI18N
        jMenuItem5.setText("Sobre");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.png"))); // NOI18N
        jMenuItem6.setText("Ajuda");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel10.setBackground(verdecl);
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.setBackground(Color.WHITE);
        jFormattedTextField1.setBackground(cinzab);
        jFormattedTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_jTextField1FocusGained

    private void jFormattedTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusGained
        jFormattedTextField1.setBackground(Color.WHITE);
        jTextField1.setBackground(cinzab);
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_jFormattedTextField1FocusGained

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        jFormattedTextField1.setBackground(cinzab);
        jTextField1.setBackground(cinzab);
        jTextField1.setText("");
        jFormattedTextField1.setText("");
    }//GEN-LAST:event_jComboBox1FocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(contar == 0){
            mens1.setText("Olá, seja Bem Vindo. Se você está aqui é porque provavelmente esta é sua");
            mens2.setText("primeira vez no nosso Sistema, então vamos para uma rápida explicação.");
            mens3.setText("");
            mens4.setText("");
            aviso.setText("Para prosseguir com as explicações você deve clicar em CONTINUAR");
            
            contar = 1;
            jLabel23.setVisible(false);
            jButton2.setText("Continuar");
        }else{
            if(contar == 1){
                mens1.setText("Antes de tudo, você já fez a ATIVAÇÃO do Sistema?");
                mens2.setText(" ");
                mens3.setText(" ");
                mens4.setText(" ");
                aviso.setText("Responda SIM ou NÃO.");
                
                jButton7.setVisible(true);
                jButton8.setVisible(true);
                jButton2.setVisible(false);
            }else{
                if(contar == 2){
                    mens1.setText("Bem, nosso Sistema funciona com um Banco de Dados Local que deve ser");
                    mens2.setText("configurado por um de nossos técnicos. Sem esse Banco de Dados, o");
                    mens3.setText("programa ficará unutilizável.");
                    mens4.setText(" ");
                    aviso.setText("Para prosseguir com as explicações você deve clicar em CONTINUAR");
                    
                    contar = 3;
                }else{
                    if(contar == 3){
                        mens1.setText("Esse Banco é para você ter uma melhor organização e agilidade na");
                        mens2.setText("hora de pôr a mão na massa. O seu Banco de Dados já está configurado?");
                        mens3.setText(" ");
                        mens4.setText(" ");
                        aviso.setText("Responda SIM ou NÃO.");
                        
                        jButton7.setVisible(true);
                        jButton8.setVisible(true);
                        jButton2.setVisible(false);
                    }else{
                        if(contar == 4){
                            mens1.setText("No nosso Sistema, você poderá trabalhar com várias cooperativas ao mesmo");
                            mens2.setText("tempo, inclusive mantendo os recibos e clientes separados de acordo com a");
                            mens3.setText("Cooperativa em aceso.");
                            mens4.setText(" ");
                            aviso.setText("Para prosseguir com as explicações você deve clicar em CONTINUAR");
                            
                            contar = 5;
                        }else{
                            if(contar == 5){
                                mens1.setText("Mas antes de cadastrar a Cooperativa, você deve criar um usuário para poder");
                                mens2.setText("entrar no Sistema. Clique em ''Criar Usuário'' e preencha todos os dados.");
                                mens3.setText(" ");
                                mens4.setText(" ");
                                
                                contar = 6;
                            }else{
                                if(contar == 6){
                                    mens1.setText("Pronto. Agora para poder começar a cadastrar recibos e clientes, você deve");
                                    mens2.setText("primeiro cadastrar a sua Cooperativa, basta clicar em ''Adicionar Cooperativa''.");
                                    mens3.setText("Preencha todos os dados e clique em ''Adicionar''.");
                                    mens4.setText(" ");
                                    
                                    contar = 7;
                                }else{
                                    if(contar == 7){
                                        mens1.setText("Depois clique em ''Buscar Cooperativa'', lá vai aparecer todas as Cooperativas");
                                        mens2.setText("cadastradas; se você desejar, também pode fazer uma busca.");
                                        mens3.setText(" ");
                                        mens4.setText(" ");
                                        
                                        contar = 8;
                                    }else{
                                        if(contar == 8){
                                            mens1.setText("Clique em ''Iniciar'', digite seu Usuário e Senha, e pronto.");
                                            mens2.setText(" ");
                                            mens3.setText("Hora de pôr a mão na massa. Tenha um Bom Dia de Trabalho e até logo!");
                                            mens4.setText(" ");
                                            aviso.setText("Clique em Continuar para Finalizar.");
                                            
                                            contar = 9;
                                        }else{
                                            if(contar == 9){
                                                mens1.setText(" ");
                                                mens2.setText(" ");
                                                mens3.setText(" ");
                                                mens4.setText(" ");
                                                aviso.setText("Deseja ver novamente?");
                                                
                                                jLabel23.setVisible(true);
                                                jButton2.setText("Iniciar");
                                                
                                                contar = 0;
                                                botaoin = 0;
                                                botaois = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        jPanel10.setBackground(verdees);
        jPanel5.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        jLabel24MouseClicked(evt);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        //Abrir Painel de Opções
        jDesktopPane4.setVisible(true);
        jDesktopPane1.setVisible(false);
        jDesktopPane2.setVisible(false);
        jDesktopPane3.setVisible(false);
        
        //Limpar campos
        jTextField1.setText("");
        jFormattedTextField1.setText("");
        jTextField2.setText("");
        jFormattedTextField2.setText("");
        jTextField3.setText("");
        jTextField6.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jFormattedTextField3.setText("");
        
        //Clicar e indicar com a cor
        jPanel15.setVisible(true);
        
        //Clicar e desativar os outros campos
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        jPanel5.setBackground(verdees);
        jPanel10.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        jLabel3MouseClicked(evt);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        //Abrir Painel de Opções
        jDesktopPane2.setVisible(true);
        jDesktopPane1.setVisible(false);
        jDesktopPane3.setVisible(false);
        jDesktopPane4.setVisible(false);

        //Limpar os campos
        jTextField7.setText("");
        jFormattedTextField4.setText("");
        jFormattedTextField5.setText("");
        jTextField9.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jTextField1.setText("");
        jFormattedTextField1.setText("");
        
        //Clicar e indicar com a cor
        jPanel13.setVisible(true);
        
        //Clicar e desativar os outros campos
        jPanel12.setVisible(false);
        jPanel14.setVisible(false);
        jPanel15.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        jPanel4.setBackground(verdees);
        jPanel10.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel3.setBackground(verdecl);
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        jLabel2MouseClicked(evt);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        //Abrir Painel de Opções
        jDesktopPane1.setVisible(true);
        jDesktopPane3.setVisible(false);
        jDesktopPane2.setVisible(false);
        jDesktopPane4.setVisible(false);
        
        //Limpar os campos
        jTextField7.setText("");
        jFormattedTextField4.setText("");
        jFormattedTextField5.setText("");
        jTextField9.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jTextField2.setText("");
        jFormattedTextField2.setText("");
        jTextField3.setText("");
        jTextField6.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField3.setText("");
        
        //Clicar e indicar com a cor
        jPanel12.setVisible(true);
        
        //Clicar e desativar os outros campos
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        jPanel15.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        jPanel3.setBackground(verdees);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel10.setBackground(verdecl);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        jLabel1MouseClicked(evt);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        //Abrir Painel de Opções
        jDesktopPane3.setVisible(true);
        jDesktopPane2.setVisible(false);
        jDesktopPane4.setVisible(false);
        jDesktopPane1.setVisible(false);
        
        //Limpar Campos
        jTextField7.setText("");
        jFormattedTextField4.setText("");
        jFormattedTextField5.setText("");
        jTextField9.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jTextField1.setText("");
        jFormattedTextField1.setText("");
        jTextField2.setText("");
        jFormattedTextField2.setText("");
        jTextField3.setText("");
        jTextField6.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jFormattedTextField3.setText("");
        
        //Clicar e indicar com a cor
        jPanel14.setVisible(true);
        
        //Clicar e desativar os outros campos
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel15.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jTable1.getSelectedRow() != -1){
            //Abir tela do Login
            new Login().setVisible(true);
            this.dispose();
            
                ///////////////Criar os arquivos com os dados da Cooperativa Selecionada///////////////////////
                try {
                    //Razão Social
                    Object cooperativa = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    //CNPJ
                    Object cnpj = jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                    //Telefone
                    Object telefone = jTable1.getValueAt(jTable1.getSelectedRow(), 2);
                    //UF
                    Object uf = jTable1.getValueAt(jTable1.getSelectedRow(), 3);
                    //Rua
                    Object rua = jTable1.getValueAt(jTable1.getSelectedRow(), 4);
                    //Bairro
                    Object bairro = jTable1.getValueAt(jTable1.getSelectedRow(), 5);
                    //Número
                    Object numero = jTable1.getValueAt(jTable1.getSelectedRow(), 6);
                    
                    File caminho = new File("/RC SISTEMA/temp/cooperado/data_live.txt");
                    
                    if(caminho.exists()){
                        FileWriter local = new FileWriter(caminho);
                        BufferedWriter escrever = new BufferedWriter(local);
                        escrever.write((String) cooperativa + " | " + cnpj + " | " + telefone + " | " + uf +
                            " | " + rua + " | " + bairro + " | " + numero);
                        escrever.close();
                    }else{
                        caminho.createNewFile();
                        FileWriter local = new FileWriter("/RC SISTEMA/temp/cooperado/data_live.txt");
                        BufferedWriter escrever = new BufferedWriter(local);
                        escrever.write((String) cooperativa + " | " + cnpj + " | " + telefone + " | " + uf +
                            " | " + rua + " | " + bairro + " | " + numero);
                        escrever.close();
                    }
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ops! Algo deu errado. Feche o Programa e tente novamente. \n"
                        + "Erro 0011 \n" + ex, " Erro de Diretório", JOptionPane.ERROR_MESSAGE);
                }
                
        }else{
            JOptionPane.showMessageDialog(null, "Selecione a Cooperativa na Tabela.", 
                    " Cooperativa", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
 
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String opn = JOptionPane.showInputDialog(null, "Olá, o motivo pelo qual você está reiniciando o "
                + "programa, é algum erro? Se sua resposta \nfor SIM, comente aí em baixo com detalhes, "
                + "se possível, qual foi esse erro. Obrigado! ");
        
        java.util.Date d = new Date();
        String dataatual = java.text.DateFormat.getDateInstance(DateFormat.DEFAULT).format(d);
        
        if(!"".equals(opn)){
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

        //Tempo para Reiniciar
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            
        }
        
        new Tela1().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        new BancoDeDados().setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        //Voltar ao padrão os botões
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel10.setBackground(verdecl);
        
        //Verificar o estado do jLabel26, se a ativação está tudo OK
        if(jLabel26.getText().equals("ATIVAÇÃO NECESSÁRIA")){
            JOptionPane.showMessageDialog(null, "Ops! Problema Grave. Erro 155.",
                    " Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //verificar um arquivo
        try{
            FileReader ler = new FileReader("C:\\RC SISTEMA\\files\\activation.txt");
            BufferedReader lerb = new BufferedReader(ler);
            String linha = lerb.readLine();

            while(linha != null){
                
                if(linha.equals("*key activation ### = &()@NO")){
                    new MensgActive().setVisible(true);
                    this.dispose();
                }else{
                    if(linha.equals("*key activation ### = $1[]YES")){
                        if(!jTextField7.getText().equals("") && !jFormattedTextField4.getText().equals("  /  /    ") && !jTextField9.getText().equals("")){
                            String senhaum, senhadois;
                            senhaum = String.valueOf(jPasswordField1.getPassword());
                            senhadois = String.valueOf(jPasswordField2.getPassword());
                            
                            if(!senhaum.equals(senhadois)){
                                jLabel25.setText("<html><font color = red>* As senhas não coincidem.</font></html>");
                            }else{
                               if(senhaum.equals("") && senhadois.equals("")){
                                    jLabel25.setText("<html><font color = red>* As senhas não podem estar em branco.</font></html>");
                                }else{
                                   if(senha == true){
                                        //BANCO DE DADOS
                                        ConectionSQLite consqlite = new ConectionSQLite();
                                        Usuario user = new Usuario();

                                        user.setNome(jTextField7.getText().trim());
                                        user.setNascimento(jFormattedTextField4.getText().trim());
                                        user.setTelefone(jFormattedTextField5.getText().trim());
                                        user.setUsuario(jTextField9.getText().trim());
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
                                            jTextField7.setText("");
                                            jTextField9.setText("");
                                            jFormattedTextField4.setText("");
                                            jFormattedTextField5.setText("");
                                            jPasswordField1.setText("");
                                            jPasswordField2.setText("");

                                            JOptionPane.showMessageDialog(null, "O Usuário " + jTextField9.getText() + 
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
                                        
                                   }else{
                                       jLabel25.setText("<html><font color = red>* A sua senha deve possuir mais de 6 caracteres.</font></html>");
                                   }
                               }
                            }
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "Ver");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ops! Parece que algum arquivo "
                                + "foi alterado. Entre em contato com um de nossos atendentes \n"
                                + "pelo número (99)99999-9999 o mais rápido possível. Até logo!");
                        System.exit(0);
                    }
                }
                
                linha = lerb.readLine();
            }
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Atenção, ocorreu o seguinte erro: " + e +"\n"
                    + "Vá na opção de ajuda e procure sobre instruções para entrar em contato com a gente.");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained
        jLabel25.setText("* Por segurança, digite seu nome completo.");
    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        jLabel25.setText(" ");
    }//GEN-LAST:event_jTextField7FocusLost

    private void jFormattedTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField5FocusGained
        jLabel25.setText("<html>* Campo não obrigatório. Por segurança, digite o numero do <b>SEU</b> celular.</html>");
    }//GEN-LAST:event_jFormattedTextField5FocusGained

    private void jFormattedTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField5FocusLost
        jLabel25.setText(" ");
    }//GEN-LAST:event_jFormattedTextField5FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        jLabel25.setText("* Você pode usar letras, números e ponto. Letras Maiúsculas e Minúsculas diferem.");
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        jLabel25.setText(" ");
    }//GEN-LAST:event_jTextField9FocusLost

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        jLabel25.setText("* Letras maiúsculas e minúsculas diferem. Mínimo de 6 caracteres.");
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        jLabel25.setText(" ");
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jPasswordField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jPasswordField2CaretUpdate
        boolean verificar;
        try{
            char charAt = jPasswordField2.getText().charAt(0);
            if(!String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))){
                jLabel25.setText("<html><font color = red>* As senhas não coincidem.</font></html>");
                verificar = false;
            }else{
                jLabel25.setText("* Antes de Continuar, verifique se todos os campos estão preenchidos corretamente.");
            }
        } catch(Error | Exception ex){
            jLabel25.setText(" ");
        }
    }//GEN-LAST:event_jPasswordField2CaretUpdate

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ ãõáéóÃÕÁÉÓ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ ãõáéóÃÕÁÉÓ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ.";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ ãõáéóÃÕÁÉÓ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZ.ãõáéóÃÕÁÉÓ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZãõáéóÃÕÁÉÓ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        String caracteres = "1234567890";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm ABCDEFGHIJKLMNOPQRSTUVWXYZãõáéóÃÕÁÉÓ";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(validarcnpj3 == true){
            //Adicionar no Banco de Dados
            ConectionSQLite consqlite = new ConectionSQLite();
            Cooperativa cooperativa = new Cooperativa();
            
            cooperativa.setRazaosocial(jTextField2.getText().trim());
            cooperativa.setCnpj(jFormattedTextField2.getText().trim());
            cooperativa.setTelefone(jFormattedTextField3.getText().trim());
            cooperativa.setUf(String.valueOf(jComboBox2.getSelectedItem()).trim());
            cooperativa.setCidade(jTextField5.getText().trim());
            cooperativa.setBairro(jTextField6.getText().trim());
            cooperativa.setNumero(Integer.parseInt(jTextField4.getText().trim()));
            cooperativa.setRua(jTextField3.getText().trim());
            
            consqlite.conectar();
            
            String sqlInsert = "INSERT INTO cooperativa "
                    + "(razaosocial, cnpj, telefone, uf, cidade, bairro, numero, rua) "
                    + "VALUES (?,?,?,?,?,?,?,?);";
            
            PreparedStatement stmt = consqlite.criarPreparedStatement(sqlInsert);
            
            try{
                stmt.setString(1, cooperativa.getRazaosocial());
                stmt.setString(2, cooperativa.getCnpj());
                stmt.setString(3, cooperativa.getTelefone());
                stmt.setString(4, cooperativa.getUf());
                stmt.setString(5, cooperativa.getCidade());
                stmt.setString(6, cooperativa.getBairro());
                stmt.setInt(7, cooperativa.getNumero());
                stmt.setString(8, cooperativa.getRua());
                
                stmt.executeUpdate();
                
                //Limpar os campos
                jTextField2.setText("");
                jFormattedTextField2.setText("");
                jFormattedTextField3.setText("");
                jComboBox2.setSelectedIndex(0);
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                
                JOptionPane.showMessageDialog(null, "A Cooperativa " + jTextField2.getText() + 
                        " foi cadastrada com Sucesso!", " Cadastro", JOptionPane.INFORMATION_MESSAGE);
                
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Não foi possível fazer o Cadastro da Cooperativa. \n"
                        + "Tente novamente. Error 61. ", " Erro de Inserção", JOptionPane.ERROR_MESSAGE);
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
            
            //Atualizar a Tabela
            readJTable();
            
        }else{
            JOptionPane.showMessageDialog(null, "CNPJ inválido. Verifique e tente novamente. ", 
                    " Campo CNPJ", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new Ajuda().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(null, "Existe alguns requisitos para você poder inserir sua LOGO: \n\n"
                + "1- A imagem deve ser até 700px de largura e 530px de altura; \n"
                + "2- Para ter um melhor desempenho do Sistema, recomendamos a imagem ser até 3 megas; \n"
                + "3- Ao invés de usar imagens/logos sem fundo, utilize arquivos com o fundo branco; \n"
                + "4- Caso a imagem ultrapasse o tamanho máximo permitido, a imagem será diminuida \n"
                + "automáticamente para poder caber; \n"
                + "5- É recomendável você utilizar arquivos com extenção jpg. Outras extenções podem ou \n"
                + "não funcionar. \n\n"
                + "Obs- Depois que você selecionar a imagem, recomendamos você 'FECHAR O PROGRAMA' e inicia-lo \n"
                + "novamente.", "Recomendações", JOptionPane.INFORMATION_MESSAGE);
        
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);
        
        if(res == JFileChooser.APPROVE_OPTION){
            File arquivo = fc.getSelectedFile();
            
            try{
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
            campologo.setIcon(new javax.swing.ImageIcon("/RC SISTEMA/temp/logo.jpg"));
            campologo.updateUI();
        } catch (IOException ex){
            
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir a Calculadora.");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new Sobre().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        botaoin++;
        if(botaoin == 1){
            mens1.setText("Tá esperando o que? Ligue já para (99)99999-9999 e ative o Programa. Também");
            mens2.setText("podemos fazer isso pelo WhatsApp ou por email, você escolhe.");
            mens3.setText("Nosso email é sitemerecibo@outlook.com");
            mens4.setText("");
            aviso.setText(" ");
            
            jButton7.setVisible(false);
            jButton8.setVisible(false);
            jButton2.setVisible(true);
            
            botaois = 2;
            botaoin = 2;
            contar = 2;
        }else{
            if(botaoin == 3){
                mens1.setText("Uhm, então não há muito o que fazer aqui, você pode chamar um dos nossos");
                mens2.setText("técnicos para ele fazer a configuração e você poder começar a trabalhar.");
                mens3.setText("Você quer saber como entrar em contato com algum técnico?");
                mens4.setText(" ");
                aviso.setText("Responda SIM ou NÃO.");
                
                botaoin = 3;
                botaois = 3;
            }else{
                if(botaoin == 4){
                    mens1.setText("Bem, vamos continuar então com a explicação, mas não vai servir muito visto");
                    mens2.setText("que o programa não irá fazer nenhum recibo entre outras coisas essenciais.");
                    mens3.setText(" ");
                    mens4.setText(" ");
                    aviso.setText(" ");
                    
                    jButton7.setVisible(false);
                    jButton8.setVisible(false);
                    jButton2.setVisible(true);
                    
                    contar = 4;
                }
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setText("<html><font color = #01DF01>SIM</font></html>");
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jButton8.setText("<html><font color = #FF0000>NÃO</font></html>");
    }//GEN-LAST:event_jButton8MouseEntered

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        jButton7.setText("SIM");
        jButton8.setText("NÃO");
        //Animação das cores dos botões
        jPanel1MouseEntered(evt);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        botaois++;
        if(botaois == 1){
            mens1.setText(" ");
            mens2.setText("Que ótimo, então vamos continuar.");
            mens3.setText(" ");
            mens4.setText(" ");
            aviso.setText(" ");
            
            jButton7.setVisible(false);
            jButton8.setVisible(false);
            jButton2.setVisible(true);
            
            botaois = 2;
            botaoin = 2;
            contar = 2;
        }else{
            if(botaois == 3){
                mens1.setText(" ");
                mens2.setText(" ");
                mens3.setText("Tá certo, então vamos continuar.");
                mens4.setText(" ");
                aviso.setText(" ");
                
                jButton7.setVisible(false);
                jButton8.setVisible(false);
                jButton2.setVisible(true);
                
                botaoin = 3;
                botaois = 3;
                contar = 4;
            }else{
                if(botaois == 4){
                    mens1.setText("É bem fácil, você pode nos ligar ou mandar mensagem no WhatsApp pelo");
                    mens2.setText("nosso número(99)99999-9999. Ou então se você preferir nos mande um email");
                    mens3.setText("<html><i>sistemerecibo@outlook.com</i>.</html>");
                    mens4.setText(" ");
                    aviso.setText(" ");

                    jButton7.setVisible(false);
                    jButton8.setVisible(false);
                    jButton2.setVisible(true);

                    contar = 4;
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        //VERIFICAR ATIVAÇÃO para continuar: abrir tela MenuPrincipal ou MensgActive
        String linha = null;
        try{
                FileReader ler = new FileReader("C:\\RC SISTEMA\\files\\activation.txt");
                BufferedReader lerb = new BufferedReader(ler);
                linha = lerb.readLine();
                
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar verificar um dos arquivos. Entre "
                    + "e, contato \ncom um de nossos técnicos para verificar o problema.", 
                    " Erro de diretório/arquivo", JOptionPane.WARNING_MESSAGE);
        }
        
        //Banco de Dados
        ConectionSQLite consqlite = new ConectionSQLite();
        ResultSet resultset = null;
        
        boolean bdativar = false;
        
        consqlite.conectar();
        
        PreparedStatement stmt = consqlite.criarPreparedStatement("SELCT * FROM ativar;");
        
        try{
            resultset = stmt.executeQuery();
            
            while(resultset.next()){
                if("NOT".equals(resultset.getString("status_ativar"))){
                    bdativar = false;
                }else{
                    if("YES".equals(resultset.getString("status_ativar"))){
                        bdativar = true;
                    }
                }
            }
            consqlite.desconectar();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro inesperado. Error 311.", 
                    " Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(linha.equals("*key activation ### = $1[]YES") && bdativar == true){
            JOptionPane.showMessageDialog(null, "Esta é uma mensagem de verificação do Status "
                    + "de Ativação do Sistema e do Banco de Dados. \nOs detalhes sobre os mesmo estão abaixo. \n\n"
                    + "Status do SISTEMA DE EMISSÃO DE RECIBO ---> Ativado com Sucesso \n"
                    + "Status do Banco de Dados ---> Ativado com Sucesso \n\n"
                    + "Para mais informações entre em contato com um de nossos atendentes (99)99999 - 9999. ", 
                    " Ativação", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(linha.equals("*key activation ### = &()@NO") && bdativar == true){
                JOptionPane.showMessageDialog(null, "Esta é uma mensagem de verificação do Status "
                        + "de Ativação do Sistema e do Banco de Dados. \nOs detalhes sobre os mesmo estão abaixo. \n\n"
                        + "Status do SISTEMA DE EMISSÃO DE RECIBO ---> Ativado com Sucesso \n"
                        + "Status do Banco de Dados ---> Não está Ativado \n\n"
                        + "Para mais informações entre em contato com um de nossos atendentes (99)99999 - 9999. ", 
                        " Ativação", JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(linha.equals("*key activation ### = &()@NO") && bdativar == false){
                    JOptionPane.showMessageDialog(null, "Esta é uma mensagem de verificação do Status "
                            + "de Ativação do Sistema e do Banco de Dados. \nOs detalhes sobre os mesmo estão abaixo. \n\n"
                            + "Status do SISTEMA DE EMISSÃO DE RECIBO ---> Não está Ativado \n"
                            + "Status do Banco de Dados ---> Não está Ativado \n\n"
                            + "Para mais informações entre em contato com um de nossos atendentes (99)99999 - 9999. ", 
                            " Ativação", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(linha.equals("*key activation ### = $1[]YES") && bdativar == false){
                        JOptionPane.showMessageDialog(null, "Esta é uma mensagem de verificação do Status "
                                + "de Ativação do Sistema e do Banco de Dados. \nOs detalhes sobre os mesmo estão abaixo. \n\n"
                                + "Status do SISTEMA DE EMISSÃO DE RECIBO ---> Não está Ativado \n"
                                + "Status do Banco de Dados ---> Não está Ativado \n\n"
                                + "Para mais informações entre em contato com um de nossos atendentes (99)99999 - 9999. ", 
                                " Ativação", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro inesperado. Error 312.", 
                                " Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        jDesktopPane1.setVisible(false);
        jDesktopPane3.setVisible(false);
        jDesktopPane2.setVisible(false);
        jDesktopPane4.setVisible(false);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        if(jLabel26.getText().equals("* ATIVAÇÃO NECESSÁRIA *")){
            //VERIFICAR ATIVAÇÃO para continuar: abrir tela MenuPrincipal ou MensgActive
            try{
                    FileReader ler = new FileReader("C:\\RC SISTEMA\\files\\activation.txt");
                    BufferedReader lerb = new BufferedReader(ler);
                    String linha = lerb.readLine();

                    if(linha.equals("*key activation ### = &()@NO")){
                        new MensgActive().setVisible(true);
                        this.setVisible(false);
                    }

            } catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar verificar um dos arquivos. Entre "
                        + "e, contato \ncom um de nossos técnicos para verificar o problema.", 
                        " Erro de diretório/arquivo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        if(jLabel26.getText().equals("* ATIVAÇÃO NECESSÁRIA *")){
            jLabel26.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        //Fechar as janelas de opções/jDesktopPanel
        jDesktopPane1.setVisible(false);
        jDesktopPane3.setVisible(false);
        jDesktopPane2.setVisible(false);
        jDesktopPane4.setVisible(false);
        
        //Voltar ao inicio a animação dos jPanel 
        jPanel12.setVisible(true);
        jPanel13.setVisible(true);
        jPanel14.setVisible(true);
        jPanel15.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        jTextField2.setText(jTextField2.getText().toUpperCase());
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        jTextField3.setText(jTextField3.getText().toUpperCase());
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        jTextField6.setText(jTextField6.getText().toUpperCase());
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        jTextField5.setText(jTextField5.getText().toUpperCase());
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        jTextField7.setText(jTextField7.getText().toUpperCase());
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jTextField1.setText(jTextField1.getText().toUpperCase());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        boolean teclado = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (teclado) {
            jLabel28.setVisible(true);
        } else {
            jLabel28.setVisible(false);
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jPasswordField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField2KeyPressed
        boolean teclado = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (teclado) {
            jLabel28.setVisible(true);
        } else {
            jLabel28.setVisible(false);
        }
    }//GEN-LAST:event_jPasswordField2KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount() == 2){
            if(jTable1.getSelectedRow() != -1){
                //Abir tela do Login
                new Login().setVisible(true);
                this.dispose();

                ///////////////Criar os arquivos com os dados da Cooperativa Selecionada///////////////////////
                try {
                    //Razão Social
                    Object cooperativa = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    //CNPJ
                    Object cnpj = jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                    //Telefone
                    Object telefone = jTable1.getValueAt(jTable1.getSelectedRow(), 2);
                    //UF
                    Object uf = jTable1.getValueAt(jTable1.getSelectedRow(), 3);
                    //Rua
                    Object rua = jTable1.getValueAt(jTable1.getSelectedRow(), 4);
                    //Bairro
                    Object bairro = jTable1.getValueAt(jTable1.getSelectedRow(), 5);
                    //Número
                    Object numero = jTable1.getValueAt(jTable1.getSelectedRow(), 6);

                    File caminho = new File("/RC SISTEMA/temp/cooperado/data_live.txt");

                    if(caminho.exists()){
                        FileWriter local = new FileWriter(caminho);
                        BufferedWriter escrever = new BufferedWriter(local);
                        escrever.write((String) cooperativa + " | " + cnpj + " | " + telefone + " | " + uf +
                            " | " + rua + " | " + bairro + " | " + numero);
                        escrever.close();
                    }else{
                        caminho.createNewFile();
                        FileWriter local = new FileWriter("/RC SISTEMA/temp/cooperado/data_live.txt");
                        BufferedWriter escrever = new BufferedWriter(local);
                        escrever.write((String) cooperativa + " | " + cnpj + " | " + telefone + " | " + uf +
                            " | " + rua + " | " + bairro + " | " + numero);
                        escrever.close();
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ops! Algo deu errado. Feche o Programa e tente novamente. \n"
                        + "Erro 0011 \n" + ex, " Erro de Diretório", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Selecione a Cooperativa na Tabela.", 
                        " Cooperativa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!"".equals(jTextField1.getText())){
            readJTableForDescRS(jTextField1.getText());
            System.out.println("1");
        }else{
            if(!"  .   .   /    -  ".equals(jFormattedTextField1.getText())){
                readJTableForDescCNPJ(jFormattedTextField1.getText());
                System.out.println("2");
            }else{
                if(!"".equals(String.valueOf(jComboBox1.getSelectedItem()))){
                    readJTableForDescUF(String.valueOf(jComboBox1.getSelectedItem()));
                }else{
                    readJTable();
                }
            } 
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        //verificar logo de fundo
        File caminho = new File("/RC SISTEMA/temp/logo.jpg");
        if(caminho.exists()){
            campologo.setIcon(new javax.swing.ImageIcon("/RC SISTEMA/temp/logo.jpg"));
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jButton5ActionPerformed(evt);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        jButton5ActionPerformed(evt);
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        File deleteimg = new File("/RC SISTEMA/temp/logo.jpg");
        
        if(deleteimg.exists() == false){
            JOptionPane.showMessageDialog(null, "Não possui nenhuma imagem no diretório.");
        }else{
            deleteimg.delete();
            campologo.setIcon(null);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jFormattedTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField2FocusLost
        int cn0, cn1, cn3, cn4, cn5, cn7, cn8, cn9, cn11, cn12, cn13, cn14, cn16, cn17;
        
        try{
            //Pegar os valores de cada caractere
            cn0 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(0)));
            cn1 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(1)));
            cn3 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(3)));
            cn4 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(4)));
            cn5 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(5)));
            cn7 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(7)));
            cn8 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(8)));
            cn9 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(9)));
            cn11 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(11)));
            cn12 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(12)));
            cn13 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(13)));
            cn14 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(14)));
            cn16 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(16)));
            cn17 = Integer.parseInt(String.valueOf(jFormattedTextField2.getText().charAt(17)));
            
            //Soma e divisao dos primeiros digitos para achar o resto da divisão
            int valorsomadivisaoresto1 = (((cn0 * 5) + (cn1 * 4) + (cn3 * 3) + (cn4 * 2) + (cn5 * 9) + (cn7 * 8) 
                    + (cn8 * 7) + (cn9 * 6) + (cn11 * 5) + (cn12 * 4) + (cn13 * 3) + (cn14 * 2)) % 11);
            
            //Verificar se o resto da divisão é menor ou não que dois
            if(valorsomadivisaoresto1 < 2){
                if(cn16 == 0){
                    validarcnpj1 = true;
                }else{
                    validarcnpj1 = false;
                }
            }else{
                if(11 - valorsomadivisaoresto1 == cn16){
                    validarcnpj1 = true;
                }else{
                    validarcnpj1 = false;
                }
            }
            
            //Soma e divisao dos primeiros digitos para achar o resto da divisão
            int valorsomadivisaoresto2 = (((cn0 * 6) + (cn1 * 5) + (cn3 * 4) + (cn4 * 3) + (cn5 * 2) + (cn7 * 9) 
                    + (cn8 * 8) + (cn9 * 7) + (cn11 * 6) + (cn12 * 5) + (cn13 * 4) + (cn14 * 3) + (cn16 * 2)) % 11);  
            
            //Verificar se o resto da divisão é menor ou não que dois
            if(valorsomadivisaoresto2 < 2){
                if(cn17 == 0){
                    validarcnpj2 = true;
                }else{
                    validarcnpj2 = false;
                }
            }else{
                if(11 - valorsomadivisaoresto2 == cn17){
                    validarcnpj2 = true;
                }else{
                    validarcnpj2 = false;
                }
            }
            
            //Validar CPF
            if(validarcnpj1 == false || validarcnpj2 == false){
                jFormattedTextField2.setForeground(Color.RED);
                validarcnpj3 = false;
            }else{
                validarcnpj3 = true;
                jFormattedTextField2.setForeground(Color.BLACK);
            }
            
        } catch (Error | Exception ex){
            
        }
        
    }//GEN-LAST:event_jFormattedTextField2FocusLost

    private void jPasswordField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jPasswordField1CaretUpdate
        try{
            char charAt = jPasswordField1.getText().charAt(5);
            jLabel25.setText("* Letras maiúsculas e minúsculas diferem. ");
            senha = true;
        } catch(Error | Exception ex){
            senha = false;
        }
    }//GEN-LAST:event_jPasswordField1CaretUpdate

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        //Voltar ao padrão os botões
        jPanel3.setBackground(verdecl);
        jPanel4.setBackground(verdecl);
        jPanel5.setBackground(verdecl);
        jPanel10.setBackground(verdecl);

        //Verificar o estado do jLabel26, se a ativação está tudo OK
        if(jLabel26.getText().equals("ATIVAÇÃO NECESSÁRIA")){
            JOptionPane.showMessageDialog(null, "Ops! Problema Grave. Erro 155.",
                " Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jPanel11MouseEntered
    
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
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela1().setVisible(true);
                
                JOptionPane.showMessageDialog(null, "Olá, Bem Vindo(a) ao nosso Sistema e Emissão de Recibo!\n"
                        + "Se esta for sua primeira vez no Sistema, recomendamos você passar pelo nosso "
                        + "''Tutorial'' para entender o \nessencial. Caso queira aprender mais, você pode "
                        + "navegar e explorar nossa Tela de Ajuda, na barra de tarefas.", " Bem Vindo(a)",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aviso;
    private javax.swing.JLabel campologo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel mens1;
    private javax.swing.JLabel mens2;
    private javax.swing.JLabel mens3;
    private javax.swing.JLabel mens4;
    private javax.swing.JLabel mens5;
    // End of variables declaration//GEN-END:variables
}
