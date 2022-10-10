import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.crypto.*;
import javax.crypto.SecretKeyFactory;
import java.security.*;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.PBEKeySpec;
// import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class EncryptPicture
{
    // Your program begins with a call to main().
    // Prints "Hello, World" to the terminal window.
    public static void main(String args[])
    {
        BufferedImage img = null;

		try {
            img = ImageIO.read(new File("s.jpg"));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            PBEKeySpec pbeKeySpec = new PBEKeySpec("bar".toCharArray());
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            PBEParameterSpec pbeParamSpec = new PBEParameterSpec("saltandp".getBytes(), 20);
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            System.out.println("Hello, World");
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
            FileOutputStream output = new FileOutputStream("saved.png");
            CipherOutputStream cos = new CipherOutputStream(output, pbeCipher);
            ImageIO.write(img,"png",cos);
            cos.close();
		}
        catch (IOException e) {}
        catch (NoSuchAlgorithmException e) {}
        catch (NoSuchPaddingException e) {}
        catch (InvalidKeySpecException e) {
            System.out.println("InvalidKeySpecException");
        }
        catch (InvalidKeyException e) {
            System.out.println("InvalidKeyException");
        }
        catch (InvalidAlgorithmParameterException e) {
            System.out.println("InvalidAlgorithmParameterException: " + e);
        }
    }
}
