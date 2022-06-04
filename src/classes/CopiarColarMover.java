package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.JOptionPane;

public class CopiarColarMover {
    
     public void bdCopyAndPaste(File destino) throws IOException{
            File origem = new File("C:\\RC SISTEMA\\bd\\banco.db");
            
            FileChannel sourcechannel = null;
            FileChannel destinationchannel = null;
            
            destino.delete();

            try {
                sourcechannel = new FileInputStream(origem).getChannel();
                destinationchannel = new FileOutputStream(destino).getChannel();
                sourcechannel.transferTo(0, sourcechannel.size(), destinationchannel);
                
                JOptionPane.showMessageDialog(null, "O Backup foi realizado com sucesso na sua Área de Trabalho!"
                        + "\nAtenção: O nome do arquivo não deve ser modificado. A pasta está livre para"
                        + " manipulaçao como o usuário desejar.", " Backup do BD", JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error 2051" + ex);
            }catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error 2052");
            }finally{
                if (sourcechannel != null && sourcechannel.isOpen())
                    sourcechannel.close();
                if (destinationchannel != null && destinationchannel.isOpen())
                    destinationchannel.close();
            }
        }
     
     public void bdMove(File local) throws IOException{
            File destino = new File("C:\\RC SISTEMA\\bd\\");
            
            boolean sucesso = local.renameTo(new File(destino, local.getName()));
            
            if(sucesso){
                System.out.println("yeeeessssss");
            }else{
                System.out.println("nooooooooooot");
            }
            
            
//            FileChannel sourcechannel = null;
//            FileChannel destinationchannel = null;
//            
//            destino.delete();
//
//            try {
//                sourcechannel = new FileInputStream(local).getChannel();
//                destinationchannel = new FileOutputStream(destino).getChannel();
//                sourcechannel.transferTo(0, sourcechannel.size(), destinationchannel);
//                
//                JOptionPane.showMessageDialog(null, "O novo Banco foi adicionado comSucesso!"
//                        + " Boa Sorte e Até mais. ", " Mover BD", JOptionPane.PLAIN_MESSAGE);
//            } catch (FileNotFoundException ex) {
//                JOptionPane.showMessageDialog(null, "Error 2053" + ex);
//            }catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error 2054");
//            }finally{
//                if (sourcechannel != null && sourcechannel.isOpen())
//                    sourcechannel.close();
//                if (destinationchannel != null && destinationchannel.isOpen())
//                    destinationchannel.close();
//            }
        }
     
     
     
     
     
     
     
     
     
    
}
