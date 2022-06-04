package classes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ManipularImagem {
    
    public static BufferedImage setImagemDimensao(String caminhoImg, Integer imgLargura, Integer imgAltura){
        Double novaImgLargura = null;
        Double novaImgAltura = null;
        Double imgProporcao = null;
        Graphics2D  g2d = null;
        BufferedImage imagem = null, novaImagem = null;
        
        try {
            //Imagem que vai ser redimensionada
            imagem = ImageIO.read(new File(caminhoImg));
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar iniciar o comando para a imagem");
        }
        
        //Largura da imagem
        novaImgLargura = (double) imagem.getWidth();
        //Altura da imagem
        novaImgAltura = (double) imagem.getHeight();
        
        //Verificar o tamanho da imagem
        if(novaImgLargura >= imgLargura){
            imgProporcao = (novaImgAltura / novaImgLargura);
            novaImgLargura = (double) imgLargura;
            
            novaImgAltura = (novaImgLargura * imgProporcao);
            
            while(novaImgAltura > imgAltura){
                novaImgLargura = (double) (--imgLargura);
                novaImgAltura = (novaImgLargura * imgProporcao);
            }
        }
        
        novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
        g2d = novaImagem.createGraphics();
        g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);
        
        return novaImagem;
        
    }
    
    public static byte[] getImgBytes(BufferedImage image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try{
            ImageIO.write(image, "JPG", baos);
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Erro ao tentar iniciar o comando para a imagem");
        }
        
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        
        return baos.toByteArray();
        
    }
    
    public static void exibiImagemLabel(byte[] minhaimagem, javax.swing.JLabel label){
        if(minhaimagem != null){
            InputStream input = new ByteArrayInputStream(minhaimagem);
            
            try{
                BufferedImage imagem = ImageIO.read(input);
                label.setIcon(new ImageIcon(imagem));
            } catch(IOException ex){
                
            }
        }else{
            label.setIcon(null);
        }
    }
    
}
