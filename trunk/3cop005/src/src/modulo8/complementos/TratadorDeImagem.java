package src.modulo8.complementos;

import com.sun.opengl.util.BufferUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author uel
 */
public class TratadorDeImagem {
    private static final int rgb = 3;
    private BufferedImage imagem;
    private int width;
    private int height;
    private int[] pixels;
    private ByteBuffer checkImageBuf;// =
  //BufferUtil.newByteBuffer(height * width * rgb);

    public TratadorDeImagem(String caminhoArquivo) throws IOException{
        this.imagem = ImageIO.read(new File(caminhoArquivo));
        this.width = imagem.getWidth();
        this.height = imagem.getHeight();
        this.pixels = imagem.getRGB(0, 0, width, height, null, 0, width);

        checkImageBuf =
  BufferUtil.newByteBuffer(height * width * rgb);

        byte c = (byte) 0xFF;
        Random r = new Random();

        for (int col = 0; col < width; col++) {
          for (int lin = 0; lin < height; lin++) {
            pixels[width * lin + col] =
              new Color(r.nextInt(255), col % 255, lin % 255).getRGB();

            c = (byte)( ( ((byte)((col & 0x8)==0?0x00:0xff)//
            ^(byte)((lin & 0x8)==0?0x00:0xff))));
        // checkImage[i][j][0] = (byte) c;
        // checkImage[i][j][1] = (byte) c;
        // checkImage[i][j][2] = (byte) c;
        checkImageBuf.put((byte) c);
        checkImageBuf.put((byte) c);
        checkImageBuf.put((byte) c);

          }
        }

        checkImageBuf.rewind();
        imagem.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(imagem, "PNG", new File("arteabstrata.png"));
    }

    

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public ByteBuffer getCheckImageBuf() {
        return checkImageBuf;
    }

    public void setCheckImageBuf(ByteBuffer checkImageBuf) {
        this.checkImageBuf = checkImageBuf;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
