package src.modulo8.complementos;

import com.sun.opengl.util.BufferUtil;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
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
    private ByteBuffer checkImageBuf;// =
    byte[] dukeRGBA;
    DataBufferByte dukeBuf;
    Image img;

    public TratadorDeImagem(String caminhoArquivo) throws IOException {
        try {
            this.imagem = ImageIO.read(new File(caminhoArquivo));
            img = Toolkit.getDefaultToolkit().createImage(caminhoArquivo);    
        } catch (Exception e) {
            System.err.println("#Erro : " + e.toString());
        }


        this.width = imagem.getWidth();
        this.height = imagem.getHeight();
        System.out.println("W: " + width + "x H: " + height);
        // Create a raster with correct size,
        // and a colorModel and finally a bufImg.

        WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 4, null);
        ComponentColorModel colorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
                new int[]{8, 8, 8, 8},
                true,
                false,
                ComponentColorModel.TRANSLUCENT,
                DataBuffer.TYPE_BYTE);

        BufferedImage bufImg = new BufferedImage(colorModel, // color model
                raster,
                false, // isRasterPremultiplied
                null); // properties

        // Filter img into bufImg and perform
        // Coordinate Transformations on the way.
        //
        Graphics2D g = bufImg.createGraphics();
        AffineTransform gt = new AffineTransform();
        gt.translate(0, height);
        gt.scale(1, -1d);
        g.transform(gt);
        g.drawImage(img, null, null);

        dukeBuf = (DataBufferByte) raster.getDataBuffer();
        dukeRGBA = dukeBuf.getData();
        checkImageBuf.wrap(dukeRGBA);

    }

    public DataBufferByte getDukeBuf() {
        return dukeBuf;
    }

    public void setDukeBuf(DataBufferByte dukeBuf) {
        this.dukeBuf = dukeBuf;
    }

    public byte[] getDukeRGBA() {
        return dukeRGBA;
    }

    public void setDukeRGBA(byte[] dukeRGBA) {
        this.dukeRGBA = dukeRGBA;
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
