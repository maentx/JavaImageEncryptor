import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

class HelloWorld
{
    public static void main(String args[])
    {
        BufferedImage img = null;

		try {
            img = ImageIO.read(new File("s.jpg"));
		    File outputfile = new File("saved.png");
		    ImageIO.write(img, "png", outputfile);
		}
        catch (IOException e) {}
    }
}
