package interfaces;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

public class GerarKEY extends javax.swing.JFrame {

    char aux1, aux2, aux3, aux4, aux5, aux6, aux7, aux8, aux9, aux10, aux11, aux12, aux13, aux14;
    String auz1, auz2, auz3, auz4, auz5, auz6, auz7, auz8, auz9, auz10, auz11, auz12, auz13, auz14;
    int auc1, auc2, auc3, auc4, auc5, auc6, auc7, auc8, auc9, auc10, auc11, auc12, auc13, auc14;
    int psomacpf, ssomacpf, prestocpf, srestocpf;
    boolean digcpfum = false, digcpfdois = false, validarcpf = false, verific = false;
    int dig1, dig3, dig4, dig5, dig6, dig7, dig8, dig9;
    String dig2;
    
    public GerarKEY() {
        initComponents();
        
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jButton2.setVisible(false);
        jTextField1.setVisible(false);
        jProgressBar1.setVisible(false);
        
        //Pegar a data do Sistema
        java.util.Date d = new Date();
        String dataatual = java.text.DateFormat.getDateInstance(DateFormat.LONG).format(d);
        jLabel3.setText(dataatual);
        
        //Verificação do arquivo para Gerar a chave de Ativação
        Path caminho = Paths.get("/RC SISTEMA/files/ankey.txt");
        
        byte[] texto = null;
        try {
            texto = Files.readAllBytes(caminho);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        String view = new String(texto);
        
        if("8ijd8m.s7klzt(kajaqwcepl922;ppceo9ksnvie9@kwk[q.ws]1dkdkdfikms9209839021091920120n883901)".equals(view)){
            JOptionPane.showMessageDialog(null, "A chave já foi construida em algum momento.", " Atenção", 
                    JOptionPane.ERROR_MESSAGE);
            jButton1.setEnabled(false);
        }else{
            jButton1.setEnabled(true);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GERAR KEY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 469, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Informe o CPF do cadastrado:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 114, -1, -1));

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jFormattedTextField1.setMargin(new java.awt.Insets(5, 8, 5, 5));
        jPanel1.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 107, 160, 30));

        jLabel3.setForeground(new java.awt.Color(193, 189, 189));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 240, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton1.setText("Verificar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 107, 110, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 469, 11));

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.setSelectionColor(new java.awt.Color(0, 204, 0));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 469, 72));

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ATENÇÃO: A chave KEY será gerada apenas uma única vez. Portanto, certifique-se de");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 177, 469, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("estar ativando o Programa ao finalizar este procedimento.");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 198, 469, -1));

        jButton2.setFont(new java.awt.Font("Arial Black", 0, 20)); // NOI18N
        jButton2.setText("GERAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 231, 183, 49));
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 304, 470, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if("   .   .   -  ".equals(jFormattedTextField1.getText())){
            JOptionPane.showMessageDialog(null, "Digite um CPF válido!");
        }else{
            
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            
        }
        
        Path caminho = Paths.get("/RC SISTEMA/files/ankey.txt");
        byte[] texto = null;
        try {
            texto = Files.readAllBytes(caminho);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro inesperado. Error 42.", 
                    " Error", JOptionPane.ERROR_MESSAGE);
        }
        
        String view = new String(texto);
        
        if("8ijd8m.s7klzt(kajaqwcepl922;ppceo9ksnvie9@kwk[qw.s]1dkdkdfikms9209839021091920120n883901)".equals(view)){
            
            Path local = Paths.get("/RC SISTEMA/files/ankey.txt");
            String text = "8ijd8m.s7klzt(kajaqwcepl922;ppceo9ksnvie9@kwk[q.ws]1dkdkdfikms9209839021091920120n883901)";
            
            byte[] textb = text.getBytes();
            
            try {
                Files.write(local, textb);
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, "Erro inesperado. Error 43.", 
                    " Error", JOptionPane.ERROR_MESSAGE);
            }
            
            JOptionPane.showMessageDialog(null, "Antenção, essa chave só será gerada uma "
                    + "única vez, portanto, tome muito cuidado para não \nesquecer ela. "
                    + "Copie essa chave e cole no primeiro espaço da KEY. Depois entre em "
                    + "contato \ncom um de nossos atendentes para ele poder liberar as outras"
                    + "duas chaves de acesso. \nBoa Sorte!");
            
            jButton1.setEnabled(false);
            
            //Converte os valores de Strign para int
            auc1 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(0)));
            auc2 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(1)));
            auc3 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(2)));
            auc5 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(4)));
            auc6 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(5)));
            auc7 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(6)));
            auc9 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(8)));
            auc10 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(9)));
            auc11 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(10)));
            auc13 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(12)));
            auc14 = Integer.parseInt(String.valueOf(jFormattedTextField1.getText().charAt(13)));

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

            if(auc1 == auc2 && auc2 == auc3 && auc3 == auc5 && auc5 == auc6 && auc6 == auc7 
                    && auc7 == auc9 && auc9 == auc10 && auc10 == auc11 && auc11 == auc13 && auc13 == auc14){
                validarcpf = false;
            }else{
                //Verificar o CPF digitado
                if(digcpfum == false || digcpfdois == false){
                    JOptionPane.showMessageDialog(null, "O CPF digitado é inválido.");
                }else{
                    jLabel4.setVisible(true);
                    jLabel5.setVisible(true);
                    jButton2.setVisible(true);
                    jTextField1.setVisible(true);
                }
            }    
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jProgressBar1.setVisible(true);
        jButton2.setEnabled(false);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
        
        new Thread(){
            public void run(){
                for (int i = 0; i < 101; i++){
                    try{
                        sleep(70);
                        jProgressBar1.setValue(i);
                        
                        if(jProgressBar1.getValue() == 20){
                            Random random = new Random();        
                            //Calcular o primeiro digito
                            dig1 = ((auc1 + auc2 + auc3) / 3);
                            //Calcular o segundo digito
                            if(auc5 == 1){
                                dig2 = "M";
                            }if(auc5 == 2){
                                dig2 = "W";
                            }if(auc5 == 3){
                                dig2 = "G";
                            }if(auc5 == 4){
                                dig2 = "Y";
                            }if(auc5 == 5){
                                dig2 = "S";
                            }if(auc5 == 6){
                                dig2 = "F";
                            }if(auc5 == 7){
                                dig2 = "Z";
                            }if(auc5 == 8){
                                dig2 = "X";
                            }if(auc5 == 9){
                                dig2 = "P";
                            }if(auc5 == 0){
                                dig2 = "K";
                            }
                            //Calcular o terceiro digito
                            if((((auc13 * 7) + (auc14 * 9)) / 10) > 10){
                                dig3 = 0;
                            }else{
                                dig3 = (((auc13 * 7) + (auc14 * 9)) / 10);
                            }
                            //Calcular o quarto, quinto e sexto digito
                            dig4 = random.nextInt(9);
                            dig5 = random.nextInt(9);
                            dig6 = random.nextInt(9);
                            //Calcular o setimo digito
                            if((dig1 + dig5) < 4){
                                dig7 = 0;
                            }else{
                                dig7 = ((dig1 + dig5) / 2);
                            }
                            //Calcular o oitavo digito
                            if(((dig7 + auc2 + auc3) / 2) < 10){
                                dig8 = ((dig7 + auc2 + auc3) / 2);
                            }else{
                                dig8 = ((dig7 + auc2) / 2);
                            }
                            //Calcular o nono digito
                            if(((dig1 + auc13 + dig5 + 30) - 21) <= 31){
                                 dig9 = 0;
                            }else{
                                dig9 = (((dig1 + auc13 + dig5 + 30) - 21) % 11);
                            }
                        }
                        
                        if(jProgressBar1.getValue() == 100){
                            jTextField1.setText(String.valueOf(dig1) + dig2 + String.valueOf(dig3) + 
                                    "-" + String.valueOf(dig4) + String.valueOf(dig5) + String.valueOf(dig6) 
                                    + "-" + String.valueOf(dig7) + String.valueOf(dig8) + String.valueOf(dig9));
                            JOptionPane.showMessageDialog(null, "Escreva a chave em um papel por segurança e dê um Ctrl + C e cole no campo correspondente.");
                        }
                    } catch (InterruptedException ex){
                        JOptionPane.showMessageDialog(null, "Erro inesperado. Error 44.", 
                    " Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }.start();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        JOptionPane.showMessageDialog(null, "Ei, não esqueça de apagar, riscar ou rasgar o papel onde você escreveu"
                + " a chave KEY. Essa \nchave é muito importante, não repasse ela para ninguém. Até mais.");
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(GerarKEY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarKEY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarKEY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarKEY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerarKEY().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
