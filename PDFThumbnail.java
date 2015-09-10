import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class PDFThumbnail {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("E:\\test.pdf");
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);

		// draw the first page to an image
		PDFPage page = pdffile.getPage(0);

		//get the width and height for the doc at the default zoom 
		Rectangle rect = new Rectangle(0,0,
		                (int)page.getBBox().getWidth(),
		                (int)page.getBBox().getHeight());

		//generate the image
		Image img = page.getImage(
		                rect.width, rect.height, //width & height
		                rect, // clip rect
		                null, // null for the ImageObserver
		                true, // fill background with white
		                true  // block until drawing is done
		                );
		File file1 = new File("E:\\test" + "." + "png");
        try {
            ImageIO.write(toBufferedImage(img), "png", file1);  // ignore returned boolean
        } catch(IOException e) {
            System.out.println("Write error for " + file.getPath() +
                               ": " + e.getMessage());
        }
 
	}
	
    private static BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }

}
