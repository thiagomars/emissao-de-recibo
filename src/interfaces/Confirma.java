package interfaces;

import conection.ConectionSQLite;
import java.awt.Color;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.bean.Ativar;

public class Confirma extends javax.swing.JFrame {

    private JFrame Confirma = this;
    int auc1, auc2, auc3, auc5, auc6, auc7, auc9, auc10, auc11, auc13, auc14;
    int aux1, aux3, aux4, aux5, aux6, aux7, aux8, aux9;
    String aux2, key1, key2, key3;
    int contagem = 1, psomacpf, ssomacpf, prestocpf, srestocpf, caracteres;
    boolean verfic = false, digcpfum = false, digcpfdois = false, validarcpf = false, chave2 = false;
    int keyDigQuatro, keyDigCinco, keyDigSeis, keyDigSete, keyDigOnze, keyDigTreze, keyDigCartoze;
    String keyDigUm, keyDigDois, keyDigTres, keyDigOito, keyDigNove, keyDigDez,  keyDigDoze, keyDigQuinze;
    String vr1, vr2, vr3, vr8, vr9, vr10, vr12, vr15;
    int vr4, vr5, vr6, vr7, vr11, vr13, vr14;
    boolean validarkey = false;
    
    public Confirma() {
        initComponents();
    }

    Color cinzaclaro = new Color (237, 237, 237);
    Color verdel = new Color (43, 227, 45);
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JFormattedTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(43, 227, 45));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hanging-over-the-key.png"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INSTRUÇÕES");

        jScrollPane1.setBorder(null);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(43, 227, 45));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Para concluir a ativação após ter feito o cadastro no nosso Sistema com um de nossos atendentes, você deve:\n\n# A primeira key é o CPF do titular que foi feito o cadastro no nosso Sistema;\n\n# A segunda KEY, deve ser gerada a partir do ícone acima de acordo com nosso algoritmo;\n\n# Copie e cole a chave gerada no segundo espaço ao lado;\n\n# Para a terceira KEY, entre em contato conosco para podermos gerar um código de acordo com os seus dados e o do prorama;\n\n# Você pode encontrar informações para contato no ícone de ajuda acima;\n\nATENÇÃO: a configuração e instalação do Banco de Dados só será feita após o cadastro e ativação do Sistema.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel6.setFont(new java.awt.Font("SF UI Display", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("?");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel6MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 600));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Digite as chaves de ativação para");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 310, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(" ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 398, 310, 30));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("iniciar o programa.");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 310, -1));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 220, 10));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 220, 10));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 220, 10));

        jTextField1.setBackground(new java.awt.Color(51, 51, 51));
        jTextField1.setFont(new java.awt.Font("SF UI Text", 1, 17)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(43, 227, 45));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText("");
        jTextField1.setBorder(null);
        jTextField1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField1.setSelectedTextColor(new java.awt.Color(0, 204, 0));
        jTextField1.setSelectionColor(new java.awt.Color(255, 255, 255));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 220, 20));

        jTextField2.setBackground(new java.awt.Color(51, 51, 51));
        jTextField2.setBorder(null);
        jTextField2.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setFont(new java.awt.Font("SF UI Text", 1, 17)); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 220, 20));

        jTextField3.setBackground(new java.awt.Color(51, 51, 51));
        jTextField3.setFont(new java.awt.Font("SF UI Text", 1, 17)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(43, 227, 45));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setToolTipText("");
        jTextField3.setBorder(null);
        jTextField3.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jTextField3.setSelectedTextColor(new java.awt.Color(0, 204, 0));
        jTextField3.setSelectionColor(new java.awt.Color(255, 255, 255));
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 220, 20));

        jButton1.setFont(new java.awt.Font("SF UI Text", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(43, 227, 45));
        jButton1.setText("VALIDAR");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(43, 227, 45), 2));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, 90, 30));

        jLabel5.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("X");
        jLabel5.setToolTipText("Fechar");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 20, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("-");
        jLabel8.setToolTipText("Voltar");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 15, 20));

        jLabel10.setFont(new java.awt.Font("SF UI Text", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("3º");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 20));

        jLabel11.setFont(new java.awt.Font("SF UI Text", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("1º");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 20));

        jLabel12.setFont(new java.awt.Font("SF UI Text", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("2º");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 20));

        jCheckBox1.setBackground(new java.awt.Color(51, 51, 51));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(204, 204, 204));
        jCheckBox1.setText("Li e Concordo com todos os termos.");
        jCheckBox1.setToolTipText("");
        jCheckBox1.setAlignmentY(0.0F);
        jCheckBox1.setBorder(null);
        jCheckBox1.setBorderPaintedFlat(true);
        jCheckBox1.setContentAreaFilled(false);
        jCheckBox1.setFocusable(false);
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBox1.setIconTextGap(9);
        jPanel4.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ATIVAÇÃO");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 210, 60));

        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ler os Termos.");
        jLabel14.setToolTipText("");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 473, 80, 10));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/telephone-keypad-with-ten-keys.png"))); // NOI18N
        jLabel9.setToolTipText("Gerar KEY");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 15, 20));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 330, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean statusA = false, statusB = false;
        
        if(keyDigUm.equals(vr1) && keyDigDois.equals(vr2) && keyDigTres.equals(vr3) && keyDigQuatro == vr4 && keyDigCinco == vr5 && 
                keyDigSeis == vr6 && keyDigSete == vr7 && keyDigOito.equals(vr8) && keyDigNove.equals(vr9) && keyDigDez.equals(vr10) && 
                keyDigOnze == vr11 && keyDigDoze.equals(vr12) && keyDigTreze == vr13 && keyDigCartoze == vr14 && keyDigQuinze.equals(vr15)){
            validarkey = true;
        }else{
            validarkey = false;
        }
        
        if(jCheckBox1.isSelected() && validarcpf == true && validarkey == true){
                //verificar um arquivo
                try{
                    FileReader ler = new FileReader("C:\\RC SISTEMA\\files\\activation.txt");
                    BufferedReader lerb = new BufferedReader(ler);
                    String linha = lerb.readLine();

                    while(linha.equals("*key activation ### = &()@NO")){
                        File arquivo = new File("C:\\RC SISTEMA\\files\\activation.txt");
                        if(linha.equals("*key activation ### = &()@NO")){
                            FileWriter fileWriter = new FileWriter(arquivo);
                            BufferedWriter escrever = new BufferedWriter(fileWriter);
                            escrever.write("*key activation ### = $1[]YES");

                            escrever.close();
                            fileWriter.close();

                            linha = lerb.readLine();

                            new Tela1().setVisible(true);
                            Confirma.dispose();
                            
                            statusA = true;
                        }
                    }
                    
                    ConectionSQLite consqlite = new ConectionSQLite();
                    
                    consqlite.conectar();
                    ResultSet resultset = null;
                    
                    PreparedStatement stmt = consqlite.criarPreparedStatement("INSERT INTO ativar "
                            + "(status_ativar, data_ativar) VALUES (?,?);");
                    
                    java.util.Date d = new Date();
                    String dataatual = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        
                    try {
                        stmt.setString(1, "YES");
                        stmt.setString(2, dataatual);
                        
                        stmt.executeUpdate();
                        
                        statusB = true;
                        
                    } catch (SQLException ex) {
                        statusB = false;
                    }
                    
                    consqlite.desconectar();
                    
                } catch(IOException e){
                    statusA = false;
                } finally{
                    if(statusA == false || statusB == false){
                        JOptionPane.showMessageDialog(null, "Nem todas as operações foram bem sucedidas. Entre "
                                + "em contato com um de nossos \natendentes para Verificar e Corrigir o erro.");
                    }else{
                        JOptionPane.showMessageDialog(null, "A ativação foi concluida com Sucesso! Tenha um Bom Dia "
                                + "e até logo. ");
                    }
                }

            if(contagem == 1){
                jLabel3.setText("<html><font color = white>Ops! Parece que tem algo de errado</font></html>");
                jLabel7.setText("<html><font color = white>com a(s) chave(s) inserida.</font></html>");
                jLabel4.setText("3 tentativas restantes!");
                contagem = 2;
            }else{
                if(contagem == 2){
                    jLabel4.setText("2 tentativas restantes!");
                    contagem = 3;
                }else{
                    if(contagem == 3){
                        jLabel4.setText("Última tentativa!");
                        contagem = 0;
                    }else{
                        JOptionPane.showMessageDialog(null, "Muitas tentativas fracassadas. Tchau!");
                        System.exit(0);
                    }
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Você só poderá fazer o uso do "
                    + "programa se você declarar que leu e \naceitou com os termos de "
                    + "uso e compromisso do mesmo..", " Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setForeground(Color.ORANGE);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        jLabel5.setForeground(Color.WHITE);
        jLabel8.setForeground(Color.WHITE); 
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        new Ajuda().setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setForeground(Color.GREEN);
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        new Tela1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        jLabel6.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        jLabel6.setForeground(cinzaclaro);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseReleased
        jLabel6.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel6MouseReleased

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        Color vverde = new Color (43,227,45);
        jTextField2.setForeground(vverde);
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        //Pega os cacteres separados um por um do primeiro campo///////////////////////////////////
        auc1 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(0)));
        auc2 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(1)));
        auc3 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(2)));
        auc5 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(4)));
        auc6 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(5)));
        auc7 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(6)));
        auc9 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(8)));
        auc10 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(9)));
        auc11 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(10)));
        auc13 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(12)));
        auc14 = Integer.parseInt(String.valueOf(jTextField2.getText().charAt(13)));
        ///////////////////////////////////////////////////////////////////////////////////////////
        //Começa a fazer a verificação de acordo com os caracteres digitados
        psomacpf = (auc1 * 10) + (auc2 * 9) + (auc3 * 8) + (auc5 * 7) + (auc6 * 6) + (auc7 * 5) + 
                (auc9 * 4) + (auc10 * 3) + (auc11 * 2);
        prestocpf = psomacpf % 11;
        //Calculo do Primeiro digito verificador
        if(prestocpf < 2){
            if(auc13 == 0){
                digcpfum = true;
            }else{
                digcpfum = false;
            }
        }else{
            if(auc13 == (11 - prestocpf)){
                digcpfum = true;
            }else{
                digcpfum = false;
            }
        }
        //Calculo do Segundo digito verificador
        if(digcpfum == true){
            ssomacpf = (auc1 * 11) + (auc2 * 10) + (auc3 * 9) + (auc5 * 8) + (auc6 * 7) + (auc7 * 6) + 
                (auc9 * 5) + (auc10 * 4) + (auc11 * 3) + (auc13 * 2);
            srestocpf = ssomacpf % 11;
            
            if(srestocpf < 2){
                if(auc14 == 0){
                    digcpfdois = true;
                }else{
                    digcpfdois = false;
                }
            }else{
                if(auc14 == (11 - srestocpf)){
                    digcpfdois = true;
                }else{
                    digcpfdois = false;
                }
            }
        }
        //Primeira verificação do CPF inserido no primeiro campo
        if(auc1 == auc2 && auc2 == auc3 && auc3 == auc5 && auc5 == auc6 && auc6 == auc7 
                && auc7 == auc9 && auc9 == auc10 && auc10 == auc11 && auc11 == auc13 && auc13 == auc14){
            validarcpf = false;
        }else{
            //Verificar o CPF digitado
            if(digcpfum == true && digcpfdois == true){
                validarcpf = true;
            }else{
                validarcpf = false;
                jTextField2.setForeground(Color.RED);
            }
        }
        
    }//GEN-LAST:event_jTextField2FocusLost

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        JOptionPane.showMessageDialog(null, "Atenção, antes de prosseguir: \n"
                + "1- Certifique-se de estar com o CPF do cadastrado em mãos; \n"
                + "2- Fique ciente que você só poderá realizar este processo uma única vez; \n"
                + "3- Certifique de estar com um celular por perto para a terceira etapa da ativação; \n"
                + "4- Verifique sua conexão com a internet para receber o email com a ativação; \n"
                + "5- Pegue um papel e anote a chave de segurança que será gerada, pois esse processo é irrepetível;");
        new GerarKEY().setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        Color vvvv = new Color (43, 227, 45);
        jButton1.setForeground(vvvv);
    }//GEN-LAST:event_jButton1MouseReleased

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        jLabel5.setForeground(Color.RED);
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        jLabel8.setForeground(Color.ORANGE);
    }//GEN-LAST:event_jLabel8MousePressed

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        //Pegar os digitos do campo 3 da key
        vr1 = String.valueOf(jTextField3.getText().charAt(0));
        vr2 = String.valueOf(jTextField3.getText().charAt(1));
        vr3 = String.valueOf(jTextField3.getText().charAt(2));
        vr4 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(4)));
        vr5 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(5)));
        vr6 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(6)));
        vr7 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(7)));
        vr8 = String.valueOf(jTextField3.getText().charAt(9));
        vr9 = String.valueOf(jTextField3.getText().charAt(10));
        vr10 = String.valueOf(jTextField3.getText().charAt(11));
        vr11 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(13)));
        vr12 = String.valueOf(jTextField3.getText().charAt(14));
        vr13 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(15)));
        vr14 = Integer.parseInt(String.valueOf(jTextField3.getText().charAt(16)));
        vr15 = String.valueOf(jTextField3.getText().charAt(17));
        
        //Verificar e Validar ou Não a terceira chave inserida
        //Primeiro Digito
        if(((auc13 + auc14) / 2) == 1){
            keyDigUm = "R";
        }
        if(((auc13 + auc14) / 2) == 2){
            keyDigUm = "F";
        }
        if(((auc13 + auc14) / 2) == 3){
            keyDigUm = "E";
        }
        if(((auc13 + auc14) / 2) == 4){
            keyDigUm = "X";
        }
        if(((auc13 + auc14) / 2) == 5){
            keyDigUm = "Y";
        }
        if(((auc13 + auc14) / 2) == 6){
            keyDigUm = "M";
        }
        if(((auc13 + auc14) / 2) == 7){
            keyDigUm = "S";
        }
        if(((auc13 + auc14) / 2) == 8){
            keyDigUm = "N";
        }
        if(((auc13 + auc14) / 2) == 9){
            keyDigUm = "P";
        }
        if(((auc13 + auc14) / 2) == 0){
            keyDigUm = "A";
        }
        //Segundo Digito
        int somad = ((auc13 + aux1) % 2);
        if(somad == 1 && "R".equals(keyDigUm)){
            keyDigDois = "E";
        }
        if(somad == 1 && "F".equals(keyDigUm)){
            keyDigDois = "B";
        }
        if(somad == 1 && "E".equals(keyDigUm)){
            keyDigDois = "T";
        }
        if(somad == 1 && "X".equals(keyDigUm)){
            keyDigDois = "Q";
        }
        if(somad == 1 && "Y".equals(keyDigUm)){
            keyDigDois = "U";
        }
        if(somad == 1 && "M".equals(keyDigUm)){
            keyDigDois = "W";
        }
        if(somad == 1 && "S".equals(keyDigUm)){
            keyDigDois = "G";
        }
        if(somad == 1 && "N".equals(keyDigUm)){
            keyDigDois = "C";
        }
        if(somad == 1 && "P".equals(keyDigUm)){
            keyDigDois = "L";
        }
        if(somad == 1 && "A".equals(keyDigUm)){
            keyDigDois = "K";
        }
        
        if(somad == 0 && "R".equals(keyDigUm)){
            keyDigDois = "H";
        }
        if(somad == 0 && "F".equals(keyDigUm)){
            keyDigDois = "J";
        }
        if(somad == 0 && "E".equals(keyDigUm)){
            keyDigDois = "T";
        }
        if(somad == 0 && "X".equals(keyDigUm)){
            keyDigDois = "X";
        }
        if(somad == 0 && "Y".equals(keyDigUm)){
            keyDigDois = "Z";
        }
        if(somad == 0 && "M".equals(keyDigUm)){
            keyDigDois = "O";
        }
        if(somad == 0 && "S".equals(keyDigUm)){
            keyDigDois = "G";
        }
        if(somad == 0 && "N".equals(keyDigUm)){
            keyDigDois = "I";
        }
        if(somad == 0 && "P".equals(keyDigUm)){
            keyDigDois = "D";
        }
        if(somad == 0 && "A".equals(keyDigUm)){
            keyDigDois = "K";
        }
        //Terceiro Digito
        if(aux3 == 1){
            keyDigTres = "A";
        }
        if(aux3 == 2){
            keyDigTres = "F";
        }
        if(aux3 == 3){
            keyDigTres = "R";
        }
        if(aux3 == 4){
            keyDigTres = "N";
        }
        if(aux3 == 5){
            keyDigTres = "W";
        }
        if(aux3 == 6){
            keyDigTres = "B";
        }
        if(aux3 == 7){
            keyDigTres = "T";
        }
        if(aux3 == 8){
            keyDigTres = "H";
        }
        if(aux3 == 9){
            keyDigTres = "E";
        }
        if(aux3 == 0){
            keyDigTres = "I";
        }
        //Quarto Digito
        keyDigQuatro = (((aux1 * 9) + (aux1 * 8) + (aux1 * 7) / 5) % 5);
        //Quinto Digito
        keyDigCinco = auc14;
        //Sexto Digito
        keyDigSeis = ((aux1 + auc3) / 2);
        //Setimo Digito
        if(auc7 != 0){
            keyDigSete = (auc7 - 1);
        }else{
            keyDigSete = 0;
        }
        //Oitavo Digito
        if((auc14 % 2) == 0){
            keyDigOito = "I";
        }else{
            keyDigOito = "P";
        }
        //Nono Digito
        if(keyDigCinco % 2 == 0){
            if(auc3 == 1){
                keyDigNove = "H";
            }
            if(auc3 == 2){
                keyDigNove = "B";
            }
            if(auc3 == 3){
                keyDigNove = "E";
            }
            if(auc3 == 4){
                keyDigNove = "Q";
            }
            if(auc3 == 5){
                keyDigNove = "Z";
            }
            if(auc3 == 6){
                keyDigNove = "W";
            }
            if(auc3 == 7){
                keyDigNove = "S";
            }
            if(auc3 == 8){
                keyDigNove = "C";
            }
            if(auc3 == 9){
                keyDigNove = "D";
            }
            if(auc3 == 0){
                keyDigNove = "A";
            }
        }
        
        if(keyDigCinco % 2 == 1){
            if(auc3 == 1){
                keyDigNove = "R";
            }
            if(auc3 == 2){
                keyDigNove = "F";
            }
            if(auc3 == 3){
                keyDigNove = "T";
            }
            if(auc3 == 4){
                keyDigNove = "X";
            }
            if(auc3 == 5){
                keyDigNove = "U";
            }
            if(auc3 == 6){
                keyDigNove = "O";
            }
            if(auc3 == 7){
                keyDigNove = "S";
            }
            if(auc3 == 8){
                keyDigNove = "N";
            }
            if(auc3 == 9){
                keyDigNove = "P";
            }
            if(auc3 == 0){
                keyDigNove = "K";
            }
        }else{
            JOptionPane.showMessageDialog(null, "Erro inesperado. Error 312", 
                    " Error", JOptionPane.ERROR_MESSAGE);
        }
        //Decimo Digito
        if((auc13 % 2) == 0 && (auc14 % 2) == 0){
            keyDigDez = "A";
        }
        if((auc13 % 2) == 1 && (auc14 % 2) == 0){
            keyDigDez = "E";
        }
        if((auc13 % 2) == 0 && (auc14 % 2) == 1){
            keyDigDez = "O";
        }
        if((auc13 % 2) == 1 && (auc14 % 2) == 1){
            keyDigDez = "U";
        }
        //Decimo Primeiro Digito
        keyDigOnze = auc5;
        //Decimo Segundo Digito
        if(aux3 == 1){
            keyDigDoze = "Y";
        }
        if(aux3 == 2){
            keyDigDoze = "P";
        }
        if(aux3 == 3){
            keyDigDoze = "D";
        }
        if(aux3 == 4){
            keyDigDoze = "H";
        }
        if(aux3 == 5){
            keyDigDoze = "L";
        }
        if(aux3 == 6){
            keyDigDoze = "B";
        }
        if(aux3 == 7){
            keyDigDoze = "C";
        }
        if(aux3 == 8){
            keyDigDoze = "Q";
        }
        if(aux3 == 9){
            keyDigDoze = "Z";
        }
        if(aux3 == 0){
            keyDigDoze = "X";
        }
        //Decimo Terceiro Digito
        if(auc10 != 9){
            keyDigTreze = auc10 + 1;
        }else{
            keyDigTreze = 0;
        }
        //Decimo Quarto Digito
        keyDigCartoze = (((auc5 * 4) + (auc6 * 5) + (auc7 * 6) / 19) % 9);
        //Decimo Quinto Digito
        if(keyDigCartoze == 1){
            keyDigQuinze = "B";
        }
        if(keyDigCartoze == 2){
            keyDigQuinze = "C";
        }
        if(keyDigCartoze == 3){
            keyDigQuinze = "D";
        }
        if(keyDigCartoze == 4){
            keyDigQuinze = "F";
        }
        if(keyDigCartoze == 5){
            keyDigQuinze = "G";
        }
        if(keyDigCartoze == 6){
            keyDigQuinze = "H";
        }
        if(keyDigCartoze == 7){
            keyDigQuinze = "J";
        }
        if(keyDigCartoze == 8){
            keyDigQuinze = "K";
        }
        if(keyDigCartoze == 9){
            keyDigQuinze = "L";
        }
        if(keyDigCartoze == 0){
            keyDigQuinze = "M";
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
        
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        String sep1 = String.valueOf(jTextField1.getText().charAt(3));
        String sep2 = String.valueOf(jTextField1.getText().charAt(7));
        
        if(jTextField1.getText().length() == 11 && "-".equals(sep1) && "-".equals(sep2)){
            //Pega os cacteres separados um por um do segundo campo
            aux1 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(0)));
            aux2 = String.valueOf(jTextField1.getText().charAt(1));
            aux3 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(2)));
            aux4 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(4)));
            aux5 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(5)));
            aux6 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(6)));
            aux7 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(8)));
            aux8 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(9)));
            aux9 = Integer.parseInt(String.valueOf(jTextField1.getText().charAt(10)));
            chave2 = true;
        }else{
            jTextField1.setForeground(Color.RED);
            chave2 = false;
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.setForeground(verdel);
    }//GEN-LAST:event_jTextField1FocusGained

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        Color vvvs = new Color (19,144,20);
        jButton1.setForeground(vvvs);
    }//GEN-LAST:event_jButton1MousePressed

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
            java.util.logging.Logger.getLogger(Confirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Confirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Confirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Confirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Confirma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JFormattedTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
