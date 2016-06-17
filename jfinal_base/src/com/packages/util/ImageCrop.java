package com.packages.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCrop {

	
	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();

	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	
	public static BufferedImage createResizedCopy(BufferedImage originalImage, int scaledWidth, 
            int scaledHeight, boolean preserveAlpha) { 
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB   : BufferedImage.TYPE_INT_ARGB; 
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType); 
        Graphics2D g = scaledBI.createGraphics(); 
        if (preserveAlpha) { 
            g.setComposite(AlphaComposite.Src); 
        } 
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
        g.dispose(); 
        return scaledBI; 
    } 
	
	 public static BufferedImage clip( BufferedImage image, float... clipVerts )
	  {
	    assert clipVerts.length >= 6;
	    assert clipVerts.length % 2 == 0;

	    int[] xp = new int[ clipVerts.length / 2 ];
	    int[] yp = new int[ xp.length ];

	    int minX = image.getWidth(), minY = image.getHeight(), maxX = 0, maxY = 0;

	    for( int j = 0; j < xp.length; j++ )
	    {
	      xp[ j ] = Math.round( clipVerts[ 2 * j ] * image.getWidth() );
	      yp[ j ] = Math.round( clipVerts[ 2 * j + 1 ] * image.getHeight() );

	      minX = Math.min( minX, xp[ j ] );
	      minY = Math.min( minY, yp[ j ] );
	      maxX = Math.max( maxX, xp[ j ] );
	      maxY = Math.max( maxY, yp[ j ] );
	    }

	    for( int i = 0; i < xp.length; i++ )
	    {
	      xp[ i ] -= minX;
	      yp[ i ] -= minY;
	    }

	    Polygon clip = new Polygon( xp, yp, xp.length );
	    BufferedImage out = new BufferedImage( maxX - minX, maxY - minY,  BufferedImage.TYPE_INT_ARGB );
	    Graphics g = out.getGraphics();
	    g.setClip( clip );

	    g.drawImage( image, -minX, -minY, null );
	    g.dispose();

	    return out;
	  }
	
	public static void main(String[] args) throws IOException {
	    BufferedImage icon = ImageIO.read(new File("c:\\user-logo.png"));
	    BufferedImage rounded = clip(icon, 10,10);
	    ImageIO.write(rounded, "png", new File("c:\\icon.rounded.png"));
	}
}
